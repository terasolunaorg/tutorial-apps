/*
 * Copyright (C) 2013-2018 NTT DATA Corporation
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND,
 * either express or implied. See the License for the specific language
 * governing permissions and limitations under the License.
 */
package com.example.securelogin.domain.common.interceptor;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ServiceCallLoggingInterceptor implements MethodInterceptor {

    private static final Logger logger = LoggerFactory
            .getLogger(ServiceCallLoggingInterceptor.class);

    @Override
    public Object invoke(MethodInvocation invocation) throws Throwable {
        String methodName = invocation.getMethod().getName();
        String className = invocation.getMethod().getDeclaringClass()
                .getSimpleName();
        logger.info("[START SERVICE]{}.{}", className, methodName);
        try {
            Object result = invocation.proceed();
            logger.info("[COMPLETE SERVICE]{}.{}", className, methodName);
            return result;
        } catch (Throwable e) {
            logger.info("[SERVICE THROWS EXCEPTION]{}.{}", className,
                    methodName);
            logger.info("Exception : {}, Message : {}", e.getClass().getName(),
                    e.getMessage());
            throw e;
        }
    }
}
