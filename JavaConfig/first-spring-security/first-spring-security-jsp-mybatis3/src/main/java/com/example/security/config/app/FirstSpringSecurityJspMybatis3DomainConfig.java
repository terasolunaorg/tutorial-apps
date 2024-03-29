package com.example.security.config.app;

import org.springframework.aop.Advisor;
import org.springframework.aop.aspectj.AspectJExpressionPointcut;
import org.springframework.aop.support.DefaultPointcutAdvisor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.terasoluna.gfw.common.exception.ExceptionLogger;
import org.terasoluna.gfw.common.exception.ResultMessagesLoggingInterceptor;

/**
 * Bean definitions for domain layer.
 */
@Configuration
@EnableTransactionManagement
@ComponentScan(basePackages = { "com.example.security.domain" })
@Import({ FirstSpringSecurityJspMybatis3InfraConfig.class,
    FirstSpringSecurityJspMybatis3CodeListConfig.class })
public class FirstSpringSecurityJspMybatis3DomainConfig {

    /**
     * Configure {@link ResultMessagesLoggingInterceptor} bean.
     * @param exceptionLogger Bean defined by ApplicationContextConfig#exceptionLogger
     * @see com.example.security.config.app.ApplicationContextConfig#exceptionLogger()
     * @return Bean of configured {@link ResultMessagesLoggingInterceptor}
     */
    @Bean("resultMessagesLoggingInterceptor")
    public ResultMessagesLoggingInterceptor resultMessagesLoggingInterceptor(
            ExceptionLogger exceptionLogger) {
        ResultMessagesLoggingInterceptor bean = new ResultMessagesLoggingInterceptor();
        bean.setExceptionLogger(exceptionLogger);
        return bean;
    }

    /**
     * Configure messages logging AOP advisor.
     * @param resultMessagesLoggingInterceptor Bean defined by #resultMessagesLoggingInterceptor
     * @see #resultMessagesLoggingInterceptor(ExceptionLogger)
     * @return Advisor configured for PointCut
     */
    @Bean
    public Advisor resultMessagesLoggingInterceptorAdvisor(
            ResultMessagesLoggingInterceptor resultMessagesLoggingInterceptor) {
        AspectJExpressionPointcut pointcut = new AspectJExpressionPointcut();
        pointcut.setExpression(
                "@within(org.springframework.stereotype.Service)");
        return new DefaultPointcutAdvisor(pointcut, resultMessagesLoggingInterceptor);
    }
}