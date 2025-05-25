package com.luv2code.aopdemo.aspect;

import com.luv2code.aopdemo.Account;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.List;


@Aspect
@Component
@Order(2)
public class MyDemoLoggingAspect {


    @Around("execution (* com.luv2code.aopdemo.service.TrafficFortuneService.getFortune(..))")
    public Object aroundGetFortune(ProceedingJoinPoint theProceedingJoinPoint) throws Throwable {

        String method = theProceedingJoinPoint.getSignature().toShortString();
        System.out.println("\n=====>>> Executing @Around on method: "+ method);

        long begin = System.currentTimeMillis();

        Object result = null;

        try {
            result = theProceedingJoinPoint.proceed();
        }
        catch(Exception ex){
            System.out.println(ex.getMessage());

            // result = "Major accident! But no worries, your private AOP helicopter is on the way";

            // rethrow exception
            throw ex;
        }

        long end = System.currentTimeMillis();

        long duration = end - begin;

        System.out.println("\n====>>> DurationL: " + duration/1000.0 + " seconds");


        return result;
    }

    @After("execution (* com.luv2code.aopdemo.dao.AccountDAO.findAccounts(..))")
    public void afterFinallyFindAccountsAdvice(JoinPoint theJoinPoint){
        String method = theJoinPoint.getSignature().toShortString();
        System.out.println("\n=====>>> Executing @After (finally) on method: "+ method);

    }

    @AfterThrowing(
            pointcut="execution (* com.luv2code.aopdemo.dao.AccountDAO.findAccounts(..))",
            throwing="theExc"
    )
    public void afterThrowingFindAccountsAdvice(JoinPoint theJoinPoint, Throwable theExc){
        String method = theJoinPoint.getSignature().toShortString();
        System.out.println("\n=====>>> Executing @AfterThrowing on method: "+ method);

        System.out.println("\n=====>>> The exception is: "+ theExc);
    }


    // add a new advice for@AfterReturning on the findAccounts method
    @AfterReturning(
            pointcut="execution (* com.luv2code.aopdemo.dao.AccountDAO.findAccounts(..))",
            returning="result"
    )
    public void afterReturningFindAccountsAdvice(JoinPoint theJoinPoint, List<Account> result){
        String method = theJoinPoint.getSignature().toShortString();
        System.out.println("\n=====>>> Executing @AfterReturning on method: "+ method);

        System.out.println("\n=====>>> result is: " + result);

        // let's post processa the data... lets modify it

        // convert the account names to uppercase

        convertAccountNamesToUpperCase(result);
        System.out.println("\n=====>>> result is: " + result);


    }

    private void convertAccountNamesToUpperCase(List<Account> result) {
        for(Account acc: result) {
            acc.setName(acc.getName().toUpperCase());
        }
    }


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
