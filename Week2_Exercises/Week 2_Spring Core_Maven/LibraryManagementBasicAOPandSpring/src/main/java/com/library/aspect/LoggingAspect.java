package com.library.aspect;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Pointcut;

@Aspect
public class LoggingAspect {

    @Pointcut("execution(* com.library.service.*.*(..))")
    private void forServicePackage() {}

    @Before("forServicePackage()")
    public void beforeMethodExecution() {
        System.out.println("Before method execution");
    }

    @After("forServicePackage()")
    public void afterMethodExecution() {
        System.out.println("After method execution");
    }
}
