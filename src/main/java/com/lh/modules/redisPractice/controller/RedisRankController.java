package com.lh.modules.redisPractice.controller;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lh.modules.redisPractice.entity.RedisRank;
import com.lh.modules.redisPractice.service.RedisRankService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * 功能描述：排行榜
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
@RestController
@RequestMapping("/redisPractice/redisRank")
@Slf4j
public class RedisRankController {

    private final RedisRankService iRedisRankService;

    @Autowired
    public RedisRankController(RedisRankService iRedisRankService){
        this.iRedisRankService = iRedisRankService;
    }

    /**
     * 初始化数据
     */
    @PostMapping("/initRankData")
    public void init(){
        iRedisRankService.init();
    }

    /**
     * 分页获取数据
     * @return
     */
    @GetMapping("/rankPage")
    public Page<RedisRank> rankPage(Page<RedisRank> page){
        return iRedisRankService.rankPage(page);
    }

    /**
     * 获取排行榜top10
     * @return
     */
    @GetMapping("/top10")
    public JSONObject top10(){
        return iRedisRankService.top10();
    }


}
