package com.example.AopExample.aspect;

import com.example.AopExample.entity.User;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

@Aspect
@Component
@Slf4j
public class LoggingAspect {

    @Pointcut("execution(* com.example.AopExample.service.*.*(..))")
    public void serviceMethods() {}

    @Pointcut("execution(* com.example.AopExample.service.UserService.saveUser(..)) && args(user)")
    public void addUserPointcut(User user) {}

    @Pointcut("execution(* com.example.AopExample.service.UserService.getUser(..))")
    public void getUserPointcut() {}

    @Before("addUserPointcut(user)")
    public void logBefore(JoinPoint joinPoint, User user) {
        System.out.println("Before Advice");
        System.out.println("Method execution started: "+joinPoint.getSignature().getName());
        System.out.println("User Details: "+ user);
    }

    @After("addUserPointcut(user)")
    public void logAfter(JoinPoint joinPoint, User user) {
        System.out.println("After Advice");
        System.out.println("Method execution finished: "+ joinPoint.getSignature().getName());
    }

    @AfterReturning(pointcut = "serviceMethods()", returning = "result")
    public void logAfterReturning(JoinPoint joinPoint, Object result) {
        System.out.printf(" %s returned with value %s\n", joinPoint.getSignature(), result);
    }

    @AfterThrowing(pointcut = "serviceMethods()", throwing = "exception")
    public void logAfterThrowing(JoinPoint joinPoint, Throwable exception) {
        log.error("{} threw an exception: {}", joinPoint.getSignature(), exception != null ? exception : "No exception details available");
    }

    @Around("serviceMethods()")
    public Object logExecutionTime(ProceedingJoinPoint joinPoint) throws Throwable {
        long start = System.currentTimeMillis();

        Object proceed = joinPoint.proceed();

        long executionTime = System.currentTimeMillis() - start;

        System.out.println(joinPoint.getSignature().getName()+" executed in "+ executionTime+"ms");

        return proceed;
    }
}
