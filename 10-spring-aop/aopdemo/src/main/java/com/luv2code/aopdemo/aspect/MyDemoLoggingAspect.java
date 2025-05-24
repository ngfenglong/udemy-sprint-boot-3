package com.luv2code.aopdemo.aspect;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class MyDemoLoggingAspect {

    // @Before("execution(* add*(..))") // To match all method that has prefix add, but might crash in IntelliJ Ultimate
    @Before("execution(* com.luv2code.aopdemo.dao.*.*(..))") // To match all methods of a package
    public void beforeAddAccountAdvice(){
        System.out.println("\n======>>> Executing @Before advice on addAccount()");
    }
}
