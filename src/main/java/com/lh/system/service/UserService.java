package com.lh.system.service;

import com.lh.system.basis.Result;
import com.lh.system.model.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 功能描述：
 * <p>
 * <p>版权所有：</p>
 * 未经本人许可，不得以任何方式复制或使用本程序任何部分
 *
 * @Company: LionHerding
 * @Author: 牧狮&&紫色年华
 * @Datetime: 2019-05-31 17:37
 */
public interface UserService {
    /**
     * @Description:用户自行注册
     * @Date: 2019/6/13 14:06
     * @Param:
     * @Return:
     * @throws:
     */
    int register(User user);

    /**
     * @Description:获取用户信息
     * @Date: 2019/6/13 16:24
     * @Param:
     * @Return:
     * @throws:
     */
    User getUserInfo(String loginName);

    Result<Object> login(String loginName, String password, HttpServletRequest request, HttpServletResponse response);
}
