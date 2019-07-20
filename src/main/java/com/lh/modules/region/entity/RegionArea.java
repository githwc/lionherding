package com.lh.modules.region.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;

/**
 * 功能描述：
 * <p>版权所有：</p>
 * 未经本人许可，不得以任何方式复制或使用本程序任何部分
 *
 * @Company: LionHerding
 * @Author: 牧狮&&紫色年华
 * @Datetime: 2019-07-14
 * @Version: 1.0.0
 */
@Data
public class RegionArea {

    @TableField(exist = false)
    private String cname;

    @TableField(exist = false)
    private String ccode;

    @TableField(exist = false)
    private String cpcode;


}
