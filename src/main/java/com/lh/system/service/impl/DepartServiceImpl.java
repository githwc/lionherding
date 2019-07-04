package com.lh.system.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lh.system.entity.Depart;
import com.lh.system.mapper.DepartMapper;
import com.lh.system.service.DepartService;
import org.springframework.stereotype.Service;

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

@Service
public class DepartServiceImpl extends ServiceImpl<DepartMapper, Depart> implements DepartService {

    @Override
    public boolean insertOrUpdate(Depart iSysDepart) {
        boolean result = false;
        return result;
    }
}
