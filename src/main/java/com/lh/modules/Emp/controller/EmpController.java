package com.lh.modules.Emp.controller;

import com.lh.modules.region.entity.Region;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author 牧狮&&紫色年华
 * @date 2019-06-28
 */
@RestController
@RequestMapping("/Emp/emp")
@Api(tags = "员工相关")
public class EmpController {

    @GetMapping("/{id}")
    @ApiOperation(value = "根据主键ID查询",  notes = "根据主键ID查询")
    public Region getRegionById(@ApiParam(required = true, name = "id",value = "主键ID")@PathVariable("id") String id){
        return null;
    }
}
