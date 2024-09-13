package com.example.library.aop;

import com.example.library.exceptions.DataNotFoundException;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class ExceptionAspect {

    @AfterThrowing(pointcut = "execution(* com.example.library.service.*.*(..))", throwing = "ex")
    public void handleException(JoinPoint joinPoint, Throwable ex) {
        String methodName = joinPoint.getSignature().getName();

        // Custom exception handling logic
        if (ex instanceof DataNotFoundException) {
            System.out.println("Exception Handling: Data not found in method: " + methodName + ". Message: " + ex.getMessage());
        } else {
            System.out.println("Exception Handling: An error occurred in method: " + methodName + ". Message: " + ex.getMessage());
        }

        // Could send alert emails or other actions here
    }
}
