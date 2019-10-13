package com.lh.modules.TestTemp.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import org.jeecgframework.poi.excel.annotation.Excel;

/**
 * 功能描述：
 *  <p>版权所有：</p>
 *  未经本人许可，不得以任何方式复制或使用本程序任何部分
 *
 * @Company: LionHerding
 * @Author xieyc
 * @Date 2019-09-29
 * @Version: 1.0.0
 *
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class Test implements Serializable {

    private static final long serialVersionUID = 1L;
    @TableId(value = "test_id", type = IdType.UUID)
    private String testId;

    @Excel(name = "我的名字", width = 15)
    private String name;

    @Excel(name = "我的年龄", width = 15)
    private Integer age;
    @Excel(name = "我的地址", width = 35)
    private String address;
    private Integer sort;
    private String remark;
    @Excel(name="我的性别",width = 15,replace = {"男_1","女_0"})
    private String sex;
    @Excel(name="测试字段",dictTable="sys_dictionary",dicCode="key",dicText="value")
    private Integer arg2;
    @Excel(name="ARG1",width = 15,replace = {"正确_1","错误_0"})
    private boolean arg1;


    /////////////////////////////// 非表字段 ///////////////////////////////

}
