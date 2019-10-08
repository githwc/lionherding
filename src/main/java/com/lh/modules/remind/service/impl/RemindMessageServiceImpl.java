package com.lh.modules.remind.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lh.common.config.webSocket.WebSocket;
import com.lh.modules.remind.entity.RemindMessage;
import com.lh.modules.remind.mapper.RemindMessageMapper;
import com.lh.modules.remind.service.RemindMessageReceiveService;
import com.lh.modules.remind.service.RemindMessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

/**
* 功能描述：
*
*  <p>版权所有：</p>
*  未经本人许可，不得以任何方式复制或使用本程序任何部分
*
* @Company: 紫色年华
* @Author xieyc
* @Date 2019-10-08
* @Version: 1.0.0
*
*/
@Service
@Transactional(rollbackFor = Exception.class)
public class RemindMessageServiceImpl extends ServiceImpl<RemindMessageMapper, RemindMessage> implements RemindMessageService {

    @Autowired
    private WebSocket webSocket;

    @Autowired
    private RemindMessageReceiveService remindMessageReceiveService;

    @Override
    public void sendUser(String userId, String content,String level,String type,String modelType,String rid) {
        JSONObject obj = new JSONObject();
        obj.put("content", content);//消息内容
        webSocket.sendOneMessage(userId, obj.toJSONString());
        /*记录推送消息*/
        RemindMessage remindMessage = new RemindMessage();
        remindMessage.setCreateTime(LocalDateTime.now());
        remindMessage.setLevel(level);
        remindMessage.setTitle("单点定向推送");
        remindMessage.setContent(content);
        remindMessage.setReceiveType("USER");
        remindMessage.setType(type);
        remindMessage.setModelType(modelType);
        remindMessage.setRid(rid);
        // todo 判断推送成功失败
        remindMessage.setSendState("1");
        remindMessage.setSendTime(LocalDateTime.now());
        // todo 获取当前在线人信息
        remindMessage.setCreateUser("admin");
        remindMessage.setCreateUserId("admin");
        int result = this.baseMapper.insert(remindMessage);
        if(result > 0){
            remindMessageReceiveService.insertRecord(userId,remindMessage.getRemindMessageId());
        }
    }

    @Override
    public void sendAllUser(String content,String level,String type,String modelType,String rid) {
        JSONObject obj = new JSONObject();
        obj.put("content", content);//消息内容
        webSocket.sendAllMessage(obj.toJSONString());
        /*记录推送消息*/
        RemindMessage remindMessage = new RemindMessage();
        remindMessage.setCreateTime(LocalDateTime.now());
        remindMessage.setLevel(level);
        remindMessage.setTitle("群发推送");
        remindMessage.setContent(content);
        remindMessage.setReceiveType("ALL");
        remindMessage.setType(type);
        remindMessage.setModelType(modelType);
        remindMessage.setRid(rid);
        // todo 判断推送成功失败
        remindMessage.setSendState("1");
        remindMessage.setSendTime(LocalDateTime.now());
        // todo 获取当前在线人信息
        remindMessage.setCreateUser("admin");
        remindMessage.setCreateUserId("admin");
        this.baseMapper.insert(remindMessage);
        /**
         * 群发消息不记录接收人
         *  通过消息创建时间和用户创建时间判断用户是否查看可以查看此消息
         *
         * todo 存在逻辑缺陷： 此情况下如何判断群发消息中用户已读未读
         *  1.存入消息表中一个文本字段 记录已读人
         */
    }


}
