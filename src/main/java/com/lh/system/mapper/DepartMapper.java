package com.lh.system.mapper;

import com.lh.system.entity.Depart;

import java.util.List;

public interface DepartMapper {
    int deleteByPrimaryKey(String id);

    int insert(Depart record);

    int insertSelective(Depart record);

    Depart selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(Depart record);

    int updateByPrimaryKey(Depart record);

    //================自定义SQL==================
    List<Depart> departList();
}