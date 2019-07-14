package com.lh.modules.region.controller;

import com.lh.modules.region.entity.Region;
import com.lh.modules.region.service.RegionService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.web.bind.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Map;

/**
 *
 * 功能描述：
 *
 *  <p>版权所有：</p>
 *  未经本人许可，不得以任何方式复制或使用本程序任何部分
 *
 * @Company: LionHerding
 * @Author 牧狮&&紫色年华
 * @Date 2019-07-14
 * @Version: 1.0.0
 *
 */
@RestController
@RequestMapping("/region/region")
public class RegionController {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    public RegionService iRegionService;

    /**
    * @Description:
    * @param:
    * @return:
    */
    @GetMapping("/{id}")
    @ApiOperation(value = "根据主键ID查询",  notes = "根据主键ID查询")
    public Region getRegionById(@ApiParam(required = true, name = "id",value = "主键ID")@PathVariable("id") String id){
        return null;
    }

    /**
    * @Description:
    * @param:
    * @return:
    */
    @DeleteMapping("/{id}")
    @ApiOperation(value = "根据主键ID删除",  notes = "根据主键ID删除")
    public int delete(@ApiParam(required = true, name = "id",value = "主键ID")@PathVariable("id") String id){
       return 0;
    }

    /**
    * @Description:
    * @param
    * @return  0 失败  1 成功
    */
    @PostMapping("/createAndUpdate")
    @ApiOperation(value = "保存和修改公用API", notes = "保存和修改公用API")
    public int createAndUpdate(Region region) {
       int count = 0;
       try {
           count = iRegionService.insertOrUpdate(region) ? 1 : 0;
       } catch (Exception e) {
           logger.error("regionSave -=- {}",e.toString());
       }
       return count;
    }

    @GetMapping("/listInfo")
    public List<Region> getInfo(){
        return iRegionService.listInfo();
    }
}
