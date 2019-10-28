package com.lh.system.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.lh.system.entity.SysPermission;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 *
 * 功能描述：
 *
 *  <p>版权所有：</p>
 *  未经本人许可，不得以任何方式复制或使用本程序任何部分
 *
 * @Company: LionHerding
 * @Author xieyc && 紫色年华
 * @Date 2019-09-20
 * @Version: 1.0.0
 *
 */
@Repository
public interface SysPermissionMapper extends BaseMapper<SysPermission> {

    List<SysPermission> queryPermissionByUser(@Param("loginName") String loginName);

    @Update("update sys_permission set is_leaf=#{leaf} where sys_permission_id = #{id}")
    public int setMenuLeaf(@Param("id") String id,@Param("leaf") int leaf);
}
