# add interceptor
__END_INTERCEPTOR_DEFINITION_LINE__i\
\
		<mvc:interceptor>\
			<mvc:mapping path="/**" />\
			<mvc:exclude-mapping path="/password/**" />\
			<mvc:exclude-mapping path="/reissue/**" />\
			<mvc:exclude-mapping path="/resources/**" />\
			<mvc:exclude-mapping path="/**/*.html" />\
			<bean\
				class="org.terasoluna.securelogin.app.common.interceptor.PasswordExpirationCheckInterceptor" />\
		</mvc:interceptor>

# add controller
__END_BEAN_DEFINITION_LINE__i\
\
	<mvc:view-controller path="/login" view-name="login/login" />
