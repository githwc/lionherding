package com.lh.modules.redisPractice.service;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lh.modules.redisPractice.entity.RedisRank;
import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

/**
 * 功能描述：
 *
 *  <p>版权所有：</p>
 *  未经本人许可，不得以任何方式复制或使用本程序任何部分
 *
 * @Company: 紫色年华
 * @Author xieyc
 * @Date 2020-02-01
 * @Version: 1.0.0
 *
 */
public interface RedisRankService extends IService<RedisRank> {

    /**
     * 初始化数据
     */
    void init();

    /**
     * 分页获取数据
     * @return
     */
    Page<RedisRank> rankPage(Page<RedisRank> page);

    /**
     * 获取排行榜top10
     * @return
     */
    JSONObject top10();


}
