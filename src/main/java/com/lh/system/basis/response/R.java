package com.lh.system.basis.response;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import java.util.HashMap;

/**
 * 功能描述：
 *
 * <p>版权所有：</p>
 * 未经本公司许可，不得以任何方式复制或使用本程序任何部分
 *
 * @Company: 烟台海涛网络科技有限公司
 * @Author: 牧狮&&紫色年华
 * @Datetime: 2019-07-23 09:23
 * @Version: 1.0.0
 */
public class R extends HashMap<String, Object> {

    public R() {
        put("code", RCode.SUCCESS);
    }

    public static R success() {
        return new R();
    }

    public static R success(String msg, Object... params) {
        R error = new R();
        error.put("msg", RUtils.formatMsg(msg, params));
        return error;
    }

    public static R error(int code, String msg, Object... params) {
        R error = new R();
        error.put("code", code);
        error.put("msg", RUtils.formatMsg(msg, params));
        return error;
    }

    @Override
    public R put(String key, Object value) {
        super.put(key, value);
        return this;
    }

    public R data(Object data) {
        super.put("data", data);
        return this;
    }

    public R page(Object page) {
        super.put("page", page);
        return this;
    }

    public R userData(Object userData) {
        super.put("userData", userData);
        return this;
    }

    @Override
    public String toString() {
        return this.toJSONString();
    }

    public String toJSONString() {
        return JSON.toJSONString(this);
    }

    public JSONObject toJSONObject() {
        return JSON.parseObject(this.toJSONString());
    }
}
