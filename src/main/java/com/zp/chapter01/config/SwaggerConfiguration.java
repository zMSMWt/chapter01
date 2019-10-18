package com.zp.chapter01.config;

import com.zp.chapter01.controller.CommonRequestData;
import io.swagger.annotations.ApiModelProperty;
import org.apache.commons.lang3.reflect.FieldUtils;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.ParameterBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Parameter;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Configuration
@EnableSwagger2
public class SwaggerConfiguration {

    @Bean
    public Docket createRestApi() {
        List<Parameter> pars = new ArrayList<Parameter>();

        //初始化公共header参数
        Arrays.asList(FieldUtils.getAllFields(CommonRequestData.class)).forEach(
                item -> {
                    ApiModelProperty annotation = item.getAnnotation(ApiModelProperty.class);
                    if (annotation != null) {
                        pars.add(new ParameterBuilder()
                                .name(CommonRequestData.HEADER_PREFIX + item.getName())
                                .description(annotation.value())
                                .modelRef(new ModelRef(annotation.dataType())).parameterType("header")
                                .required(false).hidden(true).defaultValue(annotation.example()).build());
                    }
                });

        Arrays.asList(FieldUtils.getAllFields(CommonRequestData.SensorsCommonParam.class)).forEach(
                item -> {

                    ApiModelProperty annotation = item.getAnnotation(ApiModelProperty.class);
                    if (annotation != null) {
                        pars.add(new ParameterBuilder()
                                .name(CommonRequestData.SENSOR_PREFIX + item.getName())
                                .description(annotation.value())
                                .modelRef(new ModelRef(annotation.dataType())).parameterType("header")
                                .hidden(true).required(false).defaultValue(annotation.example()).build());
                    }

                });

//        pars.add(new ParameterBuilder().name(JWTConfigurer.AUTHORIZATION_HEADER)
//                .description("请求令牌").modelRef(new ModelRef("string")).parameterType("header")
//                .hidden(false).required(false).defaultValue("").build());

        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("REST_API")
                .apiInfo(apiInfo("rest api"))
                .select()
//            .apis(RequestHandlerSelectors.basePackage("com.zp.chapter01.controller"))
//            .paths(PathSelectors.ant("/**")).build().globalOperationParameters(pars);

                /* 本地测试使用防止误提交加注释 */
                .apis(RequestHandlerSelectors.basePackage("com.zp.chapter01.controller"))
                .paths(PathSelectors.ant("/**")).build().globalOperationParameters(pars);
    }

    @Bean
    public Docket createServiceFacadeAPI() {
        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("SERVICE_REST_API")
                .apiInfo(apiInfo("service rest api"))
                .select()
                .apis(
                        RequestHandlerSelectors
                                .basePackage("com.zp.chapter01.service"))
                .paths(PathSelectors.ant("/services/**")).build();
    }

    private ApiInfo apiInfo(String title) {
        return new ApiInfoBuilder()
                // 文档标题
                .title(title)
                // 文档描述
                .description("SpringBoot 利用 Swagger 构建 API 文档")
                .termsOfServiceUrl("服务 url")
                .version("0.0.1")
                .build();
    }
}
