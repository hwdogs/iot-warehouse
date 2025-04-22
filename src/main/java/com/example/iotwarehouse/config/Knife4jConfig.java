package com.example.iotwarehouse.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.servers.Server;
import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

/**
 * Knife4j配置类
 * 
 * @author hwshou
 * @date 2025/4/22 22:19
 */
@Configuration
public class Knife4jConfig {

    @Value("${server.servlet.context-path}")
    private String contextPath;

    /**
     * 仓库管理相关接口
     */
    @Bean
    public GroupedOpenApi warehouseApi() {
        return GroupedOpenApi.builder()
                .group("仓库管理接口")
                .pathsToMatch("/warehouse/**")
                .build();
    }

    /**
     * 商品管理相关接口
     */
    @Bean
    public GroupedOpenApi goodsApi() {
        return GroupedOpenApi.builder()
                .group("商品管理接口")
                .pathsToMatch("/goods/**")
                .build();
    }

    /**
     * 库存管理相关接口
     */
    @Bean
    public GroupedOpenApi inventoryApi() {
        return GroupedOpenApi.builder()
                .group("库存管理接口")
                .pathsToMatch("/inventory/**")
                .build();
    }

    /**
     * 全局接口
     */
    @Bean
    public GroupedOpenApi allApi() {
        return GroupedOpenApi.builder()
                .group("全部接口")
                .pathsToMatch("/**")
                .build();
    }

    /**
     * 自定义API文档信息
     */
    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                // 添加服务器地址，解决context-path问题
                .servers(List.of(new Server().url(contextPath).description("IOT智能仓库管理系统")))
                .info(new Info()
                        .title("IOT智能仓库管理系统API文档")
                        .description("本文档提供系统所有API接口说明")
                        .version("1.0.0")
                        .contact(new Contact()
                                .name("IOT-Warehouse研发组")
                                .email("admin@iot-warehouse.com")
                                .url("https://github.com/hwshou"))
                        .license(new License()
                                .name("Apache 2.0")
                                .url("http://www.apache.org/licenses/LICENSE-2.0.html")));
    }
}
