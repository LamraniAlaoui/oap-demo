package com.example.library.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

import java.util.Date;

@Aspect
@Component
public class AuditingAspect {

    @Before("execution(* com.example.library.repository.*.save(..))")
    public void auditChanges(JoinPoint joinPoint) {
        Object[] args = joinPoint.getArgs();
        if (args.length > 0 && args[0] instanceof Auditable) {
            Auditable entity = (Auditable) args[0];
            if (entity.getCreatedAt() == null) {
                entity.setCreatedAt(new Date());
                System.out.println("Audit: Created at timestamp set for entity.");
            } else {
                entity.setUpdatedAt(new Date());
                System.out.println("Audit: Updated at timestamp set for entity.");
            }
        }
    }
}

interface Auditable {
    Date getCreatedAt();
    void setCreatedAt(Date createdAt);
    Date getUpdatedAt();
    void setUpdatedAt(Date updatedAt);
}

