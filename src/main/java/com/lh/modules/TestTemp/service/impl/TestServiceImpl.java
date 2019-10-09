package com.lh.modules.TestTemp.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lh.common.config.webSocket.WebSocket;
import com.lh.modules.TestTemp.entity.Test;
import com.lh.modules.TestTemp.mapper.TestMapper;
import com.lh.modules.TestTemp.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
* 功能描述：
*
*  <p>版权所有：</p>
*  未经本人许可，不得以任何方式复制或使用本程序任何部分
*
* @Company: 紫色年华
* @Author xieyc
* @Date 2019-09-29
* @Version: 1.0.0
*
*/
@Service
@Transactional(rollbackFor = Exception.class)
public class TestServiceImpl extends ServiceImpl<TestMapper, Test> implements TestService {

    @Autowired
    private WebSocket webSocket;

    @Override
    public void updateAll() {
        Test test = new Test();
        this.baseMapper.update(test,new QueryWrapper<Test>(null));
    }

    @Override
    public void tempApi() {
        JSONObject obj = new JSONObject();
        obj.put("content", "欢迎来到新的世界！");//消息内容
        webSocket.sendOneMessage("99999", obj.toJSONString());
    }
}
