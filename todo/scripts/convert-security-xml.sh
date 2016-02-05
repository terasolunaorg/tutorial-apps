#!/bin/bash

##既存ソースの修正 multiの方は既にこの設定があるのでやらない
# first-spring-security-mybatis3-env.xml
# jdbcの名前空間、スキーマロケーション
find . -type f -name 'first-spring-security-mybatis3-env.xml' | xargs sed -i -e 's|<beans xmlns="http://www.springframework.org/schema/beans"|\
<beans xmlns="http://www.springframework.org/schema/beans"\
    xmlns:jdbc="http://www.springframework.org/schema/jdbc" |'

# jdbcのスキーマロケーション
find . -type f -name 'first-spring-security-mybatis3-env.xml' | xargs sed -i -e 's|/spring-beans.xsd|/spring-beans.xsd\
        http://www.springframework.org/schema/jdbc http://www.springframework.org/schema/jdbc/spring-jdbc.xsd|'

# jdbc初期化SQL指定
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
# ログインフォームの設定
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

# 認証サービスの設定
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
