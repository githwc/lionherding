package com.lh.system.config.security.Brower;

/**
 * 功能描述：
 * <p>版权所有：</p>
 * 未经本人许可，不得以任何方式复制或使用本程序任何部分
 *
 * @Company: LionHerding
 * @Author: 牧狮&&紫色年华
 * @Datetime: 2019-07-08
 * @Version: 1.0.0
 */
// @RestController
public class BrowerSecurityController {

    // private Logger logger = LoggerFactory.getLogger(getClass());
    //
    // private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();
    //
    // //security 会将请求的url等信息放到缓存中
    // private RequestCache requestCache = new HttpSessionRequestCache();
    //
    // @Autowired
    // private SecurityProperties securityProperties;
    //
    // @RequestMapping("/authentication/require")
    // @ResponseStatus(code = HttpStatus.UNAUTHORIZED)
    // public String requireAuthentication(HttpServletRequest request, HttpServletResponse response)throws IOException {
    //     SavedRequest savedRequest = requestCache.getRequest(request,response);
    //     if(savedRequest != null){
    //         String requestUrl = savedRequest.getRedirectUrl();
    //         logger.info("引发跳转的请求是:"+requestUrl);
    //         if(StringUtils.endsWithIgnoreCase(requestUrl,".html")){
    //             redirectStrategy.sendRedirect(request,response,securityProperties.getBrower().getLoginPage());
    //         }
    //     }
    //     return "访问的服务需要认证，请引导用户到登录页！";
    // }

}
