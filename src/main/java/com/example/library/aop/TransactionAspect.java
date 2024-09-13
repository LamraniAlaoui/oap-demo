package com.example.library.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class TransactionAspect {

    @Around("execution(* com.example.library.service.*.*(..))")
    public Object manageTransaction(ProceedingJoinPoint joinPoint) throws Throwable {
        String methodName = joinPoint.getSignature().getName();
        boolean rollback = false;

        System.out.println("Transaction Started for: " + methodName);

        try {
            Object result = joinPoint.proceed();

            // Custom logic: commit transaction only for save methods
            if (methodName.startsWith("save") || methodName.startsWith("create")) {
                System.out.println("Transaction Committed for: " + methodName);
            } else {
                System.out.println("Transaction Skipped Commit for: " + methodName);
            }
            return result;
        } catch (Exception e) {
            rollback = true;
            System.out.println("Transaction Rolled Back for: " + methodName + " due to error: " + e.getMessage());
            throw e;
        } finally {
            if (!rollback) {
                System.out.println("Transaction Finalized for: " + methodName);
            }
        }
    }
}
