package com.atguigu.springcloud.util;

import com.baomidou.mybatisplus.core.exceptions.MybatisPlusException;
import com.baomidou.mybatisplus.core.toolkit.StringPool;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.InjectionConfig;
import com.baomidou.mybatisplus.generator.config.*;
import com.baomidou.mybatisplus.generator.config.po.TableInfo;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import com.baomidou.mybatisplus.generator.engine.VelocityTemplateEngine;
import org.apache.commons.lang3.StringUtils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class CodeGenerator {

    private static String projectName = "cloud-provider-payment8001";

    private static final String projectPath = System.getProperty("user.dir") + "/" + projectName;

    private static String moduleName = "payment";

    private static final String packageParent = "com.atguigu.springcloud";

    private static String tablePrefix = "";

    private static String inputTableName = "payment";


    /**
     * 读取控制台参数
     *
     * @param tip
     * @return
     */
    public static String scanner(String tip) {
        Scanner scanner = new Scanner(System.in);
        StringBuilder help = new StringBuilder();
        help.append(inputTableName);
        System.out.println(help.toString());
        if (scanner.hasNext()) {
            String ipt = scanner.next();
            if (StringUtils.isNotBlank(ipt)) {
                return ipt;
            }
        }
        throw new MybatisPlusException("请输入正确的" + tip + "！");
    }

    public static void geneCode() {
        //定义代码生成器
        AutoGenerator mpg = new AutoGenerator();
        //全局配置
        mpg.setGlobalConfig(constructGlobalConfig());
        //数据源配置
        mpg.setDataSource(constructDataSourceConfig());
        //包配置
        mpg.setPackageInfo(constructPackageConfig());
        // 配置模板
        mpg.setTemplate(constructTemplateConfig());

        //配置策略
        mpg.setStrategy(constructStrategyConfig());
        //自定义配置
        mpg.setCfg(constructInjectionConfig());

        mpg.setTemplateEngine(new VelocityTemplateEngine());
        mpg.execute();

    }

    private static InjectionConfig constructInjectionConfig() {
        InjectionConfig cfg = new InjectionConfig() {
            @Override
            public void initMap() {
//                Map<String, Object> map = new HashMap<>();
//                map.put("gysBasicEntityPackage", "com.atguigu.springcloud.entity");
////                String s = ConvertUtil.convertTableToStandStr(inputTableName, tablePrefix, "_");
//                String s = inputTableName;
//                map.put("cleanName",s.substring(0,1).toLowerCase()+s.substring(1));
//                this.setMap(map);
            }
        };
        String templatePath = "/templates/mapper.xml.vm";
        // 自定义输出配置
        List<FileOutConfig> focList = new ArrayList<>();
        // 自定义配置会被优先输出
        focList.add(new FileOutConfig(templatePath) {
            @Override
            public String outputFile(TableInfo tableInfo) {
                // 自定义输出文件名 ， 如果你 Entity 设置了前后缀、此处注意 xml 的名称会跟着发生变化！！
                return projectPath + "/src/main/resources/mapper/"
                        + tableInfo.getEntityName() + "Mapper" + StringPool.DOT_XML;
            }
        });

        cfg.setFileOutConfigList(focList);
        return cfg;
    }

    private static StrategyConfig constructStrategyConfig() {
        StrategyConfig strategy = new StrategyConfig();
        strategy.setNaming(NamingStrategy.underline_to_camel);
        strategy.setColumnNaming(NamingStrategy.underline_to_camel);
//        strategy.setSuperEntityClass("你自己的父类实体,没有就不用设置!");
        strategy.setEntityLombokModel(true);
        strategy.setRestControllerStyle(true);
        // 公共父类
//        strategy.setSuperControllerClass("com.gys.common.base.BaseController");
        // 写于父类中的公共字段
//        strategy.setSuperEntityColumns("id");
//        inputTableName = scanner("表名，多个英文逗号分割").split(",")[0];
        strategy.setInclude(inputTableName);
        strategy.setControllerMappingHyphenStyle(true);
//        strategy.setTablePrefix(tablePrefix);
        // 使用lombok模型
        strategy.setEntityLombokModel(true);
        //同时生成表的字段名 作为实体列中的属性
        strategy.setEntityColumnConstant(true);
        return strategy;
    }

    private static TemplateConfig constructTemplateConfig() {
        TemplateConfig templateConfig = new TemplateConfig();
        templateConfig.setEntity("templates/entity.java.vm") // entity模板采用自定义模板
                .setMapper("templates/mapper.java.vm")// mapper模板采用自定义模板
                .setXml(null) // 不生成xml文件
                .setService("templates/service.java.vm") // 不生成service接口
                .setServiceImpl("templates/serviceImpl.java.vm") // serviceImpl模板采用自定义模板
                .setController("templates/controller.java.vm"); // controller模板采用自定义模板
        return templateConfig;
    }

    private static GlobalConfig constructGlobalConfig() {
        GlobalConfig gc = new GlobalConfig();
        gc.setBaseColumnList(true);
        gc.setBaseResultMap(true);
        gc.setFileOverride(true);
        gc.setOutputDir(projectPath + "/src/main/java");
        gc.setAuthor("QiCheng.Wang");
        gc.setOpen(false);
        gc.setSwagger2(true);
        return gc;
    }


    private static DataSourceConfig constructDataSourceConfig() {
        DataSourceConfig res = new DataSourceConfig();
        res.setUrl("jdbc:mysql://localhost:3306/atguigu_springcloud?useUnicode=true&characterEncoding=utf-8&useSSL=false");
        // dsc.setSchemaName("public");
        res.setDriverName("com.mysql.jdbc.Driver");
        res.setUsername("root");
        res.setPassword("123456789");
        return res;
    }

    private static PackageConfig constructPackageConfig() {
        PackageConfig pc = new PackageConfig();
//        moduleName = scanner("模块名");
        pc.setModuleName(moduleName);
        pc.setParent(packageParent);
        return pc;
    }

//    private static Properties loadProperty() throws IOException {
//        Properties properties = new Properties();
//        InputStream in = CodeGenerator.class.getClass().getClassLoader().getResourceAsStream("classPath:application.yml");
//        properties.load(in);
//        return properties;
//    }


    public static void main(String[] args) throws IOException {

        geneCode();
    }

}
