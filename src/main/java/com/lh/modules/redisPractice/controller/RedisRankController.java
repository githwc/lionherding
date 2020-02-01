package com.lh.modules.redisPractice.controller;

import com.lh.modules.redisPractice.service.RedisRankService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.Set;

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
     * 获取数据
     * @return
     */
    @GetMapping("/initRank")
    public Set initRank(){
        return iRedisRankService.initRank();
    }

    /**
     * 获取总成绩排行榜top10
     * @return
     */
    @GetMapping("/scoreTop10")
    public Set top10(@RequestParam("type")String type){
        return iRedisRankService.top10(type);
    }

    /**
     * 新增一名学生的成绩到排行榜中
     */
    @PostMapping("/add")
    public void add(){
        iRedisRankService.add();
    }

    /**
     * 查询指定人的排名和分数
     * @return
     */
    @GetMapping("/userInfo")
    public Map userInfo(){
       return iRedisRankService.userInfo();
    }

    /**
     * .统计分数区间人数
     * @return
     */
    @GetMapping("/scopeCount")
    public Long scopeCount(){
        return iRedisRankService.scopeCount();
    }

    /**
     * 使用加法操作分数
     *  直接在原有的score上使用加法;
     *  如果没有这个元素，则会创建，并且score初始为0.再使用加法
     * @return
     */
    @PostMapping("/addScore")
    public void addScore(){
        iRedisRankService.addScore();
    }

}
