package com.lh.system.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * 功能描述：
 *  <p>版权所有：</p>
 *  未经本人许可，不得以任何方式复制或使用本程序任何部分
 *
 * @Company: 紫色年华
 * @Author  xieyc
 * @Date 2019-09-20
 * @Version: 1.0.0
 *
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class SysRolePermission implements Serializable {

    private static final long serialVersionUID = 1L;
    @TableId(value = "sys_role_permission_id", type = IdType.UUID)
    private String sysRolePermissionId;
    /**
     * 角色id
     */
    private String roleId;
    /**
     * 权限id
     */
    private String permissionId;


    /////////////////////////////// 自定义方法 ///////////////////////////////

    public SysRolePermission() {}

    public SysRolePermission(String sysRoleId, String permissionId) {
        this.roleId = sysRoleId;
        this.permissionId = permissionId;
    }

    /////////////////////////////// 非表字段 ///////////////////////////////

}
