package com.imooc.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * swagger2相关配置
 *
 * http://localhost:8088/swagger-ui.html     官方原路径
 * http://localhost:8088/doc.html     github自定义组件原路径
 */
@Configuration
// 开启swagger2
@EnableSwagger2
public class Swagger2Config {


    // 配置swagger2核心配置, docket
    @Bean
    public Docket createRestApi() {
        // 响应式编程风格

        return new Docket(DocumentationType.SWAGGER_2) // 指定api类型为swagger2
                // 定义api文档汇总信息
                .apiInfo(apiInfo())
                .select()
                // 扫描包所在地址指定controller包. 选择器
                .apis(RequestHandlerSelectors.basePackage("com.imooc.controller"))
                // 所有controller
                .paths(PathSelectors.any())
                .build();
    }

    /**
     * 定义api文档汇总信息
     *
     * @return ApiInfo
     */
    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("foodie-dev api")        // 文档页标题
                .contact(new Contact("imooc",
                        "https://www.imooc.com",
                        "abc@imooc.com"))        // 联系人信息
                .description("foodie-dev api dec")  // 详细信息
                .version("1.0.1")   // 文档版本号
                .termsOfServiceUrl("https://www.imooc.com") // 网站地址
                .build();
    }




}

