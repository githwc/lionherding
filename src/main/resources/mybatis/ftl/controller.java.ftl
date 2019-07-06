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
    * @Description:
    * @param:
    * @return:
    */
    @GetMapping("/{id}")
    @ApiOperation(value = "根据主键ID查询",  notes = "根据主键ID查询")
    public ${entity} get${entity}ById(@ApiParam(required = true, name = "id",value = "主键ID")@PathVariable("id") String id){
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
    public int createAndUpdate(${entity} ${table.entityPath}) {
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