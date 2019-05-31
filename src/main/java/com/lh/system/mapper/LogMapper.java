package com.lh.system.mapper;

import com.lh.system.model.Log;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface LogMapper {
    int deleteByPrimaryKey(String id);

    int insert(Log record);

    int insertSelective(Log record);

    Log selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(Log record);

    int updateByPrimaryKey(Log record);
}