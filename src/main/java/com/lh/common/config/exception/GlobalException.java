package com.lh.common.config.exception;

import com.lh.common.config.response.ResponseBean;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

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
@RestControllerAdvice
@Slf4j
public class GlobalException {

    /**
     * 拦截捕捉自定义异常
     *
     * @param e
     * @return
     */
    @ResponseStatus(HttpStatus.OK)
    @ExceptionHandler(ApiException.class)
    public Object handleAppException(ApiException e) {
        ResponseBean response = ResponseBean.error(e.getCode(), e.getMsg());
        log.error("error : {}", response.toJSONString(), e);
        return response;
    }

}
