package com.lh.system.service.impl;

import com.lh.system.log.SystemLogService;
import com.lh.system.log.WriteLog;
import com.lh.system.mapper.DepartMapper;
import com.lh.system.entity.Depart;
import com.lh.system.service.DepartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * 功能描述：
 * <p>
 * <p>版权所有：</p>
 * 未经本人许可，不得以任何方式复制或使用本程序任何部分
 *
 * @Company: LionHerding
 * @Author: 牧狮&&紫色年华
 * @Datetime: 2019-05-31 17:38
 */
@Service
public class DepartServiceImpl implements DepartService {

    @Autowired
    private DepartMapper departMapper;

    public static final String ALL_DEPART_REDIS="allDepartRedis";

    @Override
    @WriteLog(mName = "部门查询",optype = SystemLogService.OPTYPE_READ)
    @Cacheable(cacheNames = {"depart"})
    public List<Depart> departList() {
        List<Depart> departList = new ArrayList<Depart>();
        // //redis中读取数据
        // String departListString = String.valueOf(RedisUtil.get(ALL_DEPART_REDIS));
        // //读取mysql数据
        // if(departListString.equals(null)||departListString.equals("null")){
            departList = departMapper.departList();
        //     //缓存到redis
        //     RedisUtil.set(ALL_DEPART_REDIS,departList,-1);
        // }else{
        //     departList = Jackson.json2list(departListString,Depart.class);
        // }
        return departList;
    }
}
