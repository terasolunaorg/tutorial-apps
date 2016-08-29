package org.terasoluna.securelogin.domain.common.interceptor;

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
