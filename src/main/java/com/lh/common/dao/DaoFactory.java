package com.lh.common.dao;

import org.springframework.stereotype.Service;

import java.util.UUID;

/**
 * 功能描述：implements - DAO data layer operation
 * <p>
 * <p>版权所有：</p>
 * 未经本人许可，不得以任何方式复制或使用本程序任何部分
 *
 * @Company: 紫色年华
 * @Author: xieyc
 * @Datetime: 2019-05-31 14:15
 * @Version: 1.0.0
 */
@Service
public class DaoFactory implements DaoApi {

    @Override
    public synchronized String getUUID() {
       return (UUID.randomUUID().toString()).replace("-", "");
    }
}
