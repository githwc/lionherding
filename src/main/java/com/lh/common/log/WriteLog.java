package com.lh.common.log;

import java.lang.annotation.*;

/**
 *
 * 功能描述： 写日志注解接口定义
 *          @Target(ElementType.METHOD):用于限定注解使用范围，method:用于方法上
 *          @Retention(RetentionPolicy.RUNTIME):指定注解不仅保存在class文件中，JVM加载class文件之后，仍然存在
 *          @Documented:表明使用这个注解会被javadoc记录，注解类型信息会被记录在生成的文档中
 *          @Inherited：该注解会被子类继承
 *
 * <p>版权所有：</p>
 * 未经本人许可，不得以任何方式复制或使用本程序任何部分
 *
 * @Company  紫色年华
 * @Author   xieyc
 * @Datetime 2019-05-31 10:58
 */
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
public @interface WriteLog {

	public String mName();      //方法名

	public int optype();        //操作类型：CRUD

}
