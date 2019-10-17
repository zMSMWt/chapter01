package com.zp.chapter01.common.util;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTCreator;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.zp.chapter01.common.Enum.ErrorCode;
import com.zp.chapter01.common.entity.Result;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class JwtTokenUtils {

    public static final String TOKEN_HEADER = "Authorization";
    public static final String TOKEN_PREFIX = "qmcy";

    private static final String SECRET = "jwtsecretdemo";
    private static final String ISS = "sys_admin";

    // 过期时间是 3600 秒，即 1 小时
    private static final long EXPIRATION = 3600L;

    // 过期时间(毫秒)，即 10 分钟
    private static final long ttlMillis = 60 * 10 * 1000;

    // 选择了记住我之后的过期时间为 7 天
    private static final long EXPIRATION_REMEMBER = 604800L;

    /**
     * 方式一：创建 token (依赖包 com.auth0)
     * @param claims
     * @return
     */
    public static String createJwtToken(Map<String, String> claims){
        // 生成时间
        long nowMills = System.currentTimeMillis();
        long expMillis = nowMills + ttlMillis;
        Date exp = new Date(expMillis);

        //使用 HMAC256 进行加密
        Algorithm algorithm = Algorithm.HMAC256(SECRET);

        // 创建 jwt
        JWTCreator.Builder builder = JWT.create()
                .withIssuer(ISS)  //发行人
                .withExpiresAt(exp); //过期时间点

        // 传参（lambda 表达式）
        claims.forEach((key, value) ->{
            builder.withClaim(key, value);
        });

        return builder.sign(algorithm);
    }

    /**
     * 验证 token
     * @param token
     * @return
     */
    public static Result verificationToken(String token){

        Algorithm algorithm = null;
        try{
            // 使用 HMAC256 进行加密
            algorithm = Algorithm.HMAC256(SECRET);
        }catch (IllegalArgumentException e){
            return Result.wrapErrorResult(ErrorCode.TOKEN_EXPIRED.getId().toString(), ErrorCode.TOKEN_EXPIRED.getName());
        }

        // 解密
        JWTVerifier verifier = JWT.require(algorithm).withIssuer(ISS).build();
        DecodedJWT jwt = verifier.verify(token);
        Map<String, Claim> map = jwt.getClaims();
        Map<String, String> resultMap = new HashMap<>();
        map.forEach((k, v) -> resultMap.put(k, v.asString()));

        return Result.wrapSuccessfulResult(resultMap);
    }

    /**
     * 方式二：创建 token (依赖包 io.jsonwebtoken)
     * @param username
     * @param isRememberMe
     * @return
     */
    public static String createToken(String username, boolean isRememberMe){

        long expiration = isRememberMe ? EXPIRATION_REMEMBER : EXPIRATION;

        return Jwts.builder()
                .signWith(SignatureAlgorithm.HS256, SECRET)
                .setIssuer(ISS)
                .setSubject(username)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + expiration * 1000))
                .compact();
    }

    // 从 token 中获取用户名
    public static String getUsername(String token){
        return getTokenBody(token).getSubject();
    }

    // 是否已过期
    public static boolean isExpiration(String token){
        return getTokenBody(token).getExpiration().before(new Date());
    }

    private static Claims getTokenBody(String token){
        return Jwts.parser()
                .setSigningKey(SECRET)
                .parseClaimsJws(token)
                .getBody();
    }

}
