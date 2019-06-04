package com.lh.system.mapper;

import com.lh.system.model.Rolemember;

public interface RolememberMapper {
    int deleteByPrimaryKey(String id);

    int insert(Rolemember record);

    int insertSelective(Rolemember record);

    Rolemember selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(Rolemember record);

    int updateByPrimaryKey(Rolemember record);
}