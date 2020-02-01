package com.lh.system.model.vo;

import com.lh.system.entity.SysUser;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 功能描述：用户信息展示层
 * <p>版权所有：</p>
 * 未经本人许可，不得以任何方式复制或使用本程序任何部分
 *
 * @Company: 紫色年华
 * @Author: xieyc
 * @Datetime: 2020-01-07
 * @Version: 1.0.0
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class SysUserVO extends SysUser {

    /**
     * 部门名称
     */
    private String departName;
}
