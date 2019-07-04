package com.lh.system.controller;

import com.lh.system.entity.Dictionary;
import com.lh.system.service.DictionaryService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 *
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
@RestController
@RequestMapping("/dictionary")
public class DictionaryController {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    public DictionaryService iSysDictionaryService;

    /**
     * @Description:根据id查询
     * @param:
     * @return:
     */
    @GetMapping("/getById")
    public Dictionary getById(@RequestParam("id") String id){
        return null;
    }

    /**
     * @Description:根据id删除
     * @param:
     * @return:
     */
    @GetMapping("/deleteById")
    public int delete(@RequestParam("id") String id){
        return 0;
    }

    /**
     * @Description:保存和修改公用的
     * @param sysDictionary  传递的实体
     * @return  0 失败  1 成功
     */
    @PostMapping("/sysDictionarySave")
    public int sysDictionarySave(Dictionary dictionary) {
        int count = 0;
        try {
            count = iSysDictionaryService.insertOrUpdate(dictionary) ? 1 : 0;
        } catch (Exception e) {
            logger.error("sysDictionarySave -=- {}",e.toString());
        }
        return count;
    }

}
