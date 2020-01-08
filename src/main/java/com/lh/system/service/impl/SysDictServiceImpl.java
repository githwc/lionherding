package com.lh.system.service.impl;

import cn.hutool.core.util.ObjectUtil;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lh.common.config.exception.RunException.RunningException;
import com.lh.common.config.exception.parameterException.ParameterException;
import com.lh.common.constant.CommonConstant;
import com.lh.common.dao.DaoApi;
import com.lh.common.tree.Tree;
import com.lh.common.tree.TreeNode;
import com.lh.system.entity.SysDict;
import com.lh.system.mapper.SysDictMapper;
import com.lh.system.model.query.DictQuery;
import com.lh.system.service.SysDictService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

/**
 * 功能描述：
 *
 * <p>版权所有：</p>
 * 未经本人许可，不得以任何方式复制或使用本程序任何部分
 *
 * @Company: 紫色年华
 * @Author xieyc
 * @Date 2019-09-20
 * @Version: 1.0.0
 */
@Service
@Transactional(rollbackFor = Exception.class)
@Slf4j
public class SysDictServiceImpl extends ServiceImpl<SysDictMapper, SysDict> implements SysDictService {

    private final DaoApi daoApi;

    @Autowired
    public SysDictServiceImpl(DaoApi daoApi) {
        this.daoApi = daoApi;
    }

    @Override
    public List<TreeNode> dictTree(String name) {
        List<TreeNode> list = this.baseMapper.dictTree(name);
        Tree tree = new Tree(list).setRoot("字典管理").build();
        return tree.getRootNodes();
    }

    @Override
    public Page<SysDict> childrenDict(Page<SysDict> page, DictQuery dictQuery) {
        return this.baseMapper.childrenDict(page, dictQuery);
    }

    @Override
    public void editByDictId(SysDict sysDict) {
        sysDict.setUpdateUserId(daoApi.getCurrentUserId());
        this.updateById(sysDict);
    }

    @Override
    public void deleteAlone(String id) {
        List<String> idList = new ArrayList<String>();
        SysDict sysDict = this.getById(id);
        if (sysDict == null) {
            throw new RunningException("未找到对应实体");
        } else {
            //逻辑删除子级字典
            idList.add(id);
            this.checkChildrenExists(id, idList);
            idList.forEach(currId -> {
                SysDict sysDict1 = new SysDict();
                sysDict1.setDelFlag(CommonConstant.DEL_FLAG_1);
                this.update(sysDict1, new LambdaQueryWrapper<SysDict>()
                        .eq(SysDict::getSysDictId, currId)
                );
            });
        }
    }

    @Override
    public void deleteBatch(String ids) {
        if (ids == null || "".equals(ids.trim())) {
            throw new ParameterException("参数不识别！");
        } else {
            List<String> list = Arrays.asList(ids.split(","));
            list.forEach(this::deleteAlone);
        }
    }

    @Override
    public void create(SysDict sysDict) {
        if (sysDict != null) {
            sysDict.setCreateUserId(daoApi.getCurrentUserId());
            if (StringUtils.isBlank(sysDict.getParentId())) {
                sysDict.setParentId("#");
            }
            this.save(sysDict);
        }
    }

    /**
     * 查询子级字典  ====== deleteAlone 子方法 =======
     *
     * @param id     字典ID
     * @param idList idList
     */
    private void checkChildrenExists(String id, List<String> idList) {
        List<SysDict> dictList = this.list(new LambdaQueryWrapper<SysDict>()
                .eq(SysDict::getParentId, id)
                .eq(SysDict::getDelFlag, CommonConstant.DEL_FLAG_0)
        );
        if (dictList != null && dictList.size() > 0) {
            for (SysDict dict : dictList) {
                idList.add(dict.getSysDictId());
                this.checkChildrenExists(dict.getSysDictId(), idList);
            }
        }
    }

