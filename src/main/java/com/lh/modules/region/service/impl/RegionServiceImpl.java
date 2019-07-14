package com.lh.modules.region.service.impl;

import com.lh.modules.region.entity.Region;
import com.lh.modules.region.mapper.RegionMapper;
import com.lh.modules.region.service.RegionService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
* 功能描述：
*
*  <p>版权所有：</p>
*  未经本人许可，不得以任何方式复制或使用本程序任何部分
*
* @Company: LionHerding
* @Author 牧狮&&紫色年华
* @Date 2019-07-14
* @Version: 1.0.0
*
*/
@Service
@Transactional(rollbackFor = Exception.class)
public class RegionServiceImpl extends ServiceImpl<RegionMapper, Region> implements RegionService {

    @Autowired
    private RegionMapper regionMapper;

    @Override
    public boolean insertOrUpdate(Region iRegion) {
        boolean result = false;
        return result;
    }

    @Override
    public List<Region> listInfo() {
        return regionMapper.listInfo();
    }


}
