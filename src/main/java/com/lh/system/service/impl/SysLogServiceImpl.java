package com.lh.system.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lh.common.dao.DaoApi;
import com.lh.common.utils.LocalHostUtil;
import com.lh.common.utils.SpringContextUtils;
import com.lh.system.entity.SysLog;
import com.lh.system.entity.SysUser;
import com.lh.system.mapper.SysLogMapper;
import com.lh.system.service.SysLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

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

    @Autowired
    private DaoApi daoApi;

    @Override
    public void addLog(String LogContent, Integer logType, String requestMethod,String requestParams) {
        SysLog sysLog = new SysLog();
        sysLog.setLogContent(LogContent);
        sysLog.setLogType(logType);
        sysLog.setRequestMethod(requestMethod);
        sysLog.setRequestParam(requestParams);
        try {
            HttpServletRequest request = SpringContextUtils.getHttpServletRequest();
            sysLog.setIpAdress(LocalHostUtil.getIpAddress(request));
        } catch (Exception e) {
            sysLog.setIpAdress("异常地址");
        }
        SysUser currUser = daoApi.getCurrentUser();
        sysLog.setCreateUserId(currUser == null ? "" : currUser.getSysUserId());
        sysLog.setCreateUserName(currUser == null ? "" : currUser.getUserName());
        //保存系统日志
        this.baseMapper.insert(sysLog);
    }

    @Override
    public JSONObject logInfo() {
        JSONObject jsonObject = new JSONObject();
        Map<String,Object> map = new HashMap<String, Object>();
        map.put("totalVisitCount", 120);
        map.put("todayVisitCount", 19);
        map.put("todayIp", "192.168.0.283");
        jsonObject.put("logInfo",map);
        return jsonObject;
    }

}
