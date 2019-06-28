package com.lh.modules.Test.service;

import com.lh.modules.Test.entity.Test;

/**
 * 功能描述：
 * <p>版权所有：</p>
 * 未经本人许可，不得以任何方式复制或使用本程序任何部分
 *
 * @Company: LionHerding
 * @Author: 牧狮&&紫色年华
 * @Datetime: 2019-06-04
 * @Version: 1.0.0
 */
public interface TestService {

    Test getInfo();

    void addTest();

    Test getInfoById(String id);

    void updateInfo();

    void select();

    void delete();

    void selectConditions();

    void updateConditions();

    void deleteConditions();
}
