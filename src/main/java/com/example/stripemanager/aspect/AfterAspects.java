package com.example.stripemanager.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class AfterAspects {
    private static final Logger LOGGER = LoggerFactory.getLogger(AfterAspects.class);

    @AfterReturning("execution (* com.example.stripemanager.*.*.*(..))")
    public void afterReturning(JoinPoint jp) {
        String className = jp.getTarget().getClass().getSimpleName();
        String methodName = jp.getSignature().getName();
        LOGGER.info(" After Returning: From method : {} in class : {} ", methodName , className );
    }

}
