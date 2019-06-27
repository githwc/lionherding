package com.lh.modules.service;

import com.lh.modules.model.Test;
import com.lh.system.model.User;

/**
 * 功能描述：
 * <p>版权所有：</p>
 * 未经本人许可，不得以任何方式复制或使用本程序任何部分
 *
 * @Company: LionHerding
 * @Author: 牧狮&&紫色年华
 * @Datetime: 2019-06-04
 * @Version: 1.0.0
 */
public interface TestService {

    User queryByid(String id);

    Test getInfo();
}
