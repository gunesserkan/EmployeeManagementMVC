package com.gunes.employee.aspect;


import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

import java.util.logging.Logger;

@Aspect
@Component
public class LoggingAspect {
    //setup logger
    private Logger logger = Logger.getLogger(getClass().getName());

    @Pointcut("execution(* com.gunes.employee.controller.*.*(..))")
    private void forControllerPackage() {
    }

    @Pointcut("execution(* com.gunes.employee.service.*.*(..))")
    private void forServicePackage() {
    }

    @Pointcut("execution(* com.gunes.employee.dao.*.*(..))")
    private void forDaoPackage() {
    }

    @Pointcut("forControllerPackage()||forServicePackage()||forDaoPackage()")
    private void forAppFlow() {
    }

    @Before("forAppFlow()")
    public void logBefore(JoinPoint joinPoint) {
        String theMethod = joinPoint.getSignature().toShortString();
        logger.info("in @Before: calling method: " + theMethod);
        Object[] args = joinPoint.getArgs();
        for (Object arg : args) {
            logger.info("argument:" + arg);
        }
    }

    @AfterReturning(
            pointcut = "forAppFlow()",
            returning = "returnValue"
    )
    public void afterReturning(JoinPoint joinPoint, Object returnValue) {
        String theMethod = joinPoint.getSignature().toShortString();
        logger.info("in @AfterReturning: calling method: " + theMethod);
        logger.info("result:" + returnValue);
    }
}
