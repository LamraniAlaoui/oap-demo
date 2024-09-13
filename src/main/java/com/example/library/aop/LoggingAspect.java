import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Aspect
@Component
public class LoggingAspect {

    @Before("execution(* com.example.library.service.*.*(..))")
    public void logBefore(JoinPoint joinPoint) {
        String methodName = joinPoint.getSignature().getName();
        String className = joinPoint.getTarget().getClass().getSimpleName();
        Object[] args = joinPoint.getArgs();

        System.out.println("LOG - [BEFORE]: Class: " + className + ", Method: " + methodName + ", Args: " + Arrays.toString(args));
    }

    @AfterReturning(value = "execution(* com.example.library.service.*.*(..))", returning = "result")
    public void logAfterReturning(JoinPoint joinPoint, Object result) {
        String methodName = joinPoint.getSignature().getName();
        System.out.println("LOG - [AFTER]: Method: " + methodName + " Result: " + result);
    }

    @AfterThrowing(value = "execution(* com.example.library.service.*.*(..))", throwing = "exception")
    public void logException(JoinPoint joinPoint, Throwable exception) {
        String methodName = joinPoint.getSignature().getName();
        System.out.println("LOG - [EXCEPTION]: Method: " + methodName + " Exception: " + exception.getMessage());
    }
}
