package com.lh.system.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.lh.system.entity.Log;

/**
 * 功能描述：
 *
 *  <p>版权所有：</p>
 *  未经本人许可，不得以任何方式复制或使用本程序任何部分
 *
 * @Company: 紫色年华
 * @Author  xieyc
 * @Date 2019-07-04
 * @Version: 1.0.0
 *
 */
public interface LogService extends IService<Log> {

    boolean insertOrUpdate(Log iSysLog);

}
