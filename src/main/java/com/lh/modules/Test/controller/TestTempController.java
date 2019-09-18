package com.lh.modules.Test.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 功能描述：
 *
 * <p>版权所有：</p>
 * 未经本公司许可，不得以任何方式复制或使用本程序任何部分
 *
 * @Company: 烟台海涛网络科技有限公司
 * @Author: xie && 紫色年华
 * @Datetime: 2019-09-18 14:01
 * @Version: 1.0.0
 */
@Controller
public class TestTempController {

    @RequestMapping("/text")
    public String text (){
        return "temp";
    }
}
