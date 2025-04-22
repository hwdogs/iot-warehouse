package com.example.iotwarehouse;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * IOT智能仓库管理系统启动类
 * 
 * @author hwshou
 * @since 2025-04-21
 */
@SpringBootApplication
@MapperScan("com.example.iotwarehouse.mapper")
@ComponentScan(basePackages = { "com.example.iotwarehouse" })
public class IotWarehouseApplication {

    public static void main(String[] args) {
        SpringApplication.run(IotWarehouseApplication.class, args);
        System.out.println("系统启动成功！");
        System.out.println("API文档Knife4j地址: http://localhost:8080/iot-warehouse/doc.html");
        System.out.println("API文档Swagger地址: http://localhost:8080/iot-warehouse/swagger-ui/index.html");
    }

}
