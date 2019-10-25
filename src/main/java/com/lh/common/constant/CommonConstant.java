package com.lh.common.constant;

import javafx.print.PrintSides;
import sun.net.httpserver.HttpsServerImpl;

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

    // =============== 系统日志 START ====================
    /**
     * 系统日志类型： 操作
     */
    public static final int  LOG_TYPE_0 = 0;

    /**
     * 系统日志类型： 登录 & 登出
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

    /**
     * 系统日志操作类型 增加
     */
    public final static int OPTYPE_CREATE = 0;
    /**
     * 系统日志操作类型 删除
     */
    public final static int OPTYPE_DELETE = 1;
    /**
     * 系统日志操作类型 修改
     */
    public final static int OPTYPE_UPDATE = 2;
    /**
     * 系统日志操作类型 读取
     */
    public final static int OPTYPE_READ   = 3;

    // ================== 凭证相关 START ===========================

    public static String PREFIX_USER_TOKEN  = "PREFIX_USER_TOKEN_";

    public static String X_ACCESS_TOKEN = "X-Access-Token";

    /** 登录用户拥有角色缓存KEY前缀 */
    public static String LOGIN_USER_CACHERULES_ROLE = "loginUser_cacheRules::Roles_";

    /** 登录用户拥有权限缓存KEY前缀 */
    public static String LOGIN_USER_CACHERULES_PERMISSION  = "loginUser_cacheRules::Permissions_";

    // ================== 编码格式 ===========================

    /** 文本编码 */
    public static String TEXT_CODE = "text/plain;charset=UTF-8";

    /** JSON编码 */
    public static String JSON_CODE = "application/json;charset=UTF-8";

    /** XML编码 */
    public static String XML_CODE = "text/xml;charset=UTF-8";


    // ================== 状态码 ===========================

    /**
     * 未删除
     */
    public static final Integer DEL_FLAG_0 = 0 ;

    /**
     * 已删除
     */
    public static final Integer DEL_FLAG_1 = 1;
}
