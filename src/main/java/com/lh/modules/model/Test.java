package com.lh.modules.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.time.LocalDateTime;

@Data
/**
 * mp会默认使用实体类的类名到数据库中找对应的表
 * 可以通过@TableName来指定在数据库中的名字
 */
@TableName(value = "test")
public class Test {

    /**
     * @TableId:
     *  value:指定数据库表中主键的列名，如果实体类属性和表中字段一致，可省略
     *  type:指定主键策略
     *         IdType.AUTO：数据库ID自增
     *         IdType.INPUT: 用户输入ID
     *         IdType.ID_WORKER:  全局唯一ID，内容为空自动填充（默认配置）
     *         IdType.UUID: 全局唯一ID，内容为空自动填充
     */

    @TableId(value = "id",type = IdType.UUID)
    private String id;

    private String lastName;

    private String email;

    private Integer gender;

    private Integer age;

    private LocalDateTime createTime;

    /**
     * 通过此注解表明不对应数据库中的字段
     */
    @TableField(exist = false)
    private String AS;

}