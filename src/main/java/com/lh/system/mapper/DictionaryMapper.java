package com.lh.system.mapper;


import com.lh.system.model.Dictionary;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface DictionaryMapper {
    int deleteByPrimaryKey(String id);

    int insert(Dictionary record);

    int insertSelective(Dictionary record);

    Dictionary selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(Dictionary record);

    int updateByPrimaryKey(Dictionary record);
}