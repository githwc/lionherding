package com.lh.common.response;

/**
 * 功能描述：
 *
 * <p>版权所有：</p>
 * 未经本公司许可，不得以任何方式复制或使用本程序任何部分
 *
 * @Company: LionHerding
 * @Author: 牧狮&&紫色年华
 * @Datetime: 2019-08-22 08:54
 * @Version: 1.0.0
 */
public class ResponseCode {

    /**
     * 成功
     */
    public static final int SUCCESS = 200;

    /**
     * 未授权
     */
    public static final int NO_ACCESS_EXCEPTION = 401;

    /**
     * 未找到
     */
    public static final int NOT_FOUND_EXCEPTION = 404;

    /**
     * 拒绝请求
     */
    public static final int REFUSE_REQUEST = 403;

    /**
     * 系统错误
     */
    public static final int SYSTEM_EXCEPTION = 500;

    //=========================自定义 =====================

    /**
     * 登录名或密码错误
     */
    public static final int USERNAME_ERROR = 10000 ;

}
