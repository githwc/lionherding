package com.lh.system.service;

import com.lh.system.entity.SysDept;
import com.baomidou.mybatisplus.extension.service.IService;
import com.lh.system.vo.DepartIdModel;
import com.lh.system.vo.SysDeptTree;

import java.util.List;

/**
 * 功能描述：
 *
 *  <p>版权所有：</p>
 *  未经本人许可，不得以任何方式复制或使用本程序任何部分
 *
 * @Company: LionHerding
 * @Author xieyc
 * @Date 2019-09-20
 * @Version: 1.0.0
 *
 */
public interface SysDeptService extends IService<SysDept> {

    List<SysDeptTree> queryTreeList();

    List<SysDeptTree> searhBy(String keyWord);

    List<DepartIdModel> queryDepartIdTreeList();
}
