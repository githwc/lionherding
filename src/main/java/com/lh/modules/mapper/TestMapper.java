package com.lh.modules.mapper;

import com.lh.modules.model.Test;
import org.springframework.stereotype.Repository;

@Repository
public interface TestMapper {

    Test getInfo();

}