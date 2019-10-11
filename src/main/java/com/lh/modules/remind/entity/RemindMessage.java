package com.lh.modules.remind.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import java.time.LocalDateTime;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * 功能描述：
 *  <p>版权所有：</p>
 *  未经本人许可，不得以任何方式复制或使用本程序任何部分
 *
 * @Company: LionHerding
 * @Author xieyc
 * @Date 2019-10-08
 * @Version: 1.0.0
 *
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class RemindMessage implements Serializable {

    private static final long serialVersionUID = 1L;
    @TableId(value = "remind_message_id", type = IdType.UUID)
    private String remindMessageId;
    /**
     * 标题
     */
    private String title;
    /**
     * 内容
     */
    private String content;
    /**
     * 消息级别(0:INFO 1.WARNING 2.ERROR)
     */
    private String level;
    /**
     * 消息类型(0:通知公告 1: 系统消息)
     */
    private String type;
    /**
     * 模块类型
     */
    private String modelType;
    /**
     * 接收类型(USER:指定用户 ALL:全体用户 GROUP:组内用户)
     */
    private String receiveType;
    /**
     * 发布状态(0未发布 1.已发布 2.已撤销)
     */
    private String sendState;
    /**
     * 发布时间
     */
    private LocalDateTime sendTime;
    /**
     * 关联记录的标识
     */
    private String rid;
    /**
     * 发布人ID
     */
    private String createUserId;
    /**
     * 发布人
     */
    private String createUser;
    /**
     * 创建时间
     */
    private LocalDateTime createTime;


    /////////////////////////////// 非表字段 ///////////////////////////////

    /**
     * 消息接收表标识
     */
    @TableField(exist = false)
    private String userId;

    /**
     * 消息接收表标识
     */
    @TableField(exist = false)
    private String remindMessageReceiveId;

}
