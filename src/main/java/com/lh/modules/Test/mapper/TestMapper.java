package com.lh.modules.Test.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.lh.modules.Test.entity.Tests;
import org.springframework.stereotype.Repository;

@Repository
public interface TestMapper extends BaseMapper<Tests> {


    Tests getInfo();


}
