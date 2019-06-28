package com.lh.modules.Emp.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 
 * </p>
 *
 * @author 牧狮&&紫色年华
 * @date 2019-06-28
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class Emp implements Serializable {

    private static final long serialVersionUID = 1L;
    @TableId(value = "emp_id", type = IdType.UUID)
    private String empId;
    private String lastName;


    /////////////////////////////// 非表字段 ///////////////////////////////

}
