package com.lh.system.basis.response;

/**
 * 功能描述：
 *
 * <p>版权所有：</p>
 * 未经本公司许可，不得以任何方式复制或使用本程序任何部分
 *
 * @Company: 烟台海涛网络科技有限公司
 * @Author: 牧狮&&紫色年华
 * @Datetime: 2019-07-23 09:24
 * @Version: 1.0.0
 */
public class RCode {

    /**
     * 成功
     */
    public static final int SUCCESS = 0;

    /**
     * 系统错误
     */
    public static final int SYSTEM_EXCEPTION = 10100;
    /**
     * 未知错误
     */
    public static final int UNKNOWN_EXCEPTION = 10101;
    /**
     * 网络超时
     */
    public static final int REQUEST_TIMEOUT_EXCEPTION = 10102;
    /**
     * 未找到异常，通常指404错误
     */
    public static final int NOT_FOUND_EXCEPTION = 10104;

    /**
     * 异常
     */
    public static final int EXCEPTION = 10200;
    /**
     * 空指针
     */
    public static final int NULL_POINTER_EXCEPTION = 10201;
    /**
     * 文件不存在
     */
    public static final int FILE_NOT_FOUND_EXCEPTION = 10202;
    /**
     * IO异常
     */
    public static final int IO_EXCEPTION = 10203;

    /**
     * 结果集错误
     */
    public static final int RESULT_EXCEPTION = 10300;
    /**
     * 结果集空指针错误
     */
    public static final int RESULT_NULL_POINTER_EXCEPTION = 10301;
    /**
     * 结果集格式错误
     */
    public static final int RESULT_FORMAR_EXCEPTION = 10302;
    /**
     * 结果集不存在错误
     */
    public static final int RESULT_NOT_FOUND_EXCEPTION = 10303;

    /**
     * 参数错误
     */
    public static final int PARAMETER_EXCEPTION = 10400;
    /**
     * 参数不存在
     */
    public static final int PARAMETER_NOT_FOUND_EXCEPTION = 10401;
    /**
     * 参数空指针
     */
    public static final int PARAMETER_NULL_POINTER_EXCEPTION = 10402;
    /**
     * 参数格式错误
     */
    public static final int PARAMETER_FORMAT_EXCEPTION = 10403;

    /**
     * 安全异常
     */
    public static final int SAFE_EXCEPTION = 10500;
    /**
     * 验签失败异常
     */
    public static final int CHECK_SIGN_FAIL_EXCEPTION = 10501;
    /**
     * 无权限异常
     */
    public static final int NO_ACCESS_EXCEPTION = 10502;

    /**
     * 文件异常
     */
    public static final int FILE_EXCEPTION = 10600;
    /**
     * 文件超出限制异常
     */
    public static final int FILE_SIZE_EXCEPTION = 10601;
    /**
     * 文件上传异常
     */
    public static final int FILE_UPLOAD_EXCEPTION = 10602;


    /**
     * 验证失败
     */
    public static final int VALIDATE_EXCEPTION = 20100;
    /**
     * 短信验证码错误
     */
    public static final int SMS_VALIDATE_CODE_EXCEPTION = 20101;
    /**
     * 图片验证码错误
     */
    public static final int IMAGE_VALIDATE_CODE_EXCEPTION = 20102;

    /**
     * 订单错误
     */
    public static final int ORDER_EXCEPTION = 20200;
    /**
     * 订单支付错误
     */
    public static final int ORDER_PAY_EXCEPTION = 20201;
    /**
     * 订单支付重复回调
     */
    public static final int ORDER_REPEAT_PAY_CALLBACK_EXCEPTION = 20202;
    /**
     * 订单不存在错误
     */
    public static final int ORDER_NOT_FOUND_EXCEPTION = 20203;

    /**
     * 用户错误
     */
    public static final int USER_EXCEPTION = 20300;
    /**
     * 用户未登录
     */
    public static final int USER_NO_LOGIN_EXCEPTION = 20301;
    /**
     * 用户不存在
     */
    public static final int USER_NOT_FOUND_EXCEPTION = 20302;
    /**
     * 用户密码错误
     */
    public static final int USER_PWD_ERROR_EXCEPTION = 20303;
    /**
     * 用户已禁用
     */
    public static final int USER_DISABLE_EXCEPTION = 20304;
    /**
     * 用户电话错误
     */
    public static final int USER_PHONE_ERROR_EXCEPTION = 20305;
    /**
     * 用户证件号码错误
     */
    public static final int USER_IDCARD_ERROR_EXCEPTION = 20306;
    /**
     * 用户登录名称已存在
     */
    public static final int USER_LOGINNAME_EXISTS_EXCEPTION = 20307;
    /**
     * 用户登录账号或密码错误
     */
    public static final int USER_LOGINNAME_OR_PWD_ERROR_EXCEPTION = 20310;
    /**
     * 用户身份证号码已存在异常
     */
    public static final int USER_IDCARD_EXIST_EXCEPTION = 20311;
    /**
     * 用户实名认证未认证异常
     */
    public static final int USER_REAL_AUTH_UNDEFINED_EXCEPTION = 20312;
    /**
     * 用户实名认证拒绝异常
     */
    public static final int USER_REAL_AUTH_REFUSED_EXCEPTION = 20313;
    /**
     * 用户实名认证等待审核异常
     */
    public static final int USER_REAL_AUTH_WAIT_CHECK_EXCEPTION = 20314;

    /**
     * 用户资金帐户异常
     */
    public static final int USER_ACCOUNT_EXCEPTION = 20400;
    /**
     * 用户资金账户不存在
     */
    public static final int USER_ACCOUNT_NOT_FOUND_EXCEPTION = 20401;
    /**
     * 用户资金账户已冻结
     */
    public static final int USER_ACCOUNT_FREEZE_EXCEPTION = 20402;
    /**
     * 用户资金账户已注销
     */
    public static final int USER_ACCOUNT_CANCEL_EXCEPTION = 20403;
    /**
     * 用户资金账号余额不足
     */
    public static final int USER_ACCOUNT_AMOUNT_EXCEPTION = 20404;
    /**
     * 用户资金账户可提现余额不足
     */
    public static final int USER_ACCOUNT_CASH_AMOUNT_EXCEPTION = 20405;
    /**
     * 用户资金账户不可提现余额不足
     */
    public static final int USER_ACCOUNT_UNCASH_AMOUNT_EXCEPTION = 20406;


    /**
     * 短信异常
     */
    public static final int SMS_EXCEPTION = 20500;
    /**
     * 短信发送频繁，请稍后再试
     */
    public static final int SMS_BUSINESS_LIMIT_EXCEPTION = 20501;
    /**
     * 短信账户余额不足
     */
    public static final int SMS_ACCOUNT_AMOUNT_EXCEPTION = 20502;
    /**
     * 短信已超过今日总条数
     */
    public static final int SMS_TODAY_TOTAL_NUM_LIMIT_EXCEPTION = 20503;
    /**
     * 该手机号今日已发送超量
     */
    public static final int SMS_TODAY_PHONE_NUM_LIMIT_EXCEPTION = 20504;
    /**
     * 该IP今日已发送超量
     */
    public static final int SMS_TODAY_IP_NUM_LIMIT_EXCEPTION = 20505;
}
