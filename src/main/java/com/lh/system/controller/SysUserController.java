package com.lh.system.controller;

import com.alibaba.fastjson.JSONObject;
import com.lh.common.constant.CommonConstant;
import com.lh.system.entity.SysDepart;
import com.lh.system.entity.SysUser;
import com.lh.system.service.SysUserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @Autowired
    public SysUserService iSysUserService;

    @PostMapping("/login")
    @ApiOperation(value = "用户登录",notes = "用户登录")
    public SysUser login(@RequestBody SysUser sysUser){
        // Result<JSONObject> result = new Result<JSONObject>();
        // String username = sysUser.getLoginName();
        // String password = sysUser.getPassword();
        // sysUser = iSysUserService.getUserByName(username);
        // if(sysUser==null) {
        //     result.error500("该用户不存在");
        //     sysBaseAPI.addLog("登录失败，用户名:"+username+"不存在！", CommonConstant.LOG_TYPE_1, null);
        //     return result;
        // }else {
        //     //密码验证
        //     String userpassword = PasswordUtil.encrypt(username, password, sysUser.getSalt());
        //     String syspassword = sysUser.getPassword();
        //     if(!syspassword.equals(userpassword)) {
        //         result.error500("用户名或密码错误");
        //         return result;
        //     }
        //     //生成token
        //     String token = JwtUtil.sign(username, syspassword);
        //     redisUtil.set(CommonConstant.PREFIX_USER_TOKEN + token, token);
        //     //设置超时时间
        //     redisUtil.expire(CommonConstant.PREFIX_USER_TOKEN + token, JwtUtil.EXPIRE_TIME/1000);
        //
        //     //获取用户部门信息
        //     JSONObject obj = new JSONObject();
        //     List<SysDepart> departs = sysDepartService.queryUserDeparts(sysUser.getId());
        //     obj.put("departs",departs);
        //     if(departs==null || departs.size()==0) {
        //         obj.put("multi_depart",0);
        //     }else if(departs.size()==1){
        //         sysUserService.updateUserDepart(username, departs.get(0).getOrgCode());
        //         obj.put("multi_depart",1);
        //     }else {
        //         obj.put("multi_depart",2);
        //     }
        //     obj.put("token", token);
        //     obj.put("userInfo", sysUser);
        //     result.setResult(obj);
        //     result.success("登录成功");
        //     sysBaseAPI.addLog("用户名: "+username+",登录成功！", CommonConstant.LOG_TYPE_1, null);
        // }
        // return result;
        return null;
    }
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
           log.error("sysUserSave -=- {}",e.toString());
       }
       return count;
    }

}
