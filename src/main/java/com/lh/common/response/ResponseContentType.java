package com.lh.common.response;

/**
 * 功能描述：编码类型
 *
 * <p>版权所有：</p>
 * 未经本公司许可，不得以任何方式复制或使用本程序任何部分
 *
 * @Company: LionHerding
 * @Author: 牧狮&&紫色年华
 * @Datetime: 2019-08-22 10:42
 * @Version: 1.0.0
 */
public enum ResponseContentType {

    JSON("application/json;charset=UTF-8"),
    TEXT("text/plain;charset=UTF-8");

    private String value;

    ResponseContentType(String value) {
        this.value = value;
    }

    public String getValue() {
        return this.value;
    }

}
