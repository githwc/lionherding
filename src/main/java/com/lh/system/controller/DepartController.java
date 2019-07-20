package com.lh.system.controller;

import com.lh.system.entity.Depart;
import com.lh.system.log.SystemLogService;
import com.lh.system.log.WriteLog;
import com.lh.system.service.DepartService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 *
 * 功能描述：
 *
 *  <p>版权所有：</p>
 *  未经本人许可，不得以任何方式复制或使用本程序任何部分
 *
 * @Company: LionHerding
 * @Author 牧狮&&紫色年华
 * @Date 2019-07-04
 * @Version: 1.0.0
 *
 */
@RestController
@RequestMapping("/depart")
@Api(tags = "部门相关")
public class DepartController  {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    public DepartService iDepartService;

    /**
     * @Description:根据id查询
     * @param:
     * @return:
     */
    @GetMapping("/getById")
    @WriteLog(mName = "根据主键ID查询", optype = SystemLogService.OPTYPE_READ)
    @ApiOperation(value = "根据主键ID查询",  notes = "根据主键ID查询")
    public Depart getById(@ApiParam(required = true, name = "id",value = "主键ID")@RequestParam("id") String id){
        return null;
    }

    /**
     * @Description:根据id删除
     * @param:
     * @return:
     */
    @GetMapping("/deleteById")
    @WriteLog(mName = "根据主键ID删除", optype = SystemLogService.OPTYPE_DELETE)
    @ApiOperation(value = "根据主键ID删除",  notes = "根据主键ID删除")
    public int delete(@ApiParam(required = true, name = "id",value = "主键ID")@RequestParam("id") String id){
        return 0;
    }

    /**
     * @Description:保存和修改公用的
     * @param Depart  传递的实体
     * @return  0 失败  1 成功
     */
    @PostMapping("/sysDepartSave")
    @WriteLog(mName = "保存和修改公用API", optype = SystemLogService.OPTYPE_CREATE)
    @ApiOperation(value = "保存和修改公用API", notes = "保存和修改公用API")
    public int sysDepartSave(Depart sysDepart) {
        int count = 0;
        try {
            count = iDepartService.insertOrUpdate(sysDepart) ? 1 : 0;
        } catch (Exception e) {
            logger.error("sysDepartSave -=- {}",e.toString());
        }
        return count;
    }

}
