package com.lh.system.controller;

import com.fasterxml.jackson.annotation.JsonView;
import com.lh.system.basis.Result;
import com.lh.system.entity.User;
import com.lh.system.log.SystemLogService;
import com.lh.system.log.WriteLog;
import com.lh.system.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.framework.core.utils.PictureValidateCode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 *
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
@RestController
@RequestMapping("/user")
@Api(tags = "用户相关")
public class UserController {

    @Autowired
    private UserService userService;

    private Logger logger = LoggerFactory.getLogger(getClass());

    /**
     * @Description:
     * @param:
     * @return:
     */
    @GetMapping("/{id}")
    @WriteLog(mName = "根据主键ID查询", optype = SystemLogService.OPTYPE_READ)
    @ApiOperation(value = "根据主键ID查询",  notes = "根据主键ID查询")
    public User getById(@ApiParam(required = true, name = "id",value = "主键ID")@PathVariable("id") String id){
        return null;
    }

    /**
     * @Description:
     * @param:
     * @return:
     */
    @DeleteMapping("/{id}")
    @WriteLog(mName = "根据主键ID删除", optype = SystemLogService.OPTYPE_DELETE)
    @ApiOperation(value = "根据主键ID删除",  notes = "根据主键ID删除")
    public int deleteById(@ApiParam(required = true, name = "id",value = "主键ID")@PathVariable("id") String id){
        return 0;
    }

    /**
     * @Description:
     * @param sysUser  传递的实体
     * @return  0 失败  1 成功
     */
    @PostMapping("/createAndUpdate")
    @WriteLog(mName = "保存和修改公用API", optype = SystemLogService.OPTYPE_CREATE)
    @ApiOperation(value = "保存和修改公用API", notes = "保存和修改公用API")
    public int sysUserSave(User sysUser) {
        int count = 0;
        try {
            count = userService.insertOrUpdate(sysUser) ? 1 : 0;
        } catch (Exception e) {
            logger.error("sysUserSave -=- {}",e.toString());
        }
        return count;
    }
    /**
     * @Description:
     * @Date: 2019/6/13 13:34
     * @Param:
     * @Return:
     * @throws:
     */
    @PostMapping(value = "/register")
    @WriteLog(mName = "用户自行注册", optype = SystemLogService.OPTYPE_CREATE)
    @ApiOperation(value = "用户自行注册", notes = "用户自行注册")
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
     * @Description:
     * @Date: 2019/6/13 15:05
     * @Param:
     * @Return:
     * @throws:
     */
    @PostMapping(value = "/login")
    @WriteLog(mName = "用户登录", optype = SystemLogService.OPTYPE_READ)
    @ApiOperation(value = "用户登录",notes = "用户登录")
    public Result<Object> login(
                        @ApiParam(required = true,name = "login_loginName",value = "姓名")@RequestParam("login_loginName")String loginName,
                        @ApiParam(required = true,name = "login_password",value = "密码")@RequestParam("login_password")String password,
                        @ApiParam(required = true,name = "code",value = "验证码")@RequestParam("code")String code,
                        Model model,
                        HttpServletRequest request, HttpServletResponse response
    ){
        return userService.login(loginName,password,request,response,code,model);
    }

    /**
     * @Description:
     *      随机生成图片验证码
     *      并通过流的形式返回到前端
     *      比对session的code和前端返回的code是否一致
     * @Date: 2019/6/17 15:29
     * @Param:
     * @Return:
     * @throws:
     */
    @GetMapping(value = "/pictureValidate")
    @WriteLog(mName = "获取图片验证", optype = SystemLogService.OPTYPE_READ)
    @ApiOperation(value = "随机生成图片验证码",notes = "随机生成图片验证码")
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


    //===============================REST ful API START 仅用于测试  ==========================
    /**
    * @Description:
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
    @ApiOperation(value = "查询用户列表", notes = "查询用户列表")
    public List<User> userList(@ApiParam(required = true, name = "name", value = "姓名") @RequestParam(name = "name",defaultValue = "贝尔",required = false) String name,
                               @ApiParam(required = true, name = "password", value = "密码") @RequestParam(name = "password",defaultValue = "123456" ,required = false) String password
                           ){
        return userService.userList();
    }

    /**
    * @Description:
    * @Date: 22:34 2019/7/3
    * @Param:
    * @Return:
    * @throws:
    * @PathVariable：匹配请求URL片段
    */
    @GetMapping("/user/{id}")
    @ApiOperation(value = "查询指定用户详情", notes = "查询指定用户详情")
    public User userDetail(@ApiParam(required = true,name = "id",value = "主键ID")@PathVariable("id") String id){
        User user = new User();
        user.setId(id);
        user.setName("莫德里奇");
        user.setJobs(1);
        return user;
    }

    /**
    * @Description:
    * @Date: 23:26 2019/7/3
    * @Param:
    * @Return:
    * @throws:
     * @RequestBody：解析传进的json串
    */
    @PostMapping("/user")
    @ApiOperation(value = "创建用户", notes = "创建用户")
    public int create(@RequestBody User user){
        Date date = new Date();
        System.out.println(date);
        System.out.println(date.getTime());
        return 1;
    }

    @PostMapping("/op")
    public void lo(){
        System.out.println("当前类======UserController.lo()"+new Date());
    }
    //===============================REST ful API  END 仅用于测试  ==========================
}
