package com.lh.common.response;

import com.alibaba.fastjson.JSON;
import com.sun.deploy.net.HttpResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 功能描述：返回信息工具类
 *
 * <p>版权所有：</p>
 * 未经本公司许可，不得以任何方式复制或使用本程序任何部分
 *
 * @Company: LionHerding
 * @Author: 牧狮&&紫色年华
 * @Datetime: 2019-08-22 09:08
 * @Version: 1.0.0
 */
public class ResponseUtils {

    /**
     * 格式化信息
     * @param msg
     * @param params
     * @return
     */
    public static String formatMsg(String msg, Object... params) {
        int i = 0, j;
        StringBuilder content = new StringBuilder(msg.length() + 50);
        for (Object param : params) {
            j = msg.indexOf("{}", i);
            // 找到{}等待替换
            if (j != -1) {
                content.append(msg, i, j).append(param);
                i = j + 2;
            } else {
                break;
            }
        }
        content.append(msg, i, msg.length());
        return content.toString();
    }

    /**
     * 输出文本格式到客户端
     *
     * @param response
     * @param str      文本字符串
     */
    public static void outText(HttpServletResponse response, String str) {
        out(response, ResponseContentType.TEXT.getValue(), str);
    }

    /**
     * 输出json格式到客户端
     *
     * @param response
     * @param data     json格式字符串
     */
    public static void outJson(HttpServletResponse response, String data) {
        out(response, ResponseContentType.JSON.getValue(), data);
    }

    /**
     * 输出json格式到客户端
     *
     * @param response
     * @param data     待转换成json的对象
     */
    public static void outJson(HttpServletResponse response, Object data) {
        out(response, ResponseContentType.JSON.getValue(), JSON.toJSONString(data));
    }

    /**
     * 输出指定格式到客户端
     *
     * @param response
     * @param contentType 指定格式，可以调用本类中的常量
     * @param text        指定格式的内容
     */
    public static void out(HttpServletResponse response, String contentType, String text) {
        Logger log = LoggerFactory.getLogger(HttpResponse.class);
        log.debug("response : {}", text);

        response.setContentType(contentType);
        response.setHeader("Pragma", "No-cache");
        response.setHeader("Cache-Control", "no-cache");
        response.setDateHeader("Expires", 0);
        try {
            response.getWriter().write(text);
            response.getWriter().close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
