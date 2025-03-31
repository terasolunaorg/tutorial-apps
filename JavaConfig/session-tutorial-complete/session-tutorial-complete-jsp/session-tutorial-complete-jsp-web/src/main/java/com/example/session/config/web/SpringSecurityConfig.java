package com.example.session.config.web;

import static org.springframework.security.web.util.matcher.AntPathRequestMatcher.antMatcher;
import java.util.LinkedHashMap;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.access.AccessDeniedHandlerImpl;
import org.springframework.security.web.access.DelegatingAccessDeniedHandler;
import org.springframework.security.web.access.expression.DefaultWebSecurityExpressionHandler;
import org.springframework.security.web.authentication.AnonymousAuthenticationFilter;
import org.springframework.security.web.csrf.InvalidCsrfTokenException;
import org.springframework.security.web.csrf.MissingCsrfTokenException;
import org.terasoluna.gfw.security.web.logging.UserIdMDCPutFilter;

/**
 * Bean definition to configure SpringSecurity.
 */
@Configuration
@EnableWebSecurity
public class SpringSecurityConfig {

    /**
     * Configure ignore security pattern.
     * @return Bean of configured {@link WebSecurityCustomizer}
     */
    @Bean
    public WebSecurityCustomizer webSecurityCustomizer() {
        return web -> web.ignoring().requestMatchers(antMatcher("/resources/**"));
    }

    /**
     * Configure {@link SecurityFilterChain} bean.
     * @param http Builder class for setting up authentication and authorization
     * @return Bean of configured {@link SecurityFilterChain}
     * @throws Exception Exception that occurs when setting HttpSecurity
     */
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.formLogin(login -> login.loginPage("/loginForm").usernameParameter("email")
                .passwordParameter("password").defaultSuccessUrl("/goods", true)
                .failureUrl("/loginForm?error").loginProcessingUrl("/authenticate"));
        http.logout(logout -> logout.logoutUrl("/logout").logoutSuccessUrl("/loginForm")
                .deleteCookies("JSESSIONID"));
        http.exceptionHandling(ex -> ex.accessDeniedHandler(accessDeniedHandler()));
        http.addFilterAfter(userIdMDCPutFilter(), AnonymousAuthenticationFilter.class);
        http.sessionManagement(
                sessionManagement -> sessionManagement.invalidSessionUrl("/loginForm"));
        http.authorizeHttpRequests(authz -> authz.requestMatchers(antMatcher("/loginForm"))
                .permitAll().requestMatchers(antMatcher("/account/create")).permitAll()
                .requestMatchers(antMatcher("/")).permitAll().requestMatchers(antMatcher("/**"))
                .authenticated());

        return http.build();
    }

    /**
     * Configure {@link AuthenticationProvider} bean.
     * @param accountDetailsService Bean defined within Application
     * @param passwordEncoder Bean defined by ApplicationContext#passwordEncoder
     * @return Bean of configured {@link AuthenticationProvider}
     */
    @Bean
    public AuthenticationProvider authProvider(UserDetailsService accountDetailsService,
            @Qualifier("passwordEncoder") PasswordEncoder passwordEncoder) {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(accountDetailsService);
        authProvider.setPasswordEncoder(passwordEncoder);
        return authProvider;
    }

    /**
     * Configure {@link AccessDeniedHandler} bean.
     * @return Bean of configured {@link AccessDeniedHandler}
     */
    @Bean("accessDeniedHandler")
    public AccessDeniedHandler accessDeniedHandler() {
        LinkedHashMap<Class<? extends AccessDeniedException>, AccessDeniedHandler> errorHandlers =
                new LinkedHashMap<>();

        // Invalid CSRF authenticator error handler
        AccessDeniedHandlerImpl invalidCsrfTokenErrorHandler = new AccessDeniedHandlerImpl();
        invalidCsrfTokenErrorHandler
                .setErrorPage("/WEB-INF/views/common/error/invalidCsrfTokenError.jsp");
        errorHandlers.put(InvalidCsrfTokenException.class, invalidCsrfTokenErrorHandler);

        // Missing CSRF authenticator error handler
        AccessDeniedHandlerImpl missingCsrfTokenErrorHandler = new AccessDeniedHandlerImpl();
        missingCsrfTokenErrorHandler
                .setErrorPage("/WEB-INF/views/common/error/missingCsrfTokenError.jsp");
        errorHandlers.put(MissingCsrfTokenException.class, missingCsrfTokenErrorHandler);

        // Default error handler
        AccessDeniedHandlerImpl defaultErrorHandler = new AccessDeniedHandlerImpl();
        defaultErrorHandler.setErrorPage("/WEB-INF/views/common/error/accessDeniedError.jsp");

        return new DelegatingAccessDeniedHandler(errorHandlers, defaultErrorHandler);
    }

    /**
     * Configure {@link DefaultWebSecurityExpressionHandler} bean.
     * @return Bean of configured {@link DefaultWebSecurityExpressionHandler}
     */
    @Bean("webSecurityExpressionHandler")
    public DefaultWebSecurityExpressionHandler webSecurityExpressionHandler() {
        return new DefaultWebSecurityExpressionHandler();
    }

    /**
     * Configure {@link UserIdMDCPutFilter} bean.
     * @return Bean of configured {@link UserIdMDCPutFilter}
     */
    @Bean("userIdMDCPutFilter")
    public UserIdMDCPutFilter userIdMDCPutFilter() {
        return new UserIdMDCPutFilter();
    }
}
