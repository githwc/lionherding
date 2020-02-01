package com.lh.modules.redisPractice.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lh.modules.redisPractice.constant.RedisConstant;
import com.lh.modules.redisPractice.entity.RedisRank;
import com.lh.modules.redisPractice.mapper.RedisRankMapper;
import com.lh.modules.redisPractice.service.RedisRankService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.DefaultTypedTuple;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ZSetOperations;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

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
@Service
@Transactional(rollbackFor = Exception.class)
@Slf4j
public class RedisRankServiceImpl extends ServiceImpl<RedisRankMapper, RedisRank> implements RedisRankService {

    private final RedisTemplate redisTemplate;

    public RedisRankServiceImpl (RedisTemplate redisTemplate){
        this.redisTemplate = redisTemplate;
    }


    @Override
    public void init() {
        List<RedisRank> list = this.baseMapper.selectList(new QueryWrapper<RedisRank>(null));
        for(int i = 0; i<list.size();i++){

        }

        String key = RedisConstant.SCORE_BANK;
        boolean flag = redisTemplate.delete(key);
        // if(flag){
            Set<ZSetOperations.TypedTuple<String>> tuples = new HashSet<>();
            for (int i = 0; i < 100000; i++) {
                DefaultTypedTuple<String> tuple = new DefaultTypedTuple<>("乔治_" + i, 1D + i);
                tuples.add(tuple);
            }
            Long num = redisTemplate.opsForZSet().add(key, tuples);
            log.info("redis排行榜初始化完成受影响行数:"+num);
            System.out.println(redisTemplate.opsForZSet().getOperations());
        // }
    }

    @Override
    public Page<RedisRank> rankPage(Page<RedisRank> page) {
        this.baseMapper.rankPage(page);
        return null;
    }

    @Override
    public JSONObject top10() {
        return null;
    }


}
