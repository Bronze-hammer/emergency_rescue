package com.china.rescue.framework.swagger.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.ParameterBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;
import springfox.documentation.service.Parameter;

import java.util.ArrayList;
import java.util.List;

/**
 * Swagger2配置类
 *
 * @author : xbronze
 * @date : 2022-06-30  14:54
 */

@Configuration
@EnableSwagger2
public class Swagger2Config {

    @Bean
    public Docket createRestApi() {
        ParameterBuilder ticketPar = new ParameterBuilder();
        List<Parameter> pars = new ArrayList<>();

        //Token 以及Authorization 为自定义的参数，session保存的名字是哪个就可以写成那个
        ticketPar.name("Authorization").description("user ticket")
                .modelRef(new ModelRef("string")).parameterType("header")
                //header中的ticket参数非必填，传空也可以
                .required(false).build();
        pars.add(ticketPar.build());

        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.china.rescue.controller"))
                .paths(PathSelectors.any())
                .build()
                .globalOperationParameters(pars);
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("Emergency Rescue Project")
                .description("Emergency Rescue Project RESTful APIs")
                .contact(new Contact("xuzihui", "xuzihui.cn", "xuzihui.cn@gmail.com"))
//                .termsOfServiceUrl("")  // 服务条款
                .version("1.0")
                .build();
    }

}
