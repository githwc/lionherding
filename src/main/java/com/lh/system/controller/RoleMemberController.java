package com.lh.system.controller;

import com.lh.system.entity.RoleMember;
import com.lh.system.service.RoleMemberService;
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
@RequestMapping("rolemember")
public class RoleMemberController {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    public RoleMemberService iSysRolememberService;

    /**
     * @Description:根据id查询
     * @param:
     * @return:
     */
    @GetMapping("/getById")
    public RoleMember getById(@RequestParam("id") String id){
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
     * @param sysRolemember  传递的实体
     * @return  0 失败  1 成功
     */
    @PostMapping("/sysRolememberSave")
    public int sysRolememberSave(RoleMember sysRolemember) {
        int count = 0;
        try {
            count = iSysRolememberService.insertOrUpdate(sysRolemember) ? 1 : 0;
        } catch (Exception e) {
            logger.error("sysRolememberSave -=- {}",e.toString());
        }
        return count;
    }

}
