package com.zp.integration.common.token.impl;

import com.zp.integration.common.Enum.ErrorCode;
import com.zp.integration.common.entity.Constant;
import com.zp.integration.common.token.TokenService;
import com.zp.integration.common.util.RedisUtil;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.text.StrBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.util.UUID;

/**
 * @ClassName TokenServiceImpl
 * @Description token service implement
 * @Author zp
 * @Date 2021/12/23 5:27
 * @Version 1.0
 */

@Component
public class TokenServiceImpl implements TokenService {

    @Autowired
    private RedisUtil redisUtil;

    /**
     * 创建 token
     * 采用随机数算法生成随机 uuid 字符串，然后放到 redis 中
     * （为防止数据的冗余保留，设置过期时间为 10000 秒，具体视业务而定）
     * @return
     * @throws Exception
     */
    @Override
    public String createToken() throws Exception {
        String string = UUID.randomUUID().toString();
        StrBuilder token = new StrBuilder();
        try{
            token.append(Constant.Redis.TOKEN_PREFIX.getName()).append(string);
            redisUtil.setEx(token.toString(), token.toString(), 10000L);
            boolean notEmpty = StringUtils.isNotEmpty(token.toString());
            if (notEmpty) {
                return token.toString();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 从 header 中获取 token 的值（如果 header 中拿不到，就从 parameter 中获取），如果不存在，直接抛异常。
     *
     * @param request：主要用于获取 header 里面的 token。
     * @return
     * @throws Exception
     */
    @Override
    public boolean checkToken(HttpServletRequest request) throws Exception {
        String token = request.getHeader(Constant.TOKEN_NAME);
        if (StringUtils.isBlank(token)) {
            // header 中不存在 token 时
            token = request.getParameter(Constant.TOKEN_NAME);
            if (StringUtils.isBlank(token)) {
                // parameter 中也不存在 token 时，抛异常
                throw new RuntimeException(ErrorCode.ILLEGAL_ARGUMENT.getName());
            }
        }
        if (!redisUtil.exists(token)) {
            throw new RuntimeException(ErrorCode.REPETITIVE_OPERATION.getName());
        }
        boolean remove = redisUtil.remove(token);
        if (!remove) {
            throw new  RuntimeException(ErrorCode.REPETITIVE_OPERATION.getName());
        }
        return true;
    }
}
