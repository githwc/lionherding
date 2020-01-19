package com.lh.modules.redisPractice.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.lh.modules.redisPractice.entity.RedisUser;

import java.util.List;
import java.util.Map;

/**
 * 功能描述：
 *
 *  <p>版权所有：</p>
 *  未经本人许可，不得以任何方式复制或使用本程序任何部分
 *
 * @Company: LionHerding
 * @Author xieyc
 * @Date 2020-01-19
 * @Version: 1.0.0
 *
 */
public interface RedisUserService extends IService<RedisUser> {

    /**
     * 获取所有用户信息
     * @return
     */
    List<RedisUser> queryAll();

    /**
     * 根据用户ID 查询
     * @param id 用户ID
     * @return
     */
    RedisUser findUserById(String id);

    /**
     * 修改用户
     * @param id 用户ID
     * @return
     */
    void updateUser(String id);

    /**
     * 删除用户
     * @param id 用户ID
     * @return
     */
    void deleteUserById(String id);
}
