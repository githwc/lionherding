package com.lh.modules.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.lh.modules.model.Test;
import org.springframework.stereotype.Repository;

@Repository
public interface TestMapper extends BaseMapper<Test> {


    Test getInfo();


}