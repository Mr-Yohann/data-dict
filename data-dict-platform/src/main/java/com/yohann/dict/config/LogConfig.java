package com.yohann.dict.config;

import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.time.Duration;
import java.time.Instant;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * <p>
 * 日志配置
 * </p>
 *
 * @author Yohann
 */
@Aspect
@Slf4j
@Component
public class LogConfig {
    @Pointcut("execution(* com.yohann..*Controller.*(..)) && " +
            "(@annotation(org.springframework.web.bind.annotation.RequestMapping) ||" +
            "@annotation(org.springframework.web.bind.annotation.GetMapping) ||" +
            "@annotation(org.springframework.web.bind.annotation.PostMapping) ||" +
            "@annotation(org.springframework.web.bind.annotation.PutMapping) ||" +
            "@annotation(org.springframework.web.bind.annotation.DeleteMapping))")
    public void pointCut() {
    }

    @Around("pointCut()")
    public Object log(ProceedingJoinPoint pjp) throws Throwable {
        String requestId = UUID.randomUUID().toString().replace("-", "");
        Instant begin = Instant.now();
        Object[] args = pjp.getArgs();
        Object target = pjp.getTarget();
        String className = target.getClass().getName();
        MethodSignature signature = (MethodSignature) pjp.getSignature();
        String methodName = signature.getName();
        String[] parameterNames = signature.getParameterNames();
        Map<String, Object> paramMap = buildParamMap(args, parameterNames);
        log.info("\n\n" +
                "<=====================================================================================================================>\n" +
                "<===== 请求开始，请求id：{}\n" +
                "<===== 执行方法：{}\n" +
                "<===== 请求参数：{}\n" +
                "<=====================================================================================================================>\n", requestId, className + "#" + methodName, JSON.toJSONString(paramMap));
        Object proceed;
        try {
            // before
            proceed = pjp.proceed();
            Instant end = Instant.now();
            log.info("\n\n" +
                    "<=====================================================================================================================>\n" +
                    "<===== 请求结束，请求id：{}\n" +
                    "<===== 本次请求花费时间：{}ms\n" +
                    "<=====================================================================================================================>\n", requestId, Duration.between(begin, end).toMillis());
            // after
            return proceed;
            // return
        } catch (Throwable e) {
            Instant end = Instant.now();
            log.info("\n\n" +
                    "<=====================================================================================================================>\n" +
                    "<===== 请求因异常结束，请求id：{}\n" +
                    "<===== 本次请求花费时间：{}ms\n" +
                    "<=====================================================================================================================>\n", requestId, Duration.between(begin, end).toMillis());
            // throwing
            throw e;
        }
    }

    private Map<String, Object> buildParamMap(Object[] args, String[] parameterNames) {
        Map<String, Object> paramMap = new HashMap<>();
        for (int i = 0; i < args.length; i++) {
            if (args[i] instanceof HttpServletRequest
                    || args[i] instanceof HttpServletResponse
                    || args[i] instanceof MultipartFile
                    || args[i] instanceof MultipartFile[]) {
                continue;
            }
            paramMap.put(parameterNames[i], args[i]);
        }
        return paramMap;
    }
}