package com.lh.modules.Test.service;

import com.lh.modules.Test.entity.Tests;

/**
 * 功能描述：
 * <p>版权所有：</p>
 * 未经本人许可，不得以任何方式复制或使用本程序任何部分
 *
 * @Company: 紫色年华
 * @Author:  xieyc
 * @Datetime: 2019-06-04
 * @Version: 1.0.0
 */
public interface TestService {

    Tests getInfo();

    void addTest();

    Tests getInfoById(String id);

    void updateInfo();

    void select();

    void delete();

    void selectConditions();

    void updateConditions();

    void deleteConditions();

    void deleteAll();
}
