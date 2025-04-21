package com.example.iotwarehouse.util;

import com.baomidou.mybatisplus.generator.FastAutoGenerator;
import com.baomidou.mybatisplus.generator.config.OutputFile;
import com.baomidou.mybatisplus.generator.config.rules.DbColumnType;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;

import java.sql.Types;
import java.util.Collections;

/**
 * @author hwshou
 * @date 2025/4/21  0:06
 */
public class CodeGenerator {
    public static void main(String[] args) {
        String url="jdbc:mysql://localhost:3306/iot_warehouse";
        String username="root";
        String password="123456";
        FastAutoGenerator.create(url, username, password)
                .globalConfig(builder -> {
                    builder.author("hwshou") // 设置作者
                            //.enableSwagger() // 开启 swagger 模式
                            // .fileOverride() // 覆盖已生成文件
                            //System.getProperty("user.dir") :表示当前项目的路径：
                            .outputDir(System.getProperty("user.dir")+"/src/main/java"); // 指定输出目录
                })
                .dataSourceConfig(builder -> builder.typeConvertHandler((globalConfig, typeRegistry, metaInfo) -> {
                    int typeCode = metaInfo.getJdbcType().TYPE_CODE;
                    if (typeCode == Types.SMALLINT) {
                        // 自定义类型转换
                        return DbColumnType.INTEGER;
                    }
                    return typeRegistry.getColumnType(metaInfo);

                }))
                .packageConfig(builder -> {
                    builder.parent("com.example") // 设置父包名
                            .moduleName("iotwarehouse") // 设置父包模块名
                            //默认：实体类放到entity包; mapper接口放到 mapper包，service接口放到 service包下
                            // service接口实现类 service/impl 目录下;contorller控制器类放在controller包下
                            .serviceImpl("serviceImpl") //自定义 service接口实现类 存放在哪个包下
                            .pathInfo(Collections.singletonMap(OutputFile.xml, System.getProperty("user.dir")+"/src/main/resources/mapper")); // 设置mapperXml生成路径
                })
                .strategyConfig(builder -> {
                   /* builder.addInclude("t_student","city") // 设置需要生成的表名
                            .addTablePrefix("t_", "c_"); // 设置过滤表前缀*/
                    builder.addInclude("user","warehouse","shelf","permission","operation_log","inventory","goods","environment","device") // 设置需要生成的表名
                            .addTablePrefix("t_", "c_"); // 设置过滤表前缀
                })
                .templateEngine(new FreemarkerTemplateEngine()) // 使用Freemarker引擎模板，默认的是Velocity引擎模板
                .execute();
        System.out.println("代码生成完毕！...");
    }
}