    //  =============== 核心方法 START ===============
    // private final static int MODE_KEY_VALUE = 0;
    // private final static int MODE_VALUE_KEY = 1;
    //
    // /**
    //  * 核心方法，根据字典路径读取字典
    //  *
    //  * @param sKey : 字典路径，分隔符">"
    //  * @param mode : 字典模式
    //  * @return 字典
    //  */
    // private LinkedHashMap<String, Object> get(String sKey, int mode) {
    //     // 字典路径检查
    //     Object[] keys = sKey.replaceAll(" ", "").split(">");
    //     if (keys.length <= 0) {
    //         throw new RunningException("字典路径不能为空，禁止读取根字典信息！");
    //     }
    //     SysDict sysDict = this.baseMapper.selectOne(new LambdaQueryWrapper<SysDict>()
    //         .eq(SysDict::getName,keys[0])
    //         .eq(SysDict::getParentId,"root")
    //         .eq(SysDict::getDelFlag,0)
    //     );
    //     if(ObjectUtil.isNull(sysDict)){
    //         throw new RunningException("字典不存在或字典路径格式有误！");
    //     }
    //
    //
    //
    //     List<String> hqlsList = new ArrayList<String>();
    //     for (int i = 0; i < keys.length; i++) {
    //         if (i == 0) {
    //             hqlsList.add("select dic$" + i + ".id from Dictionary dic$" + i + " where dic$" + i + ".parentId = 'root' and dic$" + i + ".key = ?" + i);
    //         } else {
    //             hqlsList.add("select dic$" + i + ".id from Dictionary dic$" + i + " where dic$" + i + ".parentId = (tb$" + (i - 1) + ") and dic$" + i + ".key = ?" + i);
    //         }
    //     }
    //     String hqls = "";
    //     int size = hqlsList.size();
    //     if (size > 1) {
    //         for (int i = size - 1; i > 0; i--) {
    //             if (i == size - 1) {
    //                 hqls = hqlsList.get(i).replace("tb$" + (i - 1), hqlsList.get(i - 1));
    //             } else {
    //                 hqls = hqls.replace("tb$" + (i - 1), hqlsList.get(i - 1));
    //             }
    //         }
    //     } else if (size == 1) {
    //         hqls = hqlsList.get(0);
    //     }
    //     List<Dictionary> dicList = dao.queryHql("select dic from Dictionary dic where dic.parentId = (" + hqls + ") order by dic.sort asc", keys);
    //
    //     Dictionary dic = null;
    //     LinkedHashMap<String, Object> dics = new LinkedHashMap<String, Object>();
    //     switch (mode) {
    //         case MODE_KEY_VALUE:
    //             for (int i = 0; i < dicList.size(); i++) {
    //                 dic = dicList.get(i);
    //                 dics.put(dic.getKey(), dic.getValue());
    //             }
    //             break;
    //         case MODE_VALUE_KEY:
    //             for (int i = 0; i < dicList.size(); i++) {
    //                 dic = dicList.get(i);
    //                 dics.put(dic.getValue(), dic.getKey());
    //             }
    //             break;
    //     }
    //     return dics;
    // }
    //
    // /**
    //  * 读取字典，返回字典集合
    //  *
    //  * @param sKey : 字典路径，分隔符">"
    //  * @param mode : 字典模式
    //  * @return LinkedHashMap&gt;String, Object&lt;
    //  */
    // public LinkedHashMap<String, Object> getDictionary(String sKey, int mode) {
    //     return this.get(sKey, mode);
    // }
    //
    // /**
    //  * 读取字典，返回字典字符串
    //  *
    //  * @param sKey : 字典路径，分隔符">"
    //  * @param mode : 字典模式
    //  * @return String
    //  */
    // public String getDictionaryString(String sKey, int mode) {
    //     return JSONObject.obj2json(this.get(sKey, mode));
    // }
    //
    // /**
    //  * 读取字典(Key-Value)
    //  *
    //  * @param sKey : 字典路径，分隔符">"
    //  * @return LinkedHashMap&gt;String, Object&lt;
    //  */
    // public LinkedHashMap<String, Object> getKeyValue(String sKey) {
    //     return this.getDictionary(sKey, MODE_KEY_VALUE);
    // }
    //
    // /**
    //  * 读取字典(Key-Value)
    //  *
    //  * @param sKey : 字典路径，分隔符">"
    //  * @return String
    //  */
    // public String getKeyValueString(String sKey) {
    //     return this.getDictionaryString(sKey, MODE_KEY_VALUE);
    // }
    //
    // /**
    //  * 读取字典(Value-Key)
    //  *
    //  * @param sKey : 字典路径，分隔符">"
    //  * @return LinkedHashMap&gt;String, Object&lt;
    //  */
    // public LinkedHashMap<String, Object> getValueKey(String sKey) {
    //     return this.getDictionary(sKey, MODE_VALUE_KEY);
    // }
    //
    // /**
    //  * 读取字典(Value-Key)
    //  *
    //  * @param sKey : 字典路径，分隔符">"
    //  * @return String
    //  */
    // public String getValueKeyString(String sKey) {
    //     return this.getDictionaryString(sKey, MODE_VALUE_KEY);
    // }
    //
    // /**
    //  * 根据key查询该字典及所有的子节点
    //  *
    //  * @param sKey
    //  * @return
    //  */
    // public HashMap<String, Object> findAllDictionaryByKey(String sKey) {
    //
    //     //字典路径检查
    //     Object[] keys = sKey.replaceAll(" ", "").split(">");
    //     if (keys.length <= 0) {
    //         System.err.println("错误：字典路径不能为空，禁止读取根字典信息！");
    //     }
    //
    //     List<String> hqlsList = new ArrayList<String>();
    //     for (int i = 0; i < keys.length; i++) {
    //         if (i == 0) {
    //             hqlsList.add("select dic$" + i + ".id from Dictionary dic$" + i + " where dic$" + i + ".parentId='root' and dic$" + i + ".key=?0");
    //         } else {
    //             hqlsList.add("select dic$" + i + ".id from Dictionary dic$" + i + " where dic$" + i + ".parentId=(tb$" + (i - 1) + ") and dic$" + i + ".key=?" + i);
    //         }
    //     }
    //
    //     String hqls = "";
    //     int size = hqlsList.size();
    //     if (size > 1) {
    //         for (int i = size - 1; i > 0; i--) {
    //             if (i == size - 1) {
    //                 hqls = hqlsList.get(i).replace("tb$" + (i - 1), hqlsList.get(i - 1));
    //             } else {
    //                 hqls = hqls.replace("tb$" + (i - 1), hqlsList.get(i - 1));
    //             }
    //         }
    //     } else if (size == 1) {
    //         hqls = hqlsList.get(0);
    //     }
    //
    //     Dictionary dic = dao.queryHqlRow("select dic from Dictionary dic where dic.id = (" + hqls + ") order by dic.sort asc", keys);
    //
    //     String currentId = dic.getId();
    //     String filterSQL = "select t.id, t.parentId, t.keyword, t.value,t.sort from table@children t where t.id != '" + currentId + "'";
    //     List<HashMap<String, Object>> dics = CommonUtil.FindChildren(dao, "SYS_Dictionary", "parentId", currentId, filterSQL);
    //
    //     HashMap<String, Object> pNode = new HashMap<String, Object>();
    //     pNode.put("id", currentId);
    //     pNode.put("text", dic.getKey());
    //     pNode.put("value", dic.getValue());
    //     return assembly(pNode, dics);
    // }
    //
    // // 递归组装数据
    // public HashMap<String, Object> assembly(HashMap<String, Object> pNode, List<HashMap<String, Object>> cNodes) {
    //     List<HashMap<String, Object>> children = new ArrayList<HashMap<String, Object>>();
    //     HashMap<String, Object> node;
    //     for (int i = 0; i < cNodes.size(); i++) {
    //         if (pNode.get("id").equals(cNodes.get(i).get("parentid"))) {
    //             node = cNodes.remove(i);
    //             HashMap<String, Object> object = new HashMap<String, Object>();
    //             object.put("id", node.get("id"));
    //             object.put("text", node.get("keyword"));
    //             object.put("value", node.get("value"));
    //             object.put("sort", node.get("sort"));
    //             children.add(assembly(object, cNodes));
    //             i--;
    //         }
    //     }
    //     pNode.put("children", children);
    //     return pNode;
    // }

    //  =============== 核心方法 END ===============

}
