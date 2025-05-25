package com.luv2code.aopdemo.aspect;

import com.luv2code.aopdemo.Account;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;



@Aspect
@Component
@Order(2)
public class MyDemoLoggingAspect {
    // @Before("execution(* add*(..))") // To match all method that has prefix add, but might crash in IntelliJ Ultimate
    @Before("com.luv2code.aopdemo.aspect.LuvAopExpressions.forDaoPackageNoGetterSetter()") // To match all methods of a package
    public void beforeAddAccountAdvice(JoinPoint theJoinPoint) {
        System.out.println("\n======>>> Executing @Before advice on addAccount()");

        // display method signature
        MethodSignature methodSignautre = (MethodSignature) theJoinPoint.getSignature();

        System.out.println("Method: " + methodSignautre);

        // display method arguments
        Object[] args = theJoinPoint.getArgs();
        for(Object tempArg: args){
            System.out.println(tempArg);
            if(tempArg instanceof Account) {
                Account theAccount = (Account) tempArg;

                System.out.println("account name: "+ theAccount.getName());
                System.out.println("account name: "+ theAccount.getLevel());
            }
        }

    }




}
