package com.lh.system.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.lh.system.basis.Result;
import com.lh.system.entity.User;
import org.springframework.ui.Model;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * 功能描述：
 *
 *  <p>版权所有：</p>
 *  未经本人许可，不得以任何方式复制或使用本程序任何部分
 *
 * @Company: LionHerding
 * @Author 牧狮&&紫色年华
 * @Date 2019-07-04
 * @Version: 1.0.0
 *
 */
public interface UserService extends IService<User> {

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

    Result<Object> login(String loginName, String password, HttpServletRequest request, HttpServletResponse response, String code, Model model);

    List<User> userList();

    boolean insertOrUpdate(User iSysUser);
}
