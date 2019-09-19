package com.lh.modules.Test.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lh.modules.Test.entity.Tests;
import com.lh.modules.Test.mapper.TestMapper;
import com.lh.modules.Test.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 功能描述：
 * <p>版权所有：</p>
 * 未经本人许可，不得以任何方式复制或使用本程序任何部分
 *
 * @Company: 紫色年华
 * @Author: xieyc
 * @Datetime: 2019-06-05
 * @Version: 1.0.0
 */
@Service
public class TestServiceImpl implements TestService {

    @Autowired
    private TestMapper testMapper;

    @Override
    @Cacheable(cacheNames = {"testInfo"})
    public Tests getInfo() {
        return testMapper.getInfo();
    }

    @Override
    public void addTest() {
        //插入一条记录
        Tests test = new Tests();
        test.setAge(33);
        test.setEmail("1197798263@qq.com");
        test.setLastName("拉莫斯");
        test.setGender(1);
        test.setCreateTime(LocalDateTime.now());
        testMapper.insert(test);
        //获取当前数据在数据库中的主键值
        System.out.println("key:"+test.getId());
    }

    @Override
    public Tests getInfoById(String id) {//根据主键ID查询
        return testMapper.selectById(id);
    }

    @Override
    public void updateInfo() {
        Tests test = new Tests();
        test.setLastName("阿扎尔");
        //根据主键id修改数据
        testMapper.updateById(test);
        //修改年龄=11email模糊等于11977982的信息
        testMapper.update(test, new UpdateWrapper<Tests>()
                .eq("age","11")
                .like("email","11977982")
        );
    }

    @Override
    public void select() {
        //selectById()
        Tests test = testMapper.selectById("1");
        System.out.println("当前类:TestServiceImpl.select()===selectById()" + test);
        //selectOne
        test = testMapper.selectOne(new QueryWrapper<Tests>()
            .eq("age","1221")
            .like("email","119")
        );
        System.out.println("当前类:TestServiceImpl.select()===selectOne()" + test);
        //selectBatchIds
        List<String> list = new ArrayList<String>();
        list.add("1");
        list.add("2");
        list.add("3");
        list.add("4");
        List<Tests> testList = testMapper.selectBatchIds(list);
        System.out.println("当前类:TestServiceImpl.select()===selectBatchIds()" +testList);
        //selectByMap
        Map<String, Object> map = new HashMap<String,Object>();
        map.put("age","1221");
        map.put("last_name","贝尔");
        List<Tests> maptest = testMapper.selectByMap(map);
        System.out.println("当前类:TestServiceImpl.select()===selectByMap" +maptest );
        //selectPage()
        IPage<Tests> d = testMapper.selectPage(new Page<Tests>(1,2),null);
        System.out.println("当前类:TestServiceImpl.select()===selctpage" + d.getRecords());
    }

    @Override
    public void delete(){
        // deleteById()
        int result = testMapper.deleteById("1");
        System.out.println("当前类:TestServiceImpl.delete()===deleteById()" + result);
        //deleteBatchIds()
        List<String> list = new ArrayList<String>();
        list.add("6");
        list.add("7");
        int results = testMapper.deleteBatchIds(list);
        System.out.println("当前类:TestServiceImpl.delete()===deleteBatchIds" + results);
        // deleteByMap()
        Map<String,Object> map = new HashMap<String,Object>();
        map.put("id","8");
        int re = testMapper.deleteByMap(map);
        System.out.println("当前类:TestServiceImpl.delete()===deleteByMap" + re);
        int a =testMapper.delete(new QueryWrapper<Tests>()
            .eq("id","5")
        );
        System.out.println("当前类:TestServiceImpl.delete()===" + a);
    }

    @Override
    public void selectConditions() {
        //selectPage
        IPage<Tests> iPage = testMapper.selectPage(new Page<Tests>(1,3),new QueryWrapper<Tests>()
                .between("age","10","40")
                .eq("gender","2")
                .like("last_name","拉莫斯")
                .orderByDesc("age","gender")
        );
        System.out.println("当前类:TestServiceImpl.selectConditions()===" + iPage.getRecords());
        System.out.println("当前类======TestServiceImpl.selectConditions()"+"分页的相关信息");
        System.out.println("当前类======TestServiceImpl.selectConditions()==总条数"+iPage.getTotal());
        System.out.println("当前类======TestServiceImpl.selectConditions()==当前页码"+iPage.getCurrent());
        System.out.println("当前类======TestServiceImpl.selectConditions()==每页条数"+iPage.getSize());
        //selectList
        List<Tests> list = testMapper.selectList(new QueryWrapper<Tests>()
                .eq("gender","1")
                .eq("age","22")
                .or()
                .like("last_name","贝")
        );
        System.out.println("当前类:TestServiceImpl.selectConditions()===" + list);
    }

    @Override
    public void updateConditions() {
        Tests test = new Tests();
        test.setLastName("拉莫斯");
        test.setAge(33);
        test.getEmail();
        int result = testMapper.update(test,new UpdateWrapper<Tests>()
                .eq("age","11")
                .like("last_name","Li")
        );
        System.out.println("当前类:TestServiceImpl.updateConditions()===" + result);
    }

    @Override
    public void deleteConditions() {
       int result = testMapper.delete(new QueryWrapper<Tests>()
            .eq("last_name","尔")
            .eq("age","55")
        );
        System.out.println("当前类:TestServiceImpl.deleteConditions()===" + result);
    }

    @Override
    public void deleteAll() {
        testMapper.delete(null);
    }


}
