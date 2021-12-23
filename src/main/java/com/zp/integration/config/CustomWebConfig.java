package com.zp.integration.config;

import com.zp.integration.common.handler.AutoIdempotentInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import javax.annotation.Resource;

/**
 * @ClassName CustomWebConfig
 * @Description 自定义 web 配置类
 * @Author zp
 * @Date 2021/12/23 7:40
 * @Version 1.0
 *
 * 实现 WebMvcConfigurerAdapter，主要作用是添加 autoIdempotentInterceptor 到配置类中，使拦截器生效。
 */

@Configuration
public class CustomWebConfig extends WebMvcConfigurerAdapter {

    @Resource
    private AutoIdempotentInterceptor autoIdempotentInterceptor;

    /**
     * 添加拦截器
     * @param registry
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(autoIdempotentInterceptor);
        super.addInterceptors(registry);
    }
}
