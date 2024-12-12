package com.cubes4.CUBES4.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * @author Maël NOUVEL <br>
 * 12/2024
 **/
@Aspect
@Component
public class LogAspect {
    private static final Logger LOGGER = LoggerFactory.getLogger(LogAspect.class);

    @Before("@within(com.cubes4.CUBES4.annotation.Loggable) || @annotation(com.cubes4.CUBES4.annotation.Loggable)")
    public void logMethodEntry(JoinPoint joinPoint) {
        LOGGER.info("Entering method: {} with arguments: {}",
                joinPoint.getSignature().toShortString(), joinPoint.getArgs());
    }

    @AfterReturning(pointcut = "@within(com.cubes4.CUBES4.annotation.Loggable) || @annotation(com.cubes4.CUBES4.annotation.Loggable)",
            returning = "result")
    public void logMethodExit(JoinPoint joinPoint, Object result) {
        LOGGER.info("Exiting method: {} with return value: {}",
                joinPoint.getSignature().toShortString(), result);
    }
}
