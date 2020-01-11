package com.lh.system.service.impl;

import cn.hutool.core.util.ObjectUtil;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
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

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;

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

    private final static int MODE_KEY_VALUE = 0;
    private final static int MODE_VALUE_KEY = 1;

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
        this.get("图书借阅>订单状态",1);
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
            }else{
                QueryWrapper<SysDict> queryWrapper = new QueryWrapper<>();
                queryWrapper.eq("parent_id",sysDict.getParentId());
                queryWrapper.eq("del_flag",0);
                queryWrapper.and(wrapper-> wrapper.eq("name",sysDict.getName()).or().eq("value",sysDict.getValue()));
                List<SysDict> sysDicts = this.baseMapper.selectList(queryWrapper);
                if(ObjectUtil.isNotEmpty(sysDicts)){
                    throw new RuntimeException("存在重复字典项,请重新填写！");
                }
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

    /**
     * 核心方法，根据字典路径读取字典
     *
     * @param sKey : 字典路径，分隔符">"
     * @param mode : 字典模式
     * @return 字典
     */
    private LinkedHashMap<String, Object> get(String sKey, int mode) {
        // 字典路径检查
        Object[] keys = sKey.replaceAll(" ", "").split(">");
        if (keys.length <= 0) {
            throw new RunningException("字典路径不能为空，禁止读取根字典信息!");
        }
        if(keys.length != 2){
            throw new RunningException("字典路径格式有误！");
        }
        List<SysDict> dictList = this.baseMapper.getDictByRoute(keys[0],keys[1]);
        if(ObjectUtil.isNull(dictList)){
            throw new RunningException("字典不存在或字典路径格式有误！");
        }
        SysDict dic;
        LinkedHashMap<String, Object> dics = new LinkedHashMap<String, Object>();
        switch (mode) {
            case MODE_KEY_VALUE:
                for (int i = 0; i < dictList.size(); i++) {
                    dic = dictList.get(i);
                    dics.put(dic.getName(), dic.getValue());
                }
                break;
            case MODE_VALUE_KEY:
                for (int i = 0; i < dictList.size(); i++) {
                    dic = dictList.get(i);
                    dics.put(dic.getValue(), dic.getName());
                }
                break;
            default:
                throw new ParameterException("参数错误!");
        }
        return dics;
    }


    /**
     * 读取字典，返回字典集合
     *
     * @param sKey : 字典路径，分隔符">"
     * @param mode : 字典模式 0,1
     * @return LinkedHashMap&gt;String, Object&lt;
     */
    public LinkedHashMap<String, Object> getDict(String sKey, int mode) {
        return this.get(sKey, mode);
    }

    /**
     * 读取字典，返回字典字符串
     *
     * @param sKey : 字典路径，分隔符">"
     * @param mode : 字典模式
     * @return String
     */
    public String getDictString(String sKey, int mode) {
        return JSONObject.toJSONString(this.get(sKey, mode));
    }

    /**
     * 读取字典(Key-Value)
     *
     * @param sKey : 字典路径，分隔符">"
     * @return LinkedHashMap&gt;String, Object&lt;
     */
    public LinkedHashMap<String, Object> getKeyValue(String sKey) {
        return this.getDict(sKey, MODE_KEY_VALUE);
    }

    /**
     * 读取字典(Key-Value)
     *
     * @param sKey : 字典路径，分隔符">"
     * @return String
     */
    public String getKeyValueString(String sKey) {
        return this.getDictString(sKey, MODE_KEY_VALUE);
    }

    /**
     * 读取字典(Value-Key)
     *
     * @param sKey : 字典路径，分隔符">"
     * @return LinkedHashMap&gt;String, Object&lt;
     */
    public LinkedHashMap<String, Object> getValueKey(String sKey) {
        return this.getDict(sKey, MODE_VALUE_KEY);
    }

    /**
     * 读取字典(Value-Key)
     *
     * @param sKey : 字典路径，分隔符">"
     * @return String
     */
    public String getValueKeyString(String sKey) {
        return this.getDictString(sKey, MODE_VALUE_KEY);
    }

    //  =============== 核心方法 END ===============

    // TODO: 2020/1/11 查询存在问题 待后期处理
    /**
     * 核心方法，根据字典路径读取字典
     *  sql:根据字典路径(skey)锁定最后一级id,并将此sql作为参数传入到最终查询方法中
     * @param sKey : 字典路径，分隔符">"
     * @param mode : 字典模式
     * @return 字典
     */
    // private LinkedHashMap<String, Object> get(String sKey, int mode) {
    //     // 字典路径检查
    //     Object[] keys = sKey.replaceAll(" ", "").split(">");
    //     if (keys.length <= 0) {
    //         throw new RunningException("字典路径不能为空，禁止读取根字典信息！");
    //     }
    //     SysDict sysDict = this.baseMapper.selectOne(new LambdaQueryWrapper<SysDict>()
    //         .eq(SysDict::getName,keys[0])
    //         .eq(SysDict::getParentId,"#")
    //         .eq(SysDict::getDelFlag,0)
    //     );
    //     if(ObjectUtil.isNull(sysDict)){
    //         throw new RunningException("字典不存在或字典路径格式有误！");
    //     }
    //     List<String> hqlsList = new ArrayList<String>();
    //     for (int i = 0; i < keys.length; i++) {
    //         if (i == 0) {
    //             hqlsList.add("select dic$" + i + ".sys_dict_id from sys_dict dic$" + i + " where dic$" + i + ".parent_id = '#' and dic$" + i + ".name = '" +  keys[i] + "'");
    //         } else {
    //             hqlsList.add("select dic$" + i + ".sys_dict_id from sys_dict dic$" + i + " where dic$" + i + ".parent_id = (tb$" + (i - 1) + ") and dic$" + i + ".name = '" + keys[i] + "'");
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
    //     List<SysDict> dictList = this.baseMapper.getDict(hqls);
    //     SysDict dic;
    //     LinkedHashMap<String, Object> dics = new LinkedHashMap<String, Object>();
    //     switch (mode) {
    //         case MODE_KEY_VALUE:
    //             for (int i = 0; i < dictList.size(); i++) {
    //                 dic = dictList.get(i);
    //                 dics.put(dic.getName(), dic.getValue());
    //             }
    //             break;
    //         case MODE_VALUE_KEY:
    //             for (int i = 0; i < dictList.size(); i++) {
    //                 dic = dictList.get(i);
    //                 dics.put(dic.getValue(), dic.getName());
    //             }
    //             break;
    //             default:
    //                 throw new ParameterException("参数错误!");
    //     }
    //     return dics;
    // }
}
