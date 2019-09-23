package com.lh.common.config.mybatis;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.InjectionConfig;
import com.baomidou.mybatisplus.generator.config.*;
import com.baomidou.mybatisplus.generator.config.po.TableInfo;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 功能描述：MyBatis-plus 代码生成器
 *
 * <p>版权所有：</p>
 * 未经本公司许可，不得以任何方式复制或使用本程序任何部分
 *
 * @Company: 紫色年华
 * @Author:  xieyc
 * @Datetime: 2019-06-28 17:17
 * @Version: 1.0.0
 */
public class MyBatisGenerator {

    private static final String BASE_PATH = "D:\\idea_work\\lionherding";   //生成文件所在项目路径
    private static final String BASE_PACKAGE = "com.lh.modules";      //基本包名
    private static final String MODEL_NAME = "sysLog";                         //文件夹名
    private static final String AUTHOR = "xieyc";                    //作者
    private static final String[] TABLES = {"sys_log"};             //要生成的表名

    //数据库配置四要素
    private static final String DRIVER_NAME = "com.mysql.cj.jdbc.Driver";
    private static final String URL = "jdbc:mysql://127.0.0.1:3306/lionherding?useUnicode=true&characterEncoding=UTF-8&serverTimezone=GMT%2B8";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "123456";


    public static void main(String[] args) {
        //代码生成器
        AutoGenerator gen = new AutoGenerator();

        /**
         * 全局配置
         */
        gen.setGlobalConfig(new GlobalConfig()
                .setIdType(IdType.UUID)                         // 主键自增
                .setFileOverride(true)                          // 是否覆盖文件
                .setOutputDir( BASE_PATH + "/src/main/java")    // 输出目录
                .setAuthor(AUTHOR)                              // 作者
                .setOpen(false)                                 // 生成后打开文件夹
                // .setActiveRecord(true)                          // 开启 activeRecord 模式
                .setEnableCache(false)                          // XML 二级缓存
                .setBaseResultMap(false)                         // XML 生成基本的resultMap
                .setBaseColumnList(false)                        // XML 生成基本的sql片段
                // 自定义文件命名,%s会自动填充表实体属性！默认IXXXService
                .setMapperName("%sMapper")
                .setXmlName("%sMapper")
                .setServiceName("%sService")
                .setServiceImplName("%sServiceImpl")
                .setControllerName("%sController")
        );

        /**
         * 数据库配置
         */
        gen.setDataSource(new DataSourceConfig()
                .setUrl(URL)
                .setDriverName(DRIVER_NAME)
                .setUsername(USERNAME)
                .setPassword(PASSWORD)
                .setDbType(DbType.MYSQL)
        );

        /**
         * 包配置
         */
        gen.setPackageInfo(new PackageConfig()
                .setModuleName(MODEL_NAME)
                .setParent(BASE_PACKAGE)        // 自定义包路径
                .setController("controller")    // 这里是控制器包名，默认 web
                .setEntity("entity")
                .setMapper("mapper")
                .setService("service")
                .setServiceImpl("service.impl")
                .setXml("resource")
        );

        /**
         * 注入自定义配置 可以在 VM 中使用 cfg.abc 设置的值
         */
        InjectionConfig abc = new InjectionConfig() {
            @Override
            public void initMap() {
                Map<String, Object> map = new HashMap<String, Object>();
                map.put("abc", this.getConfig().getGlobalConfig().getAuthor() + "-mp");
                this.setMap(map);
            }
        };
        //自定义文件输出位置（非必须）
        List<FileOutConfig> fileOutList = new ArrayList<FileOutConfig>();
        fileOutList.add(new FileOutConfig("/templates/mapper.xml.ftl") {
            @Override
            public String outputFile(TableInfo tableInfo) {
                return BASE_PATH + "/src/main/resources/mapper/"+ MODEL_NAME +"/" + tableInfo.getEntityName() + "Mapper.xml";
            }
        });
        abc.setFileOutConfigList(fileOutList);
        gen.setCfg(abc);
        /**
         * 指定模板引擎 默认是VelocityTemplateEngine ，需要引入相关引擎依赖
         */
        gen.setTemplateEngine(new FreemarkerTemplateEngine());

        /**
         * 模板配置 关闭默认 xml 生成，调整生成 至 根目录
         */
        gen.setTemplate(new TemplateConfig()
                .setXml(null)
                .setService("/mybatis/service.java")
                .setServiceImpl("/mybatis/serviceImpl.java")
                .setController("/mybatis/controller.java")
                .setMapper("/mybatis/mapper.java")
                .setEntity("/mybatis/model.java")
        );


        /**
         * 策略配置
         */
        gen.setStrategy(new StrategyConfig()
                .setNaming(NamingStrategy.underline_to_camel)       // 表名生成策略
                .setColumnNaming(NamingStrategy.underline_to_camel)
                .setEntityLombokModel(true)
                .setRestControllerStyle(true)
                .setInclude(TABLES)                                 // 需要生成的表
                .setSuperEntityColumns("id")                        // 自定义实体，公共字段
                .setControllerMappingHyphenStyle(true)

                // 自定义 mapper 父类 默认BaseMapper
                //.setSuperMapperClass("com.baomidou.mybatisplus.mapper.BaseMapper")
                // 自定义 service 父类 默认IService
                // .setSuperServiceClass("com.baomidou.demo.TestService")
                // 自定义 service 实现类父类 默认ServiceImpl
                // .setSuperServiceImplClass("com.baomidou.demo.TestServiceImpl")
                // 自定义 controller 父类
                //.setSuperControllerClass("com.kichun."+packageName+".controller.AbstractController")
                // 【实体】是否生成字段常量（默认 false）
                // public static final String ID = "test_id";
                // .setEntityColumnConstant(true)
                // 【实体】是否为构建者模型（默认 false）
                // public User setName(String name) {this.name = name; return this;}
                // .setEntityBuilderModel(true)
                // 【实体】是否为lombok模型（默认 false）<a href="https://projectlombok.org/">document</a>
                // Boolean类型字段是否移除is前缀处理
                // .setEntityBooleanColumnRemoveIsPrefix(true)
        );

        // 执行生成
        gen.execute();
    }
}
