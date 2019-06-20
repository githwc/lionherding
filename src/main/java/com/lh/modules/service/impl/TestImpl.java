package com.lh.modules.service.impl;

import com.lh.modules.service.Test;
import com.lh.system.mapper.UserMapper;
import com.lh.system.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

/**
 * 功能描述：
 * <p>版权所有：</p>
 * 未经本人许可，不得以任何方式复制或使用本程序任何部分
 *
 * @Company: LionHerding
 * @Author: 牧狮&&紫色年华
 * @Datetime: 2019-06-05
 * @Version: 1.0.0
 */
@Service
public class TestImpl implements Test {

    @Autowired
    private UserMapper userMapper;

    @Override
    @Cacheable(cacheNames = {"user"})
    public User queryByid(String id) {
        return userMapper.selectByPrimaryKey(id);
    }
}
