package com.lh.modules.redisPractice.mapper;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lh.modules.redisPractice.entity.RedisUser;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.lh.modules.redisPractice.model.RedisUserQuery;
import com.lh.system.model.query.UserQuery;
import com.lh.system.model.vo.SysUserVO;
import org.apache.ibatis.annotations.Param;
import org.springframework.data.redis.repository.query.RedisQueryCreator;
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
 * @Date 2020-01-19
 * @Version: 1.0.0
 *
 */
@Repository
public interface RedisUserMapper extends BaseMapper<RedisUser> {

    /**
     * 用户查询
     * @param page
     * @param query
     * @return
     */
    Page<RedisUser> userPage(@Param("page")Page<RedisUser> page, @Param("query") RedisUserQuery query);
}
