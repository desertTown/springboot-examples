package com.evan.config;

import com.google.common.base.Predicate;
import io.swagger.annotations.ApiOperation;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.request.async.DeferredResult;
import springfox.documentation.RequestHandler;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.ParameterBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.service.Parameter;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

import java.util.ArrayList;
import java.util.List;


@Configuration
public class Swagger2Config {

    @Bean
    public Docket createRestDevApi() {
        //header中增加 token_id参数，没有可以去掉
        ParameterBuilder token = new ParameterBuilder();
        List<Parameter> pars = new ArrayList<Parameter>();
        token.name("token_id").description("user token")
                .modelRef(new ModelRef("string")).parameterType("header")
                .required(false).build();
        pars.add(token.build());
        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("dev")
                .apiInfo(apiInfo_dev())
                .select()
                // 指定controller存放的目录路径
                .apis(RequestHandlerSelectors.basePackage("com.evan.controller"))
                .paths(PathSelectors.any())
                .build()
                .globalOperationParameters(pars);
    }

    @Bean
    public Docket createRestProdApi() {


        Predicate<RequestHandler> predicate = new Predicate<RequestHandler>() {
            @Override
            public boolean apply(RequestHandler input) {
//                Class<?> declaringClass = input.declaringClass();
//                if (declaringClass == BasicErrorController.class)// 排除
//                    return false;
//                if(declaringClass.isAnnotationPresent(ApiOperation.class)) // 被注解的类
//                    return true;
//                if(input.isAnnotatedWith(ResponseBody.class)) // 被注解的方法
//                    return true;
                if (input.isAnnotatedWith(ApiOperation.class))//只有添加了ApiOperation注解的method才在API中显示
                    return true;
                return false;
            }
        };


        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("prod")
                .genericModelSubstitutes(DeferredResult.class)
//              .genericModelSubstitutes(ResponseEntity.class)
                .useDefaultResponseMessages(false)
                .forCodeGeneration(false)
                .select()
                .apis(predicate)
                .paths(PathSelectors.any())//过滤的接口
                .build()
                .apiInfo(apiInfo_prod());
    }


    private ApiInfo apiInfo_dev() {
        return new ApiInfoBuilder()
                // 文档标题
                .title("这里是DEV文档标题")
                // 文档描述
                .description("这里DEV是文档描述")
                .termsOfServiceUrl("这里可以增加DEV需求文档 wiki 地址")
                .version("v1")//定义版本号
                .build();
    }

    private ApiInfo apiInfo_prod() {

        return new ApiInfoBuilder()
                // 文档标题
                .title("这里是PROD文档标题")
                // 文档描述
                .description("这里PROD是文档描述")
                .termsOfServiceUrl("这里可以增加PROD需求文档 wiki 地址")
                .version("v1")//定义版本号
                .contact(new Contact("evan", "www.evan.com","huangev2@gmail.com"))
                .license("The Apache License, Version 2.0")
                .licenseUrl("http://www.apache.org/licenses/LICENSE-2.0.html")
                .build();
    }


}
