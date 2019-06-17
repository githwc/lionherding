package com.lh.system.basis;

/**
 * 功能描述：返回码
 *  使用枚举类型定义静态错误信息，
 *      约定200为成功，500为失败。5002**定义用户相关业务错误信息
 *
 * <p>版权所有：</p>
 * 未经本公司许可，不得以任何方式复制或使用本程序任何部分
 *
 * @Company: LionHerding
 * @Author: 牧狮&&紫色年华
 * @Datetime: 2019-06-15 09:55
 * @Version: 1.0.0
 */
public enum ResultCode {

    /**
     * 全局成功
     */
    SUCCESS(200,"成功"),

    /**
     * 全局失败
     */
    FAIL(500,"失败"),

    /**
     * 用户相关
     * User
     */
    USER_LOGIN_ERROR(500201,"登录失败，用户名或密码错误，请重新输入"),
    USER_HAS_EXISTED(500202,"用户已存在，请尝试其他用户名"),
    USER_NOT_LOGIN(500203,"用户未登录或登录已失效，请重新登录"),
    USER_PICTURE_VALIDATE(500204,"验证码错误，请修正！"),
    ;

    private Integer code;

    private String message;

    ResultCode(Integer code,String message){
        this.code = code;
        this.message = message;
    }


    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
