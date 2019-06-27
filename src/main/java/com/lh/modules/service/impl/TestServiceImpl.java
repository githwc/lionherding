package com.lh.modules.service.impl;

import com.lh.modules.mapper.TestMapper;
import com.lh.modules.model.Test;
import com.lh.modules.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

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
public class TestServiceImpl implements TestService {

    @Autowired
    private TestMapper testMapper;

    @Override
    @Cacheable(cacheNames = {"testInfo"})
    public Test getInfo() {
        return testMapper.getInfo();
    }

    @Override
    public void addTest() {
        Test test = new Test();
        test.setAge(11);
        test.setEmail("1197798263@qq.com");
        test.setLastName("贝尔");
        test.setGender(1);
        test.setCreateTime(LocalDateTime.now());
        testMapper.insert(test);
        //获取当前数据在数据库中的主键值
        System.out.println("key:"+test.getId());
    }

    @Override
    public Test getInfoById(String id) {
        return testMapper.selectById(id);
    }

    @Override
    public void updateInfo() {
        Test test = new Test();
        test.setId("1jfsidoj");
        test.setLastName("修改数据");
        testMapper.updateById(test);
    }


}
