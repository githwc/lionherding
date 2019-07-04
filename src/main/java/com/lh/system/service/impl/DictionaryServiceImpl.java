package com.lh.system.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lh.system.entity.Dictionary;
import com.lh.system.mapper.DictionaryMapper;
import com.lh.system.service.DictionaryService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


/**
 * 功能描述：
 *
 *  <p>版权所有：</p>
 *  未经本人许可，不得以任何方式复制或使用本程序任何部分
 *
 * @Company: LionHerding
 * @Author 牧狮&&紫色年华
 * @Date 2019-07-04
 * @Version: 1.0.0
 *
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class DictionaryServiceImpl extends ServiceImpl<DictionaryMapper, Dictionary> implements DictionaryService {

    @Override
    public boolean insertOrUpdate(Dictionary iSysDictionary) {
        boolean result = false;
        return result;
    }
}