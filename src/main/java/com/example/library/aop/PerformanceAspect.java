package com.example.library.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class PerformanceAspect {

    @Around("execution(* com.example.library.service.*.*(..))")
    public Object monitorPerformance(ProceedingJoinPoint joinPoint) throws Throwable {
        long startTime = System.nanoTime();
        Object result = joinPoint.proceed();
        long endTime = System.nanoTime();

        long duration = (endTime - startTime) / 1_000_000; // Convert to milliseconds
        System.out.println("Performance Monitoring: Method: " + joinPoint.getSignature().getName() + " executed in: " + duration + "ms");

        // Custom logic: alert if method exceeds a threshold (e.g., 500ms)
        if (duration > 500) {
            System.out.println("ALERT: Method " + joinPoint.getSignature().getName() + " took too long: " + duration + "ms");
        }

        return result;
    }
}
