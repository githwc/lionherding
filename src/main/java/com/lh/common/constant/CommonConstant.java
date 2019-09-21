package com.lh.common.constant;

/**
 * 功能描述：
 * <p>版权所有：</p>
 * 未经本人许可，不得以任何方式复制或使用本程序任何部分
 *
 * @Company: 紫色年华
 * @Author: xieyc
 * @Datetime: 2019-09-21
 * @Version: 1.0.0
 */
public class CommonConstant {

    /**
     * 当前在线用户
     */
    public static final String SESSION_CURRENT_USER = "SYSUSER";

    /**
     * 系统日志类型： 操作
     */
    public static final int  LOG_TYPE_0 = 0;

    /**
     * 系统日志类型： 登录
     */
    public static final int LOG_TYPE_1 = 1;

    /**
     * 系统日志类型： 定时
     */
    public static final int LOG_TYPE_2 = 2;

    /**
     * 系统日志操作结果 成功
     */
    public final static int OPSTATE_SUCCESS = 0;
    /**
     * 系统日志操作结果 失败
     */
    public final static int OPSTATE_FAILURE = 1;
}
