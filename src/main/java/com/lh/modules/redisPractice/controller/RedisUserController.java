package com.lh.modules.redisPractice.controller;

import com.lh.modules.redisPractice.entity.RedisUser;
import com.lh.modules.redisPractice.service.RedisUserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 *
 * 功能描述:redis Practice API 暴露接口
 *
 *  <p>版权所有：</p>
 *  未经本人许可，不得以任何方式复制或使用本程序任何部分
 *
 * @Company: 紫色年华
 * @Author xieyc
 * @Date 2020-01-19
 * @Version: 1.0.0
 *
 */
@RestController
@RequestMapping("/redisPractice/redisUser")
@Slf4j
public class RedisUserController {

    private final RedisUserService iRedisUserService;

    @Autowired
    public RedisUserController(RedisUserService iRedisUserService) {
        this.iRedisUserService = iRedisUserService;
    }

    @GetMapping("/queryAll")
    public List<RedisUser> queryAll(){
        return iRedisUserService.queryAll();
    }

    @GetMapping("/findUserById")
    public RedisUser findUserById(@RequestParam("redisUserId") String id){
        return iRedisUserService.findUserById(id);
    }

    @PutMapping("/updateUser")
    public void updateUser(@RequestParam("redisUserId") String id){
        iRedisUserService.updateUser(id);
    }

    @DeleteMapping("/deleteUserById")
    public void deleteUserById(@RequestParam("redisUserId") String id){
        iRedisUserService.deleteUserById(id);
    }
}
