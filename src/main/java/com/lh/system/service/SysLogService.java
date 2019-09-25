package com.lh.system.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.lh.system.entity.SysLog;

/**
 * 功能描述：
 *
 *  <p>版权所有：</p>
 *  未经本人许可，不得以任何方式复制或使用本程序任何部分
 *
 * @Company: 紫色年华
 * @Author xieyc
 * @Date 2019-09-21
 * @Version: 1.0.0
 *
 */
public interface SysLogService extends IService<SysLog> {

    /**
     * 日志添加
     * @param LogContent 内容
     * @param logType  日志类型(0.操作日志 1.登录日志 2.定时任务）
     * @param opType 操作类型(0:增 1：删  2：改 3：查)
     */
    void addLog(String LogContent, Integer logType, Integer opType);

}