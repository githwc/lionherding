package com.lh.modules.remind.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.lh.modules.remind.entity.RemindMessage;
import com.lh.modules.remind.entity.RemindMessageReceive;
import com.lh.modules.remind.mapper.RemindMessageReceiveMapper;
import com.lh.modules.remind.service.RemindMessageReceiveService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
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
* @Date 2019-10-08
* @Version: 1.0.0
*
*/
@Service
@Transactional(rollbackFor = Exception.class)
public class RemindMessageReceiveServiceImpl extends ServiceImpl<RemindMessageReceiveMapper, RemindMessageReceive> implements RemindMessageReceiveService {

    @Override
    public void insertRecord(String userId, String messageId) {
        RemindMessageReceive remindMessageReceive = new RemindMessageReceive();
        remindMessageReceive.setMessageId(messageId);
        remindMessageReceive.setUserId(userId);
        this.baseMapper.insert(remindMessageReceive);
    }

    @Override
    public void readMessage(String userId, String messageId) {
        RemindMessageReceive remindMessageReceive = new RemindMessageReceive();
        remindMessageReceive.setReadFlag("1");
        this.baseMapper.update(remindMessageReceive,new QueryWrapper<RemindMessageReceive>()
                .eq("user_id",userId)
                .eq("message_id",messageId)
        );
    }
}
