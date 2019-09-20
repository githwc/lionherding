package com.lh.system.controller;

import com.lh.system.entity.SysUser;
import com.lh.system.service.SysUserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
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
 * @Company: 紫色年华
 * @Author xieyc
 * @Date 2019-09-19
 * @Version: 1.0.0
 *
 */
@RestController
@RequestMapping("/sysUser")
@Slf4j
@Api(tags="系统用户")
public class SysUserController {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    public SysUserService iSysUserService;

    /**
    * @Description:
    * @param:
    * @return:
    */
    @GetMapping("/{id}")
    @ApiOperation(value = "根据主键ID查询",  notes = "根据主键ID查询")
    public SysUser getSysUserById(@ApiParam(required = true, name = "id",value = "主键ID")@PathVariable("id") String id){
        return null;
    }

    /**
    * @Description:
    * @param:
    * @return:
    */
    @DeleteMapping("/{id}")
    @ApiOperation(value = "根据主键ID删除",  notes = "根据主键ID删除")
    public int delete(@ApiParam(required = true, name = "id",value = "主键ID")@PathVariable("id") String id){
       return 0;
    }

    /**
    * @Description:
    * @param
    * @return  0 失败  1 成功
    */
    @PostMapping("/createAndUpdate")
    @ApiOperation(value = "保存和修改公用API", notes = "保存和修改公用API")
    public int createAndUpdate(SysUser sysUser) {
       int count = 0;
       try {
           count = iSysUserService.insertOrUpdate(sysUser) ? 1 : 0;
       } catch (Exception e) {
           logger.error("sysUserSave -=- {}",e.toString());
       }
       return count;
    }

}
