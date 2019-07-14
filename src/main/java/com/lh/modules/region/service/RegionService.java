package com.lh.modules.region.service;

import com.lh.modules.region.entity.Region;
import com.baomidou.mybatisplus.extension.service.IService;

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
public interface RegionService extends IService<Region> {

    boolean insertOrUpdate(Region iRegion);

    List<Region> listInfo();

}
