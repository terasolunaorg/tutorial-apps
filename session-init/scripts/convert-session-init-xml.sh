#!/bin/bash

# session-tutorial-init-codelist.xml
find ./session-tutorial-init -type f -name 'session-tutorial-init-codelist.xml' | xargs sed -i -e 's|-->|-->\
\
    <bean id="CL_CATEGORIES" class="org.terasoluna.gfw.common.codelist.JdbcCodeList">\
        <property name="dataSource" ref="dataSource" />\
        <property name="querySql"\
            value="SELECT category_id, category_name FROM category ORDER BY category_id" />\
        <property name="valueColumn" value="category_id" />\
        <property name="labelColumn" value="category_name" />\
    </bean>|'

# session-tutorial-init-domain.xml
find ./session-tutorial-init -type f -name 'session-tutorial-init-domain.xml' | xargs sed -i -e 's|<context:component-scan base-package="com.example.session.domain" />|<!-- (1) -->\
    <context:component-scan base-package="com.example.session.domain" />|'

# spring-mvc.xml
find ./session-tutorial-init -type f -name 'spring-mvc.xml' | xargs sed -i -e 's|class="org.springframework.data.web.PageableHandlerMethodArgumentResolver" />|class="org.springframework.data.web.PageableHandlerMethodArgumentResolver" >\
                <property name="fallbackPageable">\
                    <bean class="org.springframework.data.domain.PageRequest">\
                        <constructor-arg index="0" value="0" />\
                        <constructor-arg index="1" value="3" />\
                    </bean>\
                </property>\
            </bean>|' ./session-tutorial-init/session-tutorial-init-domain/src/main/resources/META-INF/spring/session-tutorial-init-codelist.xml

find ./session-tutorial-init -type f -name 'spring-mvc.xml' | xargs sed -i -e 's|<context:component-scan base-package="com.example.session.app" />|<!-- (1) -->\
    <context:component-scan base-package="com.example.session.app" />|'

# spring-security.xml
find ./session-tutorial-init -type f -name 'spring-security.xml' | xargs sed -i -e 's|<sec:form-login/>|<sec:form-login login-page="/loginForm"\
            username-parameter="email" password-parameter="password"\
            default-target-url="/goods" always-use-default-target="true"\
            authentication-failure-url="/loginForm?error" login-processing-url="/authenticate" />|'
            
find ./session-tutorial-init -type f -name 'spring-security.xml' | xargs sed -i -e 's|<sec:logout/>|<sec:logout logout-url="/logout" logout-success-url="/loginForm"\
            delete-cookies="JSESSIONID" />|'

find ./session-tutorial-init -type f -name 'spring-security.xml' | xargs sed -i -e 's|<sec:session-management />|<sec:session-management />\
        <sec:intercept-url pattern="/loginForm" access="permitAll" />\
        <sec:intercept-url pattern="/account/create" access="permitAll" />\
        <sec:intercept-url pattern="/" access="permitAll" />\
        <sec:intercept-url pattern="/**" access="isAuthenticated()" />|'
        
find ./session-tutorial-init -type f -name 'spring-security.xml' | xargs sed -i -e 's|<sec:authentication-manager />|<sec:authentication-manager>\
        <sec:authentication-provider\
            user-service-ref="accountDetailsService">\
            <sec:password-encoder ref="passwordEncoder" />\
        </sec:authentication-provider>\
    </sec:authentication-manager>|'