package com.lh.system.controller;

import com.fasterxml.jackson.annotation.JsonView;
import com.lh.system.basis.BasisAction;
import com.lh.system.basis.Result;
import com.lh.system.basis.ResultCode;
import com.lh.system.log.SystemLogService;
import com.lh.system.log.WriteLog;
import com.lh.system.entity.User;
import com.lh.system.service.UserService;
import org.framework.core.utils.PictureValidateCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.web.PageableDefault;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.print.Pageable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 功能描述：
 * <p>
 * <p>版权所有：</p>
 * 未经本人许可，不得以任何方式复制或使用本程序任何部分
 *
 * @Company: LionHerding
 * @Author: 牧狮&&紫色年华
 * @Datetime: 2019-05-31 17:45
 */
@RestController
public class UserController extends BasisAction {

    @Autowired
    private UserService userService;
    /**
     * @Description:用户自行注册
     * @Date: 2019/6/13 13:34
     * @Param:
     * @Return:
     * @throws:
     */
    @PostMapping(value = "/register")
    @ResponseBody
    public String register(User user, Map<String,Object> map){
        //保证登录名的唯一性
        User onlyUser = userService.getUserInfo(user.getLoginName());
        if(onlyUser!=null){
            return "自行注册失败,登录名重复，请更换登录名";
        }
        //注册用户
        int result = userService.register(user);
        if(result>0){
            return "自行注册成功！";
        }else {
            return "自行注册失败";
        }
    }

    /**
     * @Description:用户登录
     * @Date: 2019/6/13 15:05
     * @Param:
     * @Return:
     * @throws:
     */
    @PostMapping(value = "/login")
    @WriteLog(mName = "登录", optype = SystemLogService.OPTYPE_READ)
    public Result<Object> login(@RequestParam("login_loginName")String loginName,
                        @RequestParam("login_password")String password,
                        @RequestParam("code")String code,
                        Model model,
                        HttpServletRequest request, HttpServletResponse response
    ){
        return userService.login(loginName,password,request,response,code,model);
    }

    /**
     * @Description:图片验证
     *      随机生成图片验证码
     *      并通过流的形式返回到前端
     *      比对session的code和前端返回的code是否一致
     * @Date: 2019/6/17 15:29
     * @Param:
     * @Return:
     * @throws:
     */
    @RequestMapping(value = "/pictureValidate")
    @WriteLog(mName = "图片验证登录", optype = SystemLogService.OPTYPE_READ)
    public String pictureValidate(HttpServletRequest request,HttpServletResponse response
    )throws Exception{
        response.setContentType("image/jpeg");//设置响应格式
        //禁止图片缓存
        response.setHeader("Pragma","No-cache");//http1.0
        response.setHeader("Cache-Control", "no-cache");//http1.1
		response.setDateHeader("Expires",0);//在代理服务器端防止缓冲
        //随机生成一个图形验证码
        PictureValidateCode pictureValidateCode = new PictureValidateCode(120,34,5,100);
        //将图形验证码保存到session
        HttpSession session = request.getSession();
        session.setAttribute("code",pictureValidateCode.getCode());
        //通过流的形式返回到前端
        pictureValidateCode.write(response.getOutputStream());
        return null;
    }


    @RequestMapping(value = "/seckill")
    public Result<Object> login(
    ){
        System.out.println("当前类:UserAction.login()===" + new Date());
        User user = new User();
        user.setLoginName("23");
        user.setPassword("23");
        // return Result.fail(ResultCode.FAIL);
        return Result.success(ResultCode.SUCCESS);
    }

    /**
    * @Description:查询用户列表
    * @Date: 22:12 2019/7/3
    * @Param:
    * @Return:
    * @throws:
    * @RequestParam:--name:参数名称  defaultValue：默认值  required：是否必传
    * @PageableDefault：page:当前页 size:每页大小  sort:排序方式；  三个参数分别对应 传递的 page, size, sort
    * @JsonView：1.使用接口声明多个视图
     *          2.在值对象的get方法上指定视图
     *          3.在Controller方法上指定视图
    */
    @GetMapping("/user")
    @JsonView({User.userSimpleView.class})
    public List<User> userList(@RequestParam(name = "name",defaultValue = "贝尔",required = false) String name,
                           @RequestParam(name = "password",defaultValue = "123456" ,required = false) String password
                           /*@PageableDefault(page = 1,size = 10,sort = "age,asc")  Pageable pageable*/){
        return userService.userList();
    }

    /**
    * @Description:查询指定用户详情
    * @Date: 22:34 2019/7/3
    * @Param:
    * @Return:
    * @throws:
    * @PathVariable：匹配请求URL片段
    */
    @GetMapping("/user/{id}")
    public User userDetail(@PathVariable("id") String id){
        User user = new User();
        user.setId(id);
        user.setName("莫德里奇");
        user.setJobs(1);
        return user;
    }

    /**
    * @Description:创建用户
    * @Date: 23:26 2019/7/3
    * @Param:
    * @Return:
    * @throws:
     * @RequestBody：解析传进的json串
    */
    @PostMapping("/user")
    public int create(@RequestBody User user){
        Date date = new Date();
        System.out.println(date);
        System.out.println(date.getTime());
        return 1;
    }
}
