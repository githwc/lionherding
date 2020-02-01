package com.lh.modules.redisPractice.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * 功能描述：
 *  <p>版权所有：</p>
 *  未经本人许可，不得以任何方式复制或使用本程序任何部分
 *
 * @Company: 紫色年华
 * @Author xieyc
 * @Date 2020-02-01
 * @Version: 1.0.0
 *
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class RedisRank implements Serializable {

    private static final long serialVersionUID = 1L;
    @TableId(value = "redis_rank_id", type = IdType.UUID)
    private String redisRankId;
    /**
     * 姓名
     */
    private String name;
    /**
     * 总分数
     */
    private Integer score;
    /**
     * 中文分数
     */
    private Integer chineseScore;
    /**
     * 数学分数
     */
    private Integer mathScore;
    /**
     * 备注
     */
    private String remark;



}
