package com.example.library.aop;

import com.example.library.aop.annotations.CustomAnnotation;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class CustomAnnotationAspect {

    @Before("@annotation(customAnnotation)")
    public void handleCustomAnnotation(JoinPoint joinPoint, CustomAnnotation customAnnotation) {
        String value = customAnnotation.value();
        System.out.println("Custom Annotation Logic: Processing annotation with value: " + value);

        // Custom logic: Act based on the value of the annotation
        if (value.equals("ENFORCE_STRICT_RULES")) {
            System.out.println("Enforcing strict rules for method: " + joinPoint.getSignature().getName());
        }
    }
}
