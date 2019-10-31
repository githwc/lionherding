package com.lh.system.controller;

import com.lh.common.config.exception.parameterException.ParameterException;
import com.lh.common.config.exception.userException.RunningException;
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
import java.util.ArrayList;
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

    /**
     * 查出所有部门,并以树结构格式返回前端
     * @return
     */
    @GetMapping(value = "/queryTreeList")
    @ApiOperation(value = "加载所有部门树",notes = "加载所有部门树")
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
    @ApiOperation(value = "部门搜索",notes = "部门搜索")
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

    /**
     * 添加或编辑页面时对该方法发起请求,以树结构形式加载所有部门的名称
     *
     * @return
     */
    @ApiOperation(value = "部门树信息(部分信息)",notes = "部门树信息(部分信息)")
    @GetMapping(value = "/queryIdTree")
    public List<DepartIdModel> queryIdTree() {
        try {
           return service.queryDepartIdTreeList();
        } catch (Exception e) {
            throw new RunningException("系统运行错误");
        }
    }

    /**
     * 修改
     * @param sysDept
     * @param request
     * @return
     */
    @PutMapping(value = "/edit")
    @ApiOperation(value = "部门修改",notes = "部门修改")
    public void edit(@RequestBody SysDept sysDept, HttpServletRequest request) {
        try{
            service.editByDeptId(sysDept);
        }catch (Exception e){
            throw new RunningException("系统错误");
        }
    }

    /**
     *  通过id删除
     * @param id
     * @return
     */
    @ApiOperation(value = "部门删除",notes = "部门删除")
    @DeleteMapping(value = "/delete")
    public void delete(@RequestParam(name="sysDeptId",required=true) String id) {
        service.deleteById(id);
    }


    /**
     * 批量删除 根据前端请求的多个ID,对数据库执行删除相关部门数据的操作
     *
     * @param ids
     * @return
     */
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

    /**
     * 添加新数据
     *
     * @param sysDept
     * @return
     */
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
