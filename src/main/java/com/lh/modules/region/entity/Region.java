package com.lh.modules.region.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * 功能描述：
 *  <p>版权所有：</p>
 *  未经本人许可，不得以任何方式复制或使用本程序任何部分
 *
 * @Company: LionHerding
 * @Author 牧狮&&紫色年华
 * @Date 2019-07-14
 * @Version: 1.0.0
 *
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class Region implements Serializable {

    private static final long serialVersionUID = 1L;
    /**
     * 行政区划ID
     */
    @TableId(value = "region_id", type = IdType.UUID)
    private String regionId;
    /**
     * 行政区编码，编码规则遵照国家发布的“村级全国行政区划代码”。行政区划编码不能重复。全国的编码特定为0；省编码规则：xx0000000000（大于10的两位数字+10个0）；市编码规则：两位数字（省编码前两位数） +xx00000000（大于0的两位数字+8个0)；县编码规则：四位数字（市编码前4位数） +xx000000（大于0的两位数字+6个0)；乡镇编码规则：六位数字（县编码前6位数） +xxx000（大于0的三位数字+3个0)；村编码规则：九位数字（乡镇编码前9位数） +xxx（大于0的三位数字)；
     */
    private String regionCode;
    /**
     * 父节点编码
     */
    private String regionPcode;
    /**
     * 行政区名称
     */
    private String regionName;
    /**
     * 行政级别：数据字典【行政区划级别】编码，含义：
            0-国、1-省、2-市、3-县/区、4-乡/镇、5-村；
            行政区划编码长度为1，行政区划级别为0；
            行政区划编码长度为2，行政区划级别为1；
            行政区划编码长度为4，行政区划级别为2；
            行政区划编码长度为6，行政区划级别为3；
            行政区划编码长度为9，行政区划级别为4；
            行政区划编码
     */
    private String regionLevel;
    /**
     * 行政区划首字母
     */
    private String regionInitial;
    /**
     * 百度市编码
     */
    private String cityCode;
    /**
     * 备注
     */
    private String remark;
    /**
     * 排序号
     */
    private Integer seqNum;
    /**
     * 备注
     */
    private String province;
    /**
     * 备注
     */
    private String city;


    /////////////////////////////// 非表字段 ///////////////////////////////

    @TableField(exist = false)
    private List<B> blist = new ArrayList<B>();
}
