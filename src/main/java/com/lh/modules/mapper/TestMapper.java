package com.lh.modules.mapper;

import com.lh.modules.model.Test;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface TestMapper {
    int deleteByPrimaryKey(Integer id);

    @Insert("INSERT INTO Test ('id','name') VALUES (1,'213')")
    int insert(Test record);

    int insertSelective(Test record);

    Test selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Test record);

    int updateByPrimaryKey(Test record);
}