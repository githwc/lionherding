package com.lh.system.log;

import java.lang.annotation.*;

/**
 *
 * 功能描述： 写日志注解接口定义
 *
 * <p>版权所有：</p>
 * 未经本人许可，不得以任何方式复制或使用本程序任何部分
 *
 * @Company  LionHerding
 * @Author   牧狮&&紫色年华
 * @Datetime 2019-05-31 10:58
 */
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
public @interface WriteLog {

	public String mName();

	public int optype();

	public String desc() default "";

}
