package com.lh.system.controller;

import com.lh.common.config.exception.parameterException.ParameterException;
import com.lh.common.config.exception.userException.RunningException;
import com.lh.common.config.filter.JwtUtil;
import com.lh.common.constant.CacheConstant;
import com.lh.system.entity.SysDept;
import com.lh.system.service.SysDeptService;
import com.lh.system.vo.DepartIdModel;
import com.lh.system.vo.SysDeptTree;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
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
public class SysDeptController {

    @Autowired
    public SysDeptService service;

    /**
     * 查出所有部门,并以树结构格式返回前端
     * @return
     */
    @GetMapping(value = "/queryTreeList")
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

    /**
     * 添加或编辑页面对该方法发起请求,以树结构形式加载所有部门的名称
     *
     * @return
     */
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
    public void edit(@RequestBody SysDept sysDept, HttpServletRequest request) {
        try{
            service.editByDeptId(sysDept);
        }catch (Exception e){
            throw new RunningException("系统错误");
        }
    }

    /**
     *   通过id删除
     * @param id
     * @return
     */
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

}
