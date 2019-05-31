package com.lh.system.mapper;

import com.lh.system.model.RoleMember;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface RoleMemberMapper {
    int deleteByPrimaryKey(String id);

    int insert(RoleMember record);

    int insertSelective(RoleMember record);

    RoleMember selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(RoleMember record);

    int updateByPrimaryKey(RoleMember record);
}