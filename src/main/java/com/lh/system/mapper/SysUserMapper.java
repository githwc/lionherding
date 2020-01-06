package com.lh.system.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lh.system.entity.SysUser;
import com.lh.system.model.query.UserQuery;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 *
 * 功能描述：
 *
 *  <p>版权所有：</p>
 *  未经本人许可，不得以任何方式复制或使用本程序任何部分
 *
 * @Company: 紫色年华
 * @Author xieyc
 * @Date 2019-09-19
 * @Version: 1.0.0
 *
 */
@Repository
public interface SysUserMapper extends BaseMapper<SysUser> {

    /**
     * 用户查询
     * @param page
     * @param userQuery
     * @return
     */
    Page<SysUser> userList(@Param("page")Page<SysUser> page, @Param("query")UserQuery userQuery);

}
