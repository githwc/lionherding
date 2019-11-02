package com.lh.system.controller;

import com.lh.common.config.exception.RunException.RunningException;
import com.lh.common.config.exception.parameterException.ParameterException;
import com.lh.system.entity.SysDept;
import com.lh.system.service.SysDeptService;
import com.lh.system.vo.DepartIdModel;
import com.lh.system.vo.SysDeptTree;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
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
@Api(tags="系统部门")
public class SysDeptController {

    @Autowired
    public SysDeptService service;

    @GetMapping(value = "/queryTreeList")
    @ApiOperation(value = "加载部门树",notes = "加载所有部门树")
    public List<SysDeptTree> queryTreeList(){
        try {
            return service.queryTreeList();
        }catch (Exception e){
            throw new RunningException("系统错误,请联系管理员！");
        }
    }

    @GetMapping(value = "/searchBy")
    @ApiOperation(value = "部门搜索",notes = "部门搜索,根据部门名称模糊搜索")
    public List<SysDeptTree> searchBy(@RequestParam(name = "keyWord", required = true) String keyWord) {
        try {
            return service.searhBy(keyWord);
        } catch (Exception e) {
            throw new RunningException("系统错误!");
        }
    }

    @ApiOperation(value = "部门树信息",notes = "添加或编辑页面时对该方法发起请求,以树结构形式加载所有部门的名称")
    @GetMapping(value = "/queryIdTree")
    public List<DepartIdModel> queryIdTree() {
        try {
           return service.queryDepartIdTreeList();
        } catch (Exception e) {
            throw new RunningException("系统运行错误");
        }
    }

    @PutMapping(value = "/edit")
    @ApiOperation(value = "部门修改",notes = "部门修改")
    public void edit(@RequestBody SysDept sysDept, HttpServletRequest request) {
        try{
            service.editByDeptId(sysDept);
        }catch (Exception e){
            throw new RunningException("系统错误");
        }
    }

    @ApiOperation(value = "部门删除",notes = "部门删除")
    @DeleteMapping(value = "/delete")
    public void delete(@RequestParam(name="sysDeptId",required=true) String id) {
        service.deleteById(id);
    }


    @ApiOperation(value = "部门批量删除",notes = "部门批量删除")
    @DeleteMapping(value = "/deleteBatch")
    public void deleteBatch(@RequestParam(name = "ids", required = true) String ids) {
        if (ids == null || "".equals(ids.trim())) {
            throw new ParameterException("参数不识别！");
        } else {
            List<String> list = Arrays.asList(ids.split(","));
            list.forEach(curr->{
                this.service.deleteById(curr);
            });
        }
    }

    @ApiOperation(value = "部门添加",notes = "部门添加")
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public void add(@RequestBody SysDept sysDept, HttpServletRequest request) {
        try {
            service.create(sysDept,request);
        } catch (Exception e) {
            throw new RunningException("操作失败");
        }
    }

}
