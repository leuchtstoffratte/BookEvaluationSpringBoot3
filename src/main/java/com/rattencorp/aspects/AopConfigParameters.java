/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Record.java to edit this template
 */
package com.rattencorp.aspects;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.bind.DefaultValue;

/**
 *
 * @author <intentionally left blank>
 */
@ConfigurationProperties("com.rattencorp.aspects")
public record AopConfigParameters( @DefaultValue("NONE")
                                   LoggingInterceptorLevel interceptorLevel) {

    
        
    public enum LoggingInterceptorLevel{
        NONE,
        EXCEPTIONS,
        DEBUG_AOP,
        DEBUG_ASPECTJ;
        
    }
    
    
}
