package com.lh.common.config.exception;

import com.lh.common.utils.ResultUtils;
import lombok.Data;

/**
 * 功能描述：
 * <p>版权所有：</p>
 * 未经本人许可，不得以任何方式复制或使用本程序任何部分
 *
 * @Company: 紫色年华
 * @Author: xieyc
 * @Datetime: 2019-09-25
 * @Version: 1.0.0
 */
@Data
public class ApiException extends RuntimeException {
    /**
     * 错误码
     */
    private int code;
    /**
     * 错误信息
     */
    private String msg;

    public ApiException(int code, String msg, Object... params) {
        super(ResultUtils.formatMsg(msg, params));
        this.code = code;
        this.msg = ResultUtils.formatMsg(msg, params);
    }

}
