package com.lh.modules.remind.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.lh.modules.remind.entity.RemindMessage;
import com.lh.modules.remind.entity.RemindMessageReceive;

import java.util.List;

/**
 * 功能描述：
 *
 *  <p>版权所有：</p>
 *  未经本人许可，不得以任何方式复制或使用本程序任何部分
 *
 * @Company: LionHerding
 * @Author xieyc
 * @Date 2019-10-08
 * @Version: 1.0.0
 *
 */
public interface RemindMessageService extends IService<RemindMessage> {

    /**
     * 发送消息给指定人
     *
     *  userId: 接收人
     *  content:消息内容
     *  level: 消息级别(0:INFO 1.WARNING 2.ERROR)
     *  type: 消息类型(0:通知公告 1: 系统消息)
     *  modelType 模块类型
     *  rid: 关联ID
     */
    void sendUser(String userId,String content,String level,String type,String modelType,String rid);

    /**
     * 群发消息
     *
     *  content:消息内容
     *  level: 消息级别(0:INFO 1.WARNING 2.ERROR)
     *  type: 消息类型(0:通知公告 1: 系统消息)
     *  modelType 模块类型
     *  rid: 关联ID
     * @param content
     */
    void sendAllUser(String content,String level,String type,String modelType,String rid);


    /**
     * 我未接受的消息
     * @param userId 用户ID
     * @return
     */
    List<RemindMessage> myNotReceiveMessages(String userId);

}
