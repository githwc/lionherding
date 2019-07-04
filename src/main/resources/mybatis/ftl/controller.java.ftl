package ${package.Controller};

import org.springframework.web.bind.annotation.RequestMapping;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
<#if restControllerStyle>
import org.springframework.web.bind.annotation.RestController;
<#else>
import org.springframework.stereotype.Controller;
</#if>
<#if superControllerClassPackage??>
import ${superControllerClassPackage};
</#if>

/**
 *
 * 功能描述：
 *
 *  <p>版权所有：</p>
 *  未经本人许可，不得以任何方式复制或使用本程序任何部分
 *
 * @Company: LionHerding
 * @Author ${author}
 * @Date ${date}
 * @Version: 1.0.0
 *
 */
<#if restControllerStyle>
@RestController
<#else>
@Controller
</#if>
@RequestMapping("<#if package.ModuleName??>/${package.ModuleName}</#if>/<#if controllerMappingHyphenStyle??>${controllerMappingHyphen}<#else>${table.entityPath}</#if>")
<#if kotlin>
class ${table.controllerName}<#if superControllerClass??> : ${superControllerClass}()</#if>
<#else>
<#if superControllerClass??>
public class ${table.controllerName} extends ${superControllerClass} {
<#else>
public class ${table.controllerName} {
</#if>

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    public ${table.serviceName} i${entity}Service;

    /**
    * @Description:根据id查询
    * @param:
    * @return:
    */
    @GetMapping("/getById")
    public ${entity} getById(@RequestParam("id") String id){
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
    * @param ${table.entityPath}  传递的实体
    * @return  0 失败  1 成功
    */
    @PostMapping("/${table.entityPath}Save")
    public int ${table.entityPath}Save(${entity} ${table.entityPath}) {
       int count = 0;
       try {
           count = i${entity}Service.insertOrUpdate(${table.entityPath}) ? 1 : 0;
       } catch (Exception e) {
           logger.error("${table.entityPath}Save -=- {}",e.toString());
       }
       return count;
    }

}
</#if>