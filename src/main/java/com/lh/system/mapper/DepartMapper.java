package com.lh.system.mapper;

import com.lh.system.model.Depart;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface DepartMapper {
    int deleteByPrimaryKey(String id);

    int insert(Depart record);

    int insertSelective(Depart record);

    Depart selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(Depart record);

    int updateByPrimaryKey(Depart record);
}