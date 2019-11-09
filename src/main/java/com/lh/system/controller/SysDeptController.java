package com.lh.system.controller;

import com.lh.common.config.exception.RunException.RunningException;
import com.lh.common.config.exception.parameterException.ParameterException;
import com.lh.common.constant.CommonConstant;
import com.lh.common.log.WriteLog;
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
    @WriteLog(opPosition = "加载部门树" ,optype = CommonConstant.OPTYPE_READ)
    public List<SysDeptTree> queryTreeList(){
        try {
            return service.queryTreeList();
        }catch (Exception e){
            throw new RunningException(e.getMessage() .equals("") ?  "系统错误,请联系管理员！" : e.getMessage());
        }
    }

    @GetMapping(value = "/searchBy")
    @ApiOperation(value = "部门搜索",notes = "部门搜索,根据部门名称模糊搜索")
    @WriteLog(opPosition = "部门搜索" ,optype = CommonConstant.OPTYPE_READ)
    public List<SysDeptTree> searchBy(@RequestParam(name = "keyWord", required = true) String keyWord) {
        try {
            return service.searchBy(keyWord);
        } catch (Exception e) {
            throw new RunningException(e.getMessage() .equals("") ?  "系统错误,请联系管理员！" : e.getMessage());
        }
    }

    @ApiOperation(value = "部门树信息",notes = "添加或编辑页面时对该方法发起请求,以树结构形式加载所有部门的名称")
    @GetMapping(value = "/queryIdTree")
    @WriteLog(opPosition = "部门树信息" ,optype = CommonConstant.OPTYPE_READ)
    public List<DepartIdModel> queryIdTree() {
        try {
           return service.queryDepartIdTreeList();
        } catch (Exception e) {
            throw new RunningException(e.getMessage() .equals("") ?  "系统错误,请联系管理员！" : e.getMessage());
        }
    }

    @PutMapping(value = "/edit")
    @ApiOperation(value = "部门修改",notes = "部门修改")
    @WriteLog(opPosition = "部门修改" ,optype = CommonConstant.OPTYPE_UPDATE)
    public void edit(@RequestBody SysDept sysDept, HttpServletRequest request) {
        try{
            service.editByDeptId(sysDept);
        }catch (Exception e){
            throw new RunningException(e.getMessage() .equals("") ?  "系统错误,请联系管理员！" : e.getMessage());
        }
    }

    @DeleteMapping(value = "/delete")
    @ApiOperation(value = "部门删除",notes = "部门删除")
    @WriteLog(opPosition = "部门删除" ,optype = CommonConstant.OPTYPE_DELETE)
    public void delete(@RequestParam(name="sysDeptId",required=true) String id) {
        try{
            service.deleteById(id);
        }catch(Exception e){
            throw new RunningException(e.getMessage() .equals("") ?  "系统错误,请联系管理员！" : e.getMessage());
        }
    }

    @DeleteMapping(value = "/deleteBatch")
    @ApiOperation(value = "部门批量删除",notes = "部门批量删除")
    @WriteLog(opPosition = "部门批量删除" ,optype = CommonConstant.OPTYPE_DELETE)
    public void deleteBatch(@RequestParam(name = "ids", required = true) String ids) {
        try{
            service.deleteBatch(ids);
        }catch(Exception e){
            throw new RunningException(e.getMessage() .equals("") ?  "系统错误,请联系管理员！" : e.getMessage());
        }
    }

    @ApiOperation(value = "部门添加",notes = "部门添加")
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    @WriteLog(opPosition = "部门添加" ,optype = CommonConstant.OPTYPE_CREATE)
    public void add(@RequestBody SysDept sysDept, HttpServletRequest request) {
        try {
            service.create(sysDept,request);
        } catch (Exception e) {
            throw new RunningException(e.getMessage() .equals("") ?  "系统错误,请联系管理员！" : e.getMessage());
        }
    }

}
