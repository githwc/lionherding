package com.lh.system.controller;

import com.lh.common.config.exception.userException.RunningException;
import com.lh.system.service.SysDeptService;
import com.lh.system.vo.SysDeptTree;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * 功能描述：
 *
 *  <p>版权所有：</p>
 *  未经本人许可，不得以任何方式复制或使用本程序任何部分
 *
 * @Company: 紫色年华
 * @Author xieyc
 * @Date 2019-09-20
 * @Version: 1.0.0
 *
 */
@RestController
@RequestMapping("/sysDept")
@Slf4j
public class SysDeptController {

    @Autowired
    public SysDeptService service;

    /**
     * 查出所有部门,并以树结构格式返回前端
     * @return
     */
    @GetMapping("/queryTreeList")
    public List<SysDeptTree> queryTreeList(){
        List<SysDeptTree> list = new ArrayList<>();
        try {
            list = service.queryTreeList();
        }catch (Exception e){
            throw new RunningException("系统错误,请联系管理员！");
        }finally {
            return list;
        }
    }

    /**
     * <p>
     * 部门搜索功能方法,根据关键字模糊搜索相关部门
     * </p>
     *
     * @param keyWord
     * @return
     */
    @GetMapping(value = "/searchBy")
    public List<SysDeptTree> searchBy(@RequestParam(name = "keyWord", required = true) String keyWord) {
        List<SysDeptTree> list = new ArrayList<SysDeptTree>();
        try {
            list = service.searhBy(keyWord);
        } catch (Exception e) {
            throw new RunningException("系统错误!");
        }finally {
            return list;
        }
    }
}
