#!/bin/bash

# first-spring-security-mybatis3-env.xml
case "${ARCHETYPE_VERSION:0:5}" in
 "5.1.0" | "5.1.1" | "5.2.0" )
find . -type f -name 'first-spring-security-mybatis3-env.xml' | xargs sed -i -e 's|<beans xmlns="http://www.springframework.org/schema/beans"|\
<beans xmlns="http://www.springframework.org/schema/beans"\
    xmlns:jdbc="http://www.springframework.org/schema/jdbc" |'

find . -type f -name 'first-spring-security-mybatis3-env.xml' | xargs sed -i -e 's|/spring-beans.xsd|/spring-beans.xsd\
        http://www.springframework.org/schema/jdbc http://www.springframework.org/schema/jdbc/spring-jdbc.xsd|'
esac

# first-spring-security-mybatis3-env.xml
find . -type f -name 'first-spring-security-mybatis3-env.xml' | xargs sed -i -e 's|</beans>|\
    <!-- (1) -->\
    <jdbc:initialize-database data-source="dataSource"\
        ignore-failures="ALL">\
        <!-- (2) -->\
        <jdbc:script location="classpath:/database/${database}-schema.sql" />\
        <!-- (3) -->\
        <jdbc:script location="classpath:/database/${database}-dataload.sql" />\
    </jdbc:initialize-database>\
</beans>|'


# spring-security.xml
find . -type f -name 'spring-security.xml' | xargs sed -i -e 's|<sec:form-login/>|\
        <!-- (1) -->\
        <sec:form-login login-page="/login.jsp"\
            authentication-failure-url="/login.jsp?error=true" />\
        <!-- (2) -->\
        <sec:logout logout-success-url="/" delete-cookies="JSESSIONID" />\
        <!-- (3) -->\
        <sec:intercept-url pattern="/login.jsp"\
            access="permitAll" />\
        <sec:intercept-url pattern="/**" access="isAuthenticated()" />|'

# spring-security.xml
find . -type f -name 'spring-security.xml' | xargs sed -i -e 's|<sec:authentication-manager />|\
    <sec:authentication-manager>\
        <!-- com.example.security.domain.service.userdetails.SampleUserDetailsService \
            is scanned by component scan with @Service -->\
        <!-- (4) -->\
        <sec:authentication-provider\
            user-service-ref="sampleUserDetailsService">\
            <!-- (5) -->\
            <sec:password-encoder ref="passwordEncoder" />\
        </sec:authentication-provider>\
    </sec:authentication-manager>|'
