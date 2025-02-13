/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.rattencorp.aspects.interceptors.logging;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author <intentionally left blank>
 */
@Aspect
public final class AspectJDebugLoggingInterceptor implements LoggingInterceptor{

    private static final Logger LOG = LoggerFactory.getLogger(AspectJDebugLoggingInterceptor.class);
    
    
    @Before("execution(* com.rattencorp.bookevaluationspringboot3.* (..))")
    public Object logMethodCall(ProceedingJoinPoint joinPoint) throws Throwable{
        
        LOG.atDebug()
                .setMessage("DEBUG-LOGGING-WITH-ASPECTJ: ")
                .addKeyValue("target:", joinPoint::getTarget)
                .addKeyValue("args:", joinPoint::getArgs)
                .log();
                
        return joinPoint.proceed();
    }
    
}
