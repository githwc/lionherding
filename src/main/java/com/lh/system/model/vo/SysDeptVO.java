package com.lh.system.model.vo;

import com.lh.system.entity.SysDept;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 功能描述：
 * <p>版权所有：</p>
 * 未经本公司许可，不得以任何方式复制或使用本程序任何部分
 *
 * @Company: 紫色年华
 * @Author: xieyc
 * @Datetime: 2019-12-28
 * @Version: 1.0.0
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class SysDeptVO extends SysDept {

    /**
     * 管理人
     */
    private String managerName;
}
