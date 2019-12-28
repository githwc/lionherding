package com.lh.modules.TestTemp.controller;

import com.lh.modules.TestTemp.entity.Test;
import com.lh.modules.TestTemp.service.TestService;
import com.lh.system.entity.SysUser;
import lombok.extern.slf4j.Slf4j;
import org.jeecgframework.poi.excel.def.NormalExcelConstants;
import org.jeecgframework.poi.excel.entity.ExportParams;
import org.jeecgframework.poi.excel.view.JeecgEntityExcelView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * 功能描述：测试Excel 导入导出
 * <p>版权所有：</p>
 * 未经本人许可，不得以任何方式复制或使用本程序任何部分
 *
 * @Company: 紫色年华
 * @Author: xieyc
 * @Datetime: 2019-10-13
 * @Version: 1.0.0
 */
@Slf4j
@RestController
@RequestMapping("/test/excel")
public class TestExcel {

    private final TestService testService;

    @Autowired
    public TestExcel(TestService testService) {
        this.testService = testService;
    }

    /**
     * 单表导出
     * @param sysUser
     * @param request
     * @return
     */
    @RequestMapping(value = "/exportXls")
    public ModelAndView exportXls(SysUser sysUser, HttpServletRequest request) {
        // Step.1 组装查询条件
        // QueryWrapper<SysUser> queryWrapper = QueryGenerator.initQueryWrapper(sysUser, request.getParameterMap());
        //Step.2 AutoPoi 导出Excel
        ModelAndView mv = new ModelAndView(new JeecgEntityExcelView());
        List<Test> pageList = testService.list(null);

        //导出文件名称
        mv.addObject(NormalExcelConstants.FILE_NAME, "用户列表");
        mv.addObject(NormalExcelConstants.CLASS, Test.class);
        ExportParams params = new ExportParams("用户列表数据", "导出人:C罗", "导出信息");
        params.setAddIndex(true);
        mv.addObject(NormalExcelConstants.PARAMS,params);
        mv.addObject(NormalExcelConstants.DATA_LIST, pageList);
        return mv;
    }



}
