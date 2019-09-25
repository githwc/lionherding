package com.lh.system.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lh.common.utils.LocalHostUtil;
import com.lh.common.utils.SpringContextUtils;
import com.lh.system.entity.SysLog;
import com.lh.system.entity.SysUser;
import com.lh.system.mapper.SysLogMapper;
import com.lh.system.service.SysLogService;
import org.apache.shiro.SecurityUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;

/**
* 功能描述：
*
*  <p>版权所有：</p>
*  未经本人许可，不得以任何方式复制或使用本程序任何部分
*
* @Company: 紫色年华
* @Author xieyc
* @Date 2019-09-21
* @Version: 1.0.0
*
*/
@Service
@Transactional(rollbackFor = Exception.class)
public class SysLogServiceImpl extends ServiceImpl<SysLogMapper, SysLog> implements SysLogService {

    @Override
    public void addLog(String LogContent, Integer logType, Integer opType) {
        SysLog sysLog = new SysLog();
        sysLog.setLogContent(LogContent);
        sysLog.setLogType(logType);
        sysLog.setOpType(opType);
        //请求的方法名
        //请求的参数
        try {
            //获取request
            HttpServletRequest request = SpringContextUtils.getHttpServletRequest();
            sysLog.setIpAdress(LocalHostUtil.getIpAddress(request));
        } catch (Exception e) {
            sysLog.setIpAdress("异常地址");
        }

        //获取登录用户信息
        SysUser sysUser = (SysUser) SecurityUtils.getSubject().getPrincipal();
        if(sysUser!=null){
            sysLog.setCreateUserId(sysUser.getLoginName());
            sysLog.setCreateUserName(sysUser.getLoginName());
        }
        sysLog.setCreateTime(LocalDateTime.now());
        //保存系统日志
        this.baseMapper.insert(sysLog);
    }
}
