package com.example.library.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class SecurityAspect {

    @Before("execution(* com.example.library.service.*.*(..))")
    public void checkAccess(JoinPoint joinPoint) throws SecurityException {
        String methodName = joinPoint.getSignature().getName();
        String userRole = getCurrentUserRole(); // Assume method to get user role

        // Custom rule: only admins can delete entities
        if (methodName.startsWith("delete") && !userRole.equals("ADMIN")) {
            throw new SecurityException("Access Denied: Only admins can delete resources.");
        }

        System.out.println("Access Granted for: " + methodName + " to role: " + userRole);
    }

    private String getCurrentUserRole() {
        // For demo purposes, assume "USER" or "ADMIN"
        return "USER"; // Mock role
    }
}
