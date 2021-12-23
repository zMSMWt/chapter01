package com.zp.integration.common.token;

import javax.servlet.http.HttpServletRequest;

/**
 * @ClassName TokenService
 * @Description token service
 * @Author zp
 * @Date 2021/12/23 5:16
 * @Version 1.0
 */
public interface TokenService {

    /**
     * 创建 token
     * @return
     */
    String createToken() throws Exception;

    /**
     * 校验 token
     * @param request：主要用于获取 header 里面的 token。
     * @return
     * @throws Exception
     */
    boolean checkToken(HttpServletRequest request) throws Exception;
}
