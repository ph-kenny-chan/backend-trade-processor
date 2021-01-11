package com.currencyfair.demo.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.micrometer.core.instrument.util.StringUtils;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.aop.aspectj.MethodInvocationProceedingJoinPoint;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

@Component
@Aspect
@Slf4j
public class WebLogAspect {

    @Pointcut("execution(* com.currencyfair.demo.controller..*.*(..))")
    private void parameterPointCut() {
    }

    @Before("parameterPointCut()")
    public void requestLog(JoinPoint joinPoint){
        RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();//这个RequestContextHolder是Springmvc提供来获得请求的东西
        if(requestAttributes!=null) {
            HttpServletRequest request = ((ServletRequestAttributes) requestAttributes).getRequest();
            String queryStr = request.getQueryString();
            if (StringUtils.isNotEmpty(queryStr)) {
                log.info("Request URI: [{}] {}", request.getMethod(), request.getRequestURI());
            } else {
                log.info("Request URI: [{}] {}?{} ", request.getMethod(), request.getRequestURI(), queryStr);
            }
        }

        printRequestArgs(joinPoint);
    }

    private void printRequestArgs(JoinPoint joinPoint){
        log.info("Request Method: {}", joinPoint.toString());

        Object[] reqArgs = joinPoint.getArgs();
        if(null == reqArgs){
            return;
        }

        int c = 0;
        ObjectMapper mapper = new ObjectMapper();
        for(Object arg: reqArgs){
            try{
                log.info("Request Parameter[{}]: {}", c, mapper.writeValueAsString(arg));
            }catch (Exception ex){
                log.error("Request abnormal", ex);
            }
            c++;
        }
    }

    @AfterReturning(returning = "ret",pointcut = "parameterPointCut()")
    public Object responseLog(JoinPoint joinPoint, Object ret){
        try {
            ObjectMapper mapper = new ObjectMapper();
            log.info("Response Parameter: {}", mapper.writeValueAsString(ret));
        } catch (Throwable ex) {
            log.error("Response Abnormal", ex);
        }
        return ret;
    }
}
