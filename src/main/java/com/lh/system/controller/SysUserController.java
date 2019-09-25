package com.lh.system.controller;

import com.alibaba.fastjson.JSONObject;
import com.lh.common.config.exception.userException.UserLoginNameException;
import com.lh.common.config.exception.userException.UserPasswordException;
import com.lh.common.constant.CommonConstant;
import com.lh.common.utils.EncoderUtil;
import com.lh.common.utils.JwtUtil;
import com.lh.common.utils.RedisUtil;
import com.lh.system.entity.SysDepart;
import com.lh.system.entity.SysUser;
import com.lh.system.service.SysLogService;
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
 * 功能描述：系统用户 Controller
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

    @Autowired
    public SysLogService sysLogService;

    @PostMapping("/login")
    @ApiOperation(value = "用户登录",notes = "用户登录")
    public JSONObject login(@RequestBody SysUser sysUser){
        String username = sysUser.getLoginName();
        String password = sysUser.getPassword();
        sysUser = iSysUserService.getUserByName(username);
        if(sysUser==null) {
            sysLogService.addLog("登录失败，用户名:"+username+"不存在！", CommonConstant.LOG_TYPE_1, 3);
            throw new UserLoginNameException("该用户不存在！");
        }else {
            // 密码验证
            String userpassword = EncoderUtil.encrypt(username, password, sysUser.getLoginName());
            String syspassword = sysUser.getPassword();
            // if(!syspassword.equals(userpassword)) {
            //     sysLogService.addLog("登录失败，用户:"+username+"密码输入错误！", CommonConstant.LOG_TYPE_1, 3);
            //     throw new UserPasswordException("密码错误！");
            // }
            JSONObject jsonObject = new JSONObject();
            //生成token
            String token = JwtUtil.sign(username, syspassword);
            RedisUtil.set(CommonConstant.PREFIX_USER_TOKEN + token, token);
            //设置超时时间
            RedisUtil.expire(CommonConstant.PREFIX_USER_TOKEN + token, JwtUtil.EXPIRE_TIME/1000);
            jsonObject.put("token", token);
            jsonObject.put("userInfo", sysUser);

            sysLogService.addLog("用户名: "+username+",登录成功！", CommonConstant.LOG_TYPE_1, 3);
            return jsonObject;
        }
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
