package com.example.stripemanager.aspect;


import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;


@Aspect
@Component
public class AroundAspects {
    private static final Logger LOGGER = LoggerFactory.getLogger(AroundAspects.class);


    @Around("execution(* com.example.stripemanager.services.*.*(..))")
    public Object doBasicProfiling(ProceedingJoinPoint pjp) throws Throwable {
        long startTime = System.nanoTime();

        String methodName = pjp.getSignature().getName();
        String className = pjp.getTarget().getClass().getSimpleName();

        Object retVal = pjp.proceed();

        long endTime = System.nanoTime();
        double durationInMilliseconds = (double) (endTime - startTime) / 1_000_000;

        LOGGER.info("The duration time for the execution of the method {} in the class {} took {} milliseconds to execute", methodName, className, durationInMilliseconds);

        return retVal;
    }





}

