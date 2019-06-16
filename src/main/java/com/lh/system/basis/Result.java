package com.lh.system.basis;

import lombok.Data;

/**
 * 功能描述：接口统一返回信息
 *  快速为JavaBean赋值,得到JSON格式结果
 *  {"code":"500201","message":"登录失败，用户名或密码错误，请重新输入","data":"......"}
 *
 * <p>版权所有：</p>
 * 未经本公司许可，不得以任何方式复制或使用本程序任何部分
 *
 * @Company: LionHerding
 * @Author: 牧狮&&紫色年华
 * @Datetime: 2019-06-15 09:46
 * @Version: 1.0.0
 */
@Data
public class Result<T> {

    /**
     *  返回码:200/500
     */
    private Integer code;

    /**
     * 返回信息:操作成功/失败
     */
    private String message;

    /**
     * 返回数据
     */
    private T data;

    //============Method============

    /**
     * 返回错误
     * @param <T>
     * @return
     */
    public static <T> Result<T> fail(){
        Result<T> result = new Result<T>();
        result.setResultCode(ResultCode.FAIL);
        return result;
    }

    /**
     * 返回带信息的错误
     * @param data
     * @param <T>
     * @return
     */
    public static <T> Result<T> fail(T data){
        Result<T> result = new Result<T>();
        result.setResultCode(ResultCode.FAIL);
        result.setData(data);
        return result;
    }

    /**
     * 返回特定错误
     * @param resultCode
     * @param <T>
     * @return
     */
    public static <T> Result<T> fail(ResultCode resultCode){
        Result<T> result = new Result<T>();
        result.setResultCode(resultCode);
        return result;
    }

    /**
     * 返回带信息的特定错误
     * @param resultCode
     * @param data
     * @param <T>
     * @return
     */
    public static <T> Result<T> fail(ResultCode resultCode,T data){
        Result<T> result = new Result<T>();
        result.setResultCode(resultCode);
        result.setData(data);
        return result;
    }

    /**
     * 返回成功
     * @param <T>
     * @return
     */
    public static <T> Result<T> success(){
        Result<T> result = new Result<T>();
        result.setResultCode(ResultCode.SUCCESS);
        return result;
    }

    /**
     * 返回带信息的成功
     * @param data
     * @param <T>
     * @return
     */
    public static <T> Result<T> success(T data){
        Result<T> result = new Result<T>();
        result.setResultCode(ResultCode.SUCCESS);
        result.setData(data);
        return result;
    }

    public void setResultCode(ResultCode resultCode){
        this.code = resultCode.getCode();
        this.message = resultCode.getMessage();
    }
}
