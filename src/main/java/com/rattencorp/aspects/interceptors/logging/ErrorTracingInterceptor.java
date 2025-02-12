/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.rattencorp.aspects.interceptors.logging;

import java.util.Arrays;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.AfterThrowing;


/**
 *
 * @author <intentionally left blank>
 */
@Aspect
public final class ErrorTracingInterceptor implements LoggingInterceptor {
 
    private static final Log LOG = LogFactory.getLog(ErrorTracingInterceptor.class);
    

    @AfterThrowing(pointcut = "execution( * com.rattencorp.bookevaluationspringboot3.*(..) throws *)", throwing = "error")
    public void logErrorMessagesAndDetails(JoinPoint joinPoint, Throwable error){
        final String arguments =
                Arrays.stream(joinPoint.getArgs())
                    .map(o -> new StringBuilder(o.toString()))
                    .reduce((a,b) -> a.append(";%n").append(b))
                    .toString();

        LOG.error(
                "An error happend for call %s with parameters '%s'".formatted(joinPoint.toShortString(), arguments),
                error
        );
    }
    
    
    
}
