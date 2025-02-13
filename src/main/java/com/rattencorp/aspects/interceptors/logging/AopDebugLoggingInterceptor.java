/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.rattencorp.aspects.interceptors.logging;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.aop.BeforeAdvice;
import org.springframework.aop.ClassFilter;
import org.springframework.aop.MethodMatcher;
import org.springframework.aop.Pointcut;

/**
 *
 * @author <intentionally left blank>
 */
public final class AopDebugLoggingInterceptor implements LoggingInterceptor, BeforeAdvice{
    
    
    private static final Logger LOG = LoggerFactory.getLogger(AspectJDebugLoggingInterceptor.class);
    
    
    public static class AopLoggingPointcut implements Pointcut{

        @Override
        public ClassFilter getClassFilter() {
            return c -> c.getPackageName().startsWith("com.rattencorp.bookevaluationspringboot3");
        }

        @Override
        public MethodMatcher getMethodMatcher() {
            //match all methods
            return MethodMatcher.TRUE;
        }
    
    }
    
    
    
    @Before("AopLoggingPointcut.class")
    public void logCall(JoinPoint joinpoint){
        
        LOG.atDebug()
                .setMessage("DEBUG-LOGGING-WITH-ASPECTJ: ")
                .addKeyValue("this: ", joinpoint::getThis)
                .addKeyValue("target:", joinpoint::getTarget)
                .addKeyValue("args:", joinpoint::getArgs)
                .log();
                
    }
    

    
}
