# add bean definitions
__END_BEAN_DEFINITION_LINE__i\
\
	<bean id="logoutSuccessHandler"\
		class="org.terasoluna.securelogin.app.common.security.CacheClearLogoutSuccessHandler">\
		<constructor-arg value="/" />\
	</bean>

# add intercept url
__END_SEC_HTTP_DEFINITION_LINE__i\
\
		<sec:intercept-url pattern="/login" access="permitAll" />\
		<sec:intercept-url pattern="/reissue/**" access="permitAll" />\
		<sec:intercept-url pattern="/api/receivedmail" access="permitAll" />\
		<sec:intercept-url pattern="/unlock/**" access="hasRole('ADMIN')" />\
		<sec:intercept-url pattern="/**" access="isAuthenticated()" />

# replace form-login
__FORM_LIGIN_LINE__i\
		<sec:form-login login-page="/login"\
			login-processing-url="/login" username-parameter="username"\
			password-parameter="password" />
__FORM_LIGIN_LINE__d

# replace logout
__LOGOUT_LINE__i\
		<sec:logout logout-url="/logout" success-handler-ref="logoutSuccessHandler"\
			delete-cookies="JSESSIONID" />
__LOGOUT_LINE__d

# replace authentication-manager
__AUTHENTICATION_MANAGER_LINE__i\
	<sec:authentication-manager>\
		<sec:authentication-provider\
			user-service-ref="loggedInUserDetailsService">\
			<sec:password-encoder ref="passwordEncoder" />\
		</sec:authentication-provider>\
	</sec:authentication-manager>
__AUTHENTICATION_MANAGER_LINE__d
