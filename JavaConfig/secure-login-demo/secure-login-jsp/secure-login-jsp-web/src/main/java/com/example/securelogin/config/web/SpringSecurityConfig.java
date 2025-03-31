package com.example.securelogin.config.web;

import static org.springframework.security.web.util.matcher.AntPathRequestMatcher.antMatcher;
import java.util.LinkedHashMap;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
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
import com.example.securelogin.app.common.security.CacheClearLogoutSuccessHandler;

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
        http.formLogin(login -> login.loginPage("/login").loginProcessingUrl("/login")
                .usernameParameter("username").passwordParameter("password")
                .defaultSuccessUrl("/", true));
        http.logout(logout -> logout.logoutUrl("/logout")
                .logoutSuccessHandler(logoutSuccessHandler()).deleteCookies("JSESSIONID"));
        http.exceptionHandling(ex -> ex.accessDeniedHandler(accessDeniedHandler()));
        http.addFilterAfter(userIdMDCPutFilter(), AnonymousAuthenticationFilter.class);
        http.sessionManagement(Customizer.withDefaults());
        http.authorizeHttpRequests(authz -> authz.requestMatchers(antMatcher("/login")).permitAll()
                .requestMatchers(antMatcher("/accounts/create")).permitAll()
                .requestMatchers(antMatcher("/reissue/**")).permitAll()
                .requestMatchers(antMatcher("/api/receivedmail")).permitAll()
                .requestMatchers(antMatcher("/unlock/**")).hasRole("ADMIN")
                .requestMatchers(antMatcher("/**")).authenticated());

        return http.build();
    }

    /**
     * Configure {@link AuthenticationProvider} bean.
     * @param userDetailsService Bean defined within Application
     * @param passwordEncoder Bean defined by ApplicationContext#passwordEncoder
     * @return Bean of configured {@link AuthenticationProvider}
     */
    @Bean
    public AuthenticationProvider authProvider(UserDetailsService loggedInUserDetailsService,
            @Qualifier("passwordEncoder") PasswordEncoder passwordEncoder) {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(loggedInUserDetailsService);
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

    /**
     * Configure {@link CacheClearLogoutSuccessHandler} bean.
     * @return Bean of configured {@link CacheClearLogoutSuccessHandler}
     */
    @Bean(name = "logoutSuccessHandler")
    public CacheClearLogoutSuccessHandler logoutSuccessHandler() {
        CacheClearLogoutSuccessHandler bean = new CacheClearLogoutSuccessHandler("/");
        return bean;
    }
}
