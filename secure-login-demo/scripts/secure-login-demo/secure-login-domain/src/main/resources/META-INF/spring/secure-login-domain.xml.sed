# add bean definitions
__END_BEAN_DEFINITION_LINE__i\
\
	<bean id="serviceCallLoggingInterceptor"\
		class="org.terasoluna.securelogin.domain.common.interceptor.ServiceCallLoggingInterceptor" />\
	<aop:config>\
		<aop:advisor advice-ref="serviceCallLoggingInterceptor"\
			pointcut="@within(org.springframework.stereotype.Service)" />\
	</aop:config>
