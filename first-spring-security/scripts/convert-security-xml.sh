#!/bin/bash

target[0]=./target-project/first-spring-security-mybatis3
target[1]=./target-project/first-spring-security-mybatis3-multi

# first-spring-security-mybatis3-env.xml
case "${ARCHETYPE_VERSION:0:5}" in
 "5.1.0" | "5.1.1" | "5.2.0" )
for i in "${target[*]}";do
find $i -type f -name 'first-spring-security-mybatis3-env.xml' | xargs sed -i -e 's|<beans xmlns="http://www.springframework.org/schema/beans"|\
<beans xmlns="http://www.springframework.org/schema/beans"\
    xmlns:jdbc="http://www.springframework.org/schema/jdbc" |'
done

for i in "${target[*]}";do
find $i -type f -name 'first-spring-security-mybatis3-env.xml' | xargs sed -i -e 's|/spring-beans.xsd|/spring-beans.xsd\
        http://www.springframework.org/schema/jdbc http://www.springframework.org/schema/jdbc/spring-jdbc.xsd|'
done

for i in "${target[*]}";do
find $i -type f -name 'first-spring-security-mybatis3-env.xml' | xargs sed -i -e 's|</beans>|\
    <!-- (1) -->\
    <jdbc:initialize-database data-source="dataSource"\
        ignore-failures="ALL">\
        <!-- (2) -->\
        <jdbc:script location="classpath:/database/${database}-schema.sql" />\
        <!-- (3) -->\
        <jdbc:script location="classpath:/database/${database}-dataload.sql" />\
    </jdbc:initialize-database>\
</beans>|'
done
esac

# spring-security.xml
for i in "${target[*]}";do
find $i -type f -name 'spring-security.xml' | xargs sed -i -e 's|<sec:form-login/>|\
        <!-- (1) -->\
        <sec:form-login login-page="/login.jsp"\
            authentication-failure-url="/login.jsp?error=true" />\
        <!-- (2) -->\
        <sec:logout logout-success-url="/" delete-cookies="JSESSIONID" />\
        <!-- (3) -->\
        <sec:intercept-url pattern="/login.jsp"\
            access="permitAll" />\
        <sec:intercept-url pattern="/**" access="isAuthenticated()" />|'
done

# spring-security.xml
for i in "${target[*]}";do
find $i -type f -name 'spring-security.xml' | xargs sed -i -e 's|<sec:authentication-manager />|\
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
done
