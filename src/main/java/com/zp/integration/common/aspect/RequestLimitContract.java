package com.zp.integration.common.aspect;

import com.zp.integration.common.annotation.RequestLimit;
import com.zp.integration.common.exception.RequestLimitException;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

/**
 * @before 注解代表在请求发送到控制器之前会先来到这里执行相应的内容，
 * within 里面的书写表示写在控制器上方并且有对应注解的控制器会来到这里。
 *
 * 在获得 ip 和对应的 url 之后将它作为一个 key，存放到 map 中，
 * 假如 map 中已经存在了这个 key，那么多产生一个时间处理器，
 * 当时间超过注解书写的单位时间之后又会将这个 key 从 map 中移走。
 *
 * 假如访问的次数超过了限制，将会抛出异常说明访问次数过多。
 */
@Aspect
@Component
public class RequestLimitContract {

    private static final Logger logger = LoggerFactory.getLogger("requestLimitLogger");
    private Map<String, Integer> redisTemplate = new HashMap<>();

    @Before("within(@org.springframework.stereotype.Controller *) && @annotation(limit)")
    public void requestLimit(final JoinPoint joinPoint, RequestLimit limit) throws RequestLimitException {
        try {
            Object[] args = joinPoint.getArgs();
            HttpServletRequest request = null;
            for (int i = 0; i < args.length; i++) {
                if (args[i] instanceof HttpServletRequest) {
                    request = (HttpServletRequest) args[i];
                    break;
                }
            }
            if (request == null) {
                throw new RequestLimitException("方法中缺失 HttpServletRequest 参数");
            }
            String ip = request.getLocalAddr();
            String url = request.getRequestURL().toString();
            String key = "req_limit_".concat(url).concat(ip);
            if (redisTemplate.get(key) == null || redisTemplate.get(key) == 0) {
                redisTemplate.put(key, 1);
            } else {
                redisTemplate.put(key, redisTemplate.get(key) + 1);
            }
            int count = redisTemplate.get(key);
            if (count > 0) {
                // 创建一个定时器
                Timer timer = new Timer();
                TimerTask timerTask = new TimerTask() {
                    @Override
                    public void run() {
                        redisTemplate.remove(key);
                    }
                };
                // 此定时器设定在 timer 规定的时间之后会执行上面的 remove 方法，也即在此时间后可以重新访问
                timer.schedule(timerTask, limit.time());
            }
            if (count > limit.count()) {
                logger.info("用户 IP[" + ip + "]访问地址[" + url + "]超过了限定的次数[" + limit.count() + "]");
                throw new RequestLimitException();
            }
        } catch (RequestLimitException e) {
            throw e;
        } catch (Exception e) {
            logger.error("发生异常", e);
        }
    }

}
