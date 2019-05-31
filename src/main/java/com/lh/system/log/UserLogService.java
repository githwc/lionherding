package com.lh.system.log;

import com.lh.system.model.Log;
import com.lh.system.model.User;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.framework.core.dao.DaoApi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.servlet.http.HttpServletRequest;
import java.util.Date;

/**
 *
 * 功能描述： 系统日志服务类
 *
 * <p>版权所有：</p>
 * 未经本人许可，不得以任何方式复制或使用本程序任何部分
 *
 * @Company  LionHerding
 * @Author   牧狮&&紫色年华
 * @Datetime 2019-05-31 10:58
 */
@Service
public class UserLogService {

	private static Logger logger = LogManager.getLogger(UserLogService.class);
	
	public final static int OPTYPE_CREATE = 0;
	public final static int OPTYPE_DELETE = 1;
	public final static int OPTYPE_UPDATE = 2;
	public final static int OPTYPE_READ   = 3;
	
	public final static int OPSTATE_SUCCESS = 0;
	public final static int OPSTATE_FAILURE = 1;

	@Autowired
    private DaoApi dao;

	/**
	 * @Description:写系统日志(原始方法)
     * @Date: 2019/5/31 11:14
	 * @param user	    : HttpSession中的User
	 * @param operate	: 操作位置:比如写模块的名称、站点名称、登录、退出等
	 * @param opType	: 操作类型
	 * @param describe	: 描述(可选)
     *      String...:String类型的可变长度的数组,固定长度的数组是String[] str={};这样写,可变的就String... str.
	 * @return 是否成功
	 */
	public boolean write(User user, String operate, int opType, String... describe) {
		user = user!=null ? user : new User();
		Log log = new Log();
		log.setId(dao.getUUID());
		log.setLoginName(user.getLoginName());
		log.setUserId(user.getId());
		log.setUserName(user.getName());
		log.setIpAdress(user.getIpAddress());
		log.setFunctionId(operate);
		log.setOpDate(new Date());
		log.setOpType(opType);
		String tmpDesc = "";
		for(int i=0; i<describe.length; i++){
			tmpDesc += describe[i] + (i<describe.length-1?"\r\n":"");
		}
		log.setDesc(tmpDesc);
		return true;
	}
	
	/**
	 * @Description:方法重写：仅仅记录用户系统操作日志，不进行系统文件日志写入
     * @Date: 2019/5/31 11:15
	 * @param request	: HttpServletRequest
	 * @param operate	: 操作位置
	 * @param opType	: 操作类型
	 * @param opState	: 操作执行情况
	 * @return 是否成功
	 */
	public boolean write(HttpServletRequest request, String operate, int opType, int opState) {
		String message = new String[] { "创建", "删除", "更新", "读取" }[opType] + "位置(" + operate + ")" + (opState != 1 ? "成功" : "失败");
        User user = new User();
        return write(user, operate, opType, message);
	}
	
	/**
	 * @Description:方法重写：方法抛出异常情况下调用，不仅仅写入用户操作日志，* 而且还需要将系统异常信息写入到文件日志中
	 * @param request	: HttpServletRequest
	 * @param operate	: 操作位置
	 * @param opType	: 操作类型
	 * @param opState	: 操作执行情况
	 * @param ex	: 异常对象
	 * @return 是否成功
	 */
	public boolean write(HttpServletRequest request, String operate, int opType, int opState, Exception ex) {
		String message = new String[] { "创建", "删除", "更新", "读取" }[opType] + "位置(" + operate + ")" + (opState != 1 ? "成功" : "失败");
		//如果抛出异常则将异常信息写入到文件日志中，否则仅仅记录操作日志不会写文件日志
		logger.error(message, ex);
		//将系统异常信息写入文件日志中后，写用户系统操作日志，返回日志操作状态
        User user = new User();
		return write(user, operate, opType, message);
	}
	
}
