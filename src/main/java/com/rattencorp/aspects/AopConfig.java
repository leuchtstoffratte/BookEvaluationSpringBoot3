/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/springframework/Configuration.java to edit this template
 */
package com.rattencorp.aspects;

import com.rattencorp.aspects.interceptors.logging.DebugLoggingInterceptor;
import com.rattencorp.aspects.interceptors.logging.ErrorTracingInterceptor;
import com.rattencorp.aspects.interceptors.logging.LoggingInterceptor;
import com.rattencorp.aspects.interceptors.logging.NoopLoggingInterceptor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.context.annotation.Profile;

/**
 *
 * @author <intentionally left blank>
 */

@Configuration
@EnableConfigurationProperties({AopConfig.class})
public class AopConfig {


    private static final Logger LOG = LogManager.getLogger(AopConfig.class);
    
    
    
    @Bean
    @Profile("aop-profile")
    @DependsOn("com.rattencorp.aspects.AopConfigParameters")
    @Autowired
    public LoggingInterceptor createLoggingInterceptor(AopConfigParameters configParams){
        
        LOG.fatal(()-> "got configParams: '%s'".formatted(configParams));

        
        return switch (configParams.interceptorLevel()){
            case NONE -> new NoopLoggingInterceptor();
            case EXCEPTIONS -> new ErrorTracingInterceptor();
            case DEBUG_AOP -> null;
            case DEBUG_ASPECTJ -> new DebugLoggingInterceptor();
        };
        
        

    }
    

    
    
    
    

}
