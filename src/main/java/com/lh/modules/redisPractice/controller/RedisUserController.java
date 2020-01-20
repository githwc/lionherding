package com.lh.modules.redisPractice.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lh.modules.redisPractice.entity.RedisUser;
import com.lh.modules.redisPractice.model.RedisUserQuery;
import com.lh.modules.redisPractice.service.RedisUserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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

    // ========= Redis Cache 1.0 START ===========
    @GetMapping("/userPage")
    public Page<RedisUser> userPage(Page<RedisUser> page, RedisUserQuery query){
        System.out.println(iRedisUserService.userPage(page,query));
        return iRedisUserService.userPage(page,query);
    }

    @GetMapping("/findUserById")
    public RedisUser findUserById(@RequestParam("redisUserId") String id){
        return iRedisUserService.findUserById(id);
    }

    @PostMapping("/add")
    public void add(@RequestBody RedisUser redisUser){
        iRedisUserService.add(redisUser);
    }

    @PutMapping("/updateUser")
    public void updateUser(@RequestBody RedisUser redisUser){
        iRedisUserService.updateUser(redisUser);
    }

    @DeleteMapping("/deleteUserById")
    public void deleteUserById(@RequestParam("redisUserId") String id){
        iRedisUserService.deleteUserById(id);
    }

    // ========= Redis Cache 2.0 (注解缓存) START ===========


}
