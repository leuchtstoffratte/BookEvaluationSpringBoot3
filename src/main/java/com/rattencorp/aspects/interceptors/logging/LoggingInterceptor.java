/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.rattencorp.aspects.interceptors.logging;

/**
 *
 * Markerinterface for all logging interceptors
 * @author <intentionally left blank>
 */
public sealed interface LoggingInterceptor permits  NoopLoggingInterceptor, 
                                                    ErrorTracingInterceptor, 
                                                    AspectJDebugLoggingInterceptor,
                                                    AopDebugLoggingInterceptor{

}
