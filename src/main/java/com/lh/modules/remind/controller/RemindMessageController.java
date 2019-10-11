package com.lh.modules.remind.controller;

import com.alibaba.fastjson.JSONObject;
import com.lh.modules.remind.service.RemindMessageService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * 功能描述：消息提醒
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
@RestController
@RequestMapping("/remind/remind-message")
@Slf4j
public class RemindMessageController {

    @Autowired
    public RemindMessageService iRemindMessageService;

    /**
     * 发送消息给指定人
     *
     *  userId: 接收人
     *  content:消息内容
     *
     *  level: 消息级别(0:INFO 1.WARNING 2.ERROR)
     *  type: 消息类型(0:通知公告 1: 系统消息)
     *  modelType 模块类型
     *  rid: 关联ID
     * @param jsonObject
     */
    @PostMapping("/sendUser")
    public void sendUser(@RequestBody JSONObject jsonObject){
        iRemindMessageService.sendUser(jsonObject.getString("userId"),
                jsonObject.getString("content"),
                jsonObject.getString("level"),
                jsonObject.getString("type"),
                jsonObject.getString("modelType"),
                jsonObject.getString("rid")
        );
    }


    /**
     * 群发消息
     *
     *  content:消息内容
     *
     *  level: 消息级别(0:INFO 1.WARNING 2.ERROR)
     *  type: 消息类型(0:通知公告 1: 系统消息)
     *  modelType 模块类型
     *  rid: 关联ID
     * @param jsonObject
     */
    @PostMapping("/sendAll")
    public void sendAll(@RequestBody JSONObject jsonObject){
        iRemindMessageService.sendAllUser(jsonObject.getString("content"),
                jsonObject.getString("level"),
                jsonObject.getString("type"),
                jsonObject.getString("modelType"),
                jsonObject.getString("rid")
        );
    }
}
