package com.luv2code.springdemo.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import java.util.logging.Logger;

@Aspect
@Component
public class CRMLoggingAspect {
    // setup logger
    private Logger myLogger = Logger.getLogger(getClass().getName());

    // setup pointcut declarations
    @Pointcut("execution(* com.luv2code.springdemo.controller.*.*(..))")
    private void forControllerPackage() {}

    // setup pointcut declarations
    @Pointcut("execution(* com.luv2code.springdemo.service.*.*(..))")
    private void forServicePackage() {}

    // setup pointcut declarations
    @Pointcut("execution(* com.luv2code.springdemo.dao.*.*(..))")
    private void forDaoPackage() {}

    @Pointcut("forControllerPackage() || forServicePackage() || forDaoPackage()")
    private void forAppFlow() {}


    // add @Before advice
    @Before("forAppFlow()")
    public void before(JoinPoint theJoinPoint) {
        // display the method we are calling
        String theMethod = theJoinPoint.getSignature().toShortString();
        myLogger.info("----> in @Before, calling method: " + theMethod);

        // display the argument to the method
        Object[] args = theJoinPoint.getArgs();
        // get the arguments
        for (Object x : args) {
            myLogger.info("======> argument: " + x);
        }
    }

    @AfterReturning(
            pointcut = "forAppFlow()",
            returning = "theResult"
        )
    public void afterReturning(JoinPoint theJoinPoint, Object theResult) {
        // display the method we are calling
        String theMethod = theJoinPoint.getSignature().toShortString();
        myLogger.info("----> in @AfterReturning from method: " + theMethod);

        //display data returned
        myLogger.info("====>>>>>  result: " + theResult);

    }




    // add @AfterReturning advice


}
