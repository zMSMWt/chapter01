package com.zp.integration.common.handler;

import com.alibaba.fastjson.JSON;
import com.zp.integration.common.annotation.AutoIdempotent;
import com.zp.integration.common.entity.Result;
import com.zp.integration.common.token.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Method;

/**
 * @ClassName AutoIdempotentInterceptor
 * @Description 自定义拦截器
 * @Author zp
 * @Date 2021/12/23 7:44
 * @Version 1.0
 *
 * 主要功能是拦截扫描到 @AutoIdempotent 注解的方法，然后调用 tokenService 的校验方法校验 token 是否正确，
 * 如果哦捕捉到异常信息渲染成 json 返回给前端。
 */

@Component
public class AutoIdempotentInterceptor implements HandlerInterceptor {

    @Autowired
    private TokenService tokenService;

    /**
     * 预处理
     * @param httpServletRequest
     * @param httpServletResponse
     * @param o
     * @return
     * @throws Exception
     */
    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o) throws Exception {
        if (!(o instanceof HandlerMethod)) {
            return true;
        }
        HandlerMethod handlerMethod = (HandlerMethod) o;
        Method method = handlerMethod.getMethod();
        // 被 AutoIdempotent 标记的扫描
        AutoIdempotent methodAnnotation = method.getAnnotation(AutoIdempotent.class);
        if (methodAnnotation != null) {
            try {
                // 幂等性校验，校验通过则放行，校验失败则抛异常，并通过统一异常处理返回友好提示
                return tokenService.checkToken(httpServletRequest);
            } catch (Exception e) {
                Result failedResult = Result.wrapErrorResult("999999", e.getMessage());
                writeReturnJson(httpServletResponse, JSON.toJSONString(failedResult));
                throw e;
            }
        }
        // 必须返回 true，否则会被拦截一切请求
        return true;
    }

    /**
     * 返回的 json 值
     * @param httpServletResponse
     * @param json
     */
    private void writeReturnJson(HttpServletResponse httpServletResponse, String json) {
        PrintWriter writer = null;
        httpServletResponse.setContentType("text/html;charset=utf-8");
        try {
            writer = httpServletResponse.getWriter();
            writer.print(json);
        } catch (IOException e) {
        } finally {
            if (writer != null) {
                writer.close();
            }
        }
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {

    }
}
