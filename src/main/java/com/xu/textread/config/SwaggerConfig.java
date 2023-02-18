package com.xu.textread.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2WebMvc;


/**
 * @author aniki
 */
@Configuration
@EnableSwagger2WebMvc
public class SwaggerConfig {

    @Bean(value = "defaultApi2")
    public Docket defaultApi2() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(adminApiInfo())
                .groupName("adminApi")
                .select()
                //只显示admin下面的路径
                .apis(RequestHandlerSelectors.basePackage("com.xu.textread.controller"))
                .build();
    }

    private ApiInfo adminApiInfo() {
        return new ApiInfoBuilder()
                .title("图书阅读")
                .description("图书阅读文档描述")
                .version("1.0")
                .build();
    }
}
