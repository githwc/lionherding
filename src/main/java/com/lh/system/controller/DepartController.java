package com.lh.system.controller;

import com.lh.system.basis.BasisAction;
import com.lh.system.basis.Result;
import com.lh.system.entity.Depart;
import com.lh.system.service.DepartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 功能描述：
 * <p>
 * <p>版权所有：</p>
 * 未经本人许可，不得以任何方式复制或使用本程序任何部分
 *
 * @Company: LionHerding
 * @Author: 牧狮&&紫色年华
 * @Datetime: 2019-05-31 17:45
 */
@RestController
public class DepartController extends BasisAction {

    @Autowired
    private DepartService departService;

    /**
     * @Description:查询所有部门
     * @Date: 2019/6/18 11:09
     * @Param:
     * @Return:
     * @throws:
     */
    @RequestMapping("/departList")
    public Result<List<Depart>> departList(){
        return Result.success(departService.departList());
    }
}
