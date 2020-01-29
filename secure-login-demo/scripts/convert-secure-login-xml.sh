#!/bin/bash
# Convert xml resource(s) on blank project.
#   ${ARTIFACT_ID}=Artifact ID of tutorial project.
# Parameters:
#   $1 : (Optional) Target project path to convert.

TARGET_DIR=$1
if test -n "${TARGET_DIR}/${ARTIFACT_ID}"; then
  pushd "${TARGET_DIR}/${ARTIFACT_ID}"
fi

# domain/pom.xml
DOMAIN_POM=`find ./${ARTIFACT_ID}-domain -type f -name 'pom.xml'`
sed -i -e 's|</dependencies>|\
        <dependency>\
            <groupId>org.springframework</groupId>\
            <artifactId>spring-web</artifactId>\
        </dependency>\
\
        <dependency>\
            <groupId>com.h2database</groupId>\
            <artifactId>h2</artifactId>\
            <scope>runtime</scope>\
        </dependency>\
\
        <dependency>\
            <groupId>org.projectlombok</groupId>\
            <artifactId>lombok</artifactId>\
            <scope>provided</scope>\
        </dependency>\
\
        <dependency>\
            <groupId>org.passay</groupId>\
            <artifactId>passay</artifactId>\
            <version>1.1.0</version>\
        </dependency>\
\
        <dependency>\
            <groupId>com.sun.mail</groupId>\
            <artifactId>javax.mail</artifactId>\
        </dependency>\
\
        <dependency>\
            <groupId>com.sun.activation</groupId>\
            <artifactId>javax.activation</artifactId>\
            <scope>runtime</scope>\
        </dependency>\
\
        <dependency>\
            <groupId>com.icegreen</groupId>\
            <artifactId>greenmail-spring</artifactId>\
            <version>1.4.1</version>\
        </dependency>\
    </dependencies>|' "$DOMAIN_POM"

sed -i -e 's|</project>|\
  <profiles>\
    <profile>\
      <id>jdk11</id>\
      <activation>\
        <jdk>11</jdk>\
      </activation>\
      <dependencies>\
        <dependency>\
          <groupId>javax.annotation</groupId>\
          <artifactId>javax.annotation-api</artifactId>\
        </dependency>\
      </dependencies>\
    </profile>\
  </profiles>\
\
</project>|' "$DOMAIN_POM"

# mybatis-config.xml
MYBATIS_CONFIG=`find ./ -type f -name 'mybatis-config.xml'`
sed -i -e 's|</typeAliases>|\
        <package name="com.example.securelogin.infra.mybatis.typehandler" />\
    </typeAliases>|' "$MYBATIS_CONFIG"

sed -i -e 's|</typeHandlers>|\
        <package name="com.example.securelogin.infra.mybatis.typehandler" />\
    </typeHandlers>|' "$MYBATIS_CONFIG"

# -domain.xml
SECURE_LOGIN_DOMAIN=`find ./ -type f -name "${ARTIFACT_ID}-domain.xml"`
sed -i -e 's|</beans>|\
    <bean id="serviceCallLoggingInterceptor"\
        class="com.example.securelogin.domain.common.interceptor.ServiceCallLoggingInterceptor" />\
    <aop:config>\
        <aop:advisor advice-ref="serviceCallLoggingInterceptor"\
            pointcut="@within(org.springframework.stereotype.Service)" />\
    </aop:config>\
</beans>|' "$SECURE_LOGIN_DOMAIN"

# -env.xml
SECURE_LOGIN_ENV=`find ./ -type f -name "${ARTIFACT_ID}-env.xml"`
sed -i -e 's|</beans>|\
    <bean id="mailSender" class="org.springframework.mail.javamail.JavaMailSenderImpl">\
        <property name="host" value="${mail.host}" />\
        <property name="port" value="${mail.port}" />\
    </bean>\
\
    <bean id="passwordReissueMessage" class="org.springframework.mail.SimpleMailMessage">\
        <property name="from" value="${mail.from}" />\
        <property name="subject" value="${mail.subject}" />\
    </bean>\
\
    <cache:annotation-driven />\
\
    <bean id="cacheManager" class="org.springframework.cache.support.SimpleCacheManager">\
        <property name="caches">\
            <set>\
                <bean\
                    class="org.springframework.cache.concurrent.ConcurrentMapCacheFactoryBean"\
                    name="isInitialPassword" />\
                <bean\
                    class="org.springframework.cache.concurrent.ConcurrentMapCacheFactoryBean"\
                    name="isCurrentPasswordExpired" />\
            </set>\
        </property>\
    </bean>\
\
    <bean id="greenMailBean" class="com.icegreen.greenmail.spring.GreenMailBean" />\
</beans>|' "$SECURE_LOGIN_ENV"

sed -i -e 's|xsi:schemaLocation|\
    xmlns:cache="http://www.springframework.org/schema/cache"\
    xsi:schemaLocation|' "$SECURE_LOGIN_ENV"

sed -i -e 's|spring-beans\.xsd|spring-beans\.xsd\
        http://www.springframework.org/schema/cache https://www.springframework.org/schema/cache/spring-cache.xsd|' "$SECURE_LOGIN_ENV"

# context.xml
CONTEXT_XML=`find ./${ARTIFACT_ID}-env/configs -type f -name 'context.xml'`
sed -i -e "s|projectName|${ARTIFACT_ID}|g" "$CONTEXT_XML"

# logback.xml
LOGBACK_XML=`find ./${ARTIFACT_ID}-env/src/main/resources -type f -name 'logback.xml'`
sed -i -e 's|<!-- Application Loggers -->|<appender name="AUDIT_LOG_FILE"\
        class="ch.qos.logback.core.rolling.RollingFileAppender">\
        <file>${app.log.dir:-log}/security-audit.log</file>\
        <rollingPolicy class="ch.qos.logback.core.rolling.TimeBasedRollingPolicy">\
            <fileNamePattern>${app.log.dir:-log}/security-audit-%d{yyyyMMdd}.log</fileNamePattern>\
            <maxHistory>7</maxHistory>\
        </rollingPolicy>\
        <encoder>\
            <charset>UTF-8</charset>\
            <pattern><![CDATA[date:%d{yyyy-MM-dd HH:mm:ss}\\tthread:%thread\\tUSER:%X{USER}\\tX-Track:%X{X-Track}\\tlevel:%-5level\\tlogger:%-48logger{48}\\tmessage:%msg%n]]></pattern>\
        </encoder>\
    </appender>\
\
    <appender name="DB" class="ch.qos.logback.classic.db.DBAppender">\
        <connectionSource class="ch.qos.logback.core.db.DataSourceConnectionSource">\
            <dataSource class="org.apache.commons.dbcp2.BasicDataSource">\
                <driverClassName>org.h2.Driver</driverClassName>\
                <url>jdbc:h2:mem:'"${ARTIFACT_ID}"';DB_CLOSE_DELAY=-1;</url>\
                <username>sa</username>\
                <password></password>\
            </dataSource>\
        </connectionSource>\
    </appender>\
\
    <!-- Application Loggers -->|' "$LOGBACK_XML"

sed -i -e '/<logger name="com.example.securelogin">/,/<\/logger>/s|<level value="debug" />|<level value="info" />\
    </logger>\
    <logger\
        name="com.example.securelogin.domain.common.interceptor.ServiceCallLoggingInterceptor"\
        additivity="false">\
        <level value="info" />\
        <appender-ref ref="AUDIT_LOG_FILE" />\
        <appender-ref ref="DB" />|' "$LOGBACK_XML"

sed -i -e '/<logger name="org.terasoluna.gfw.web.logging.TraceLoggingInterceptor">/,/<\/logger>/s/<level value="trace" \/>/<level value="info" \/>/' "$LOGBACK_XML"

sed -i -e 's|<appender-ref ref="MONITORING_LOG_FILE" />|<appender-ref ref="MONITORING_LOG_FILE" />\
        <appender-ref ref="DB" />|' "$LOGBACK_XML"

sed -i -e 's|<appender-ref ref="APPLICATION_LOG_FILE" />|<appender-ref ref="APPLICATION_LOG_FILE" />\
        <appender-ref ref="DB" />|' "$LOGBACK_XML"

sed -i -e '/<!-- only for development -->/,/<\/logger>/d' "$LOGBACK_XML"

# web/pom.xml
WEB_POM=`find ./${ARTIFACT_ID}-web -type f -name 'pom.xml'`
sed -i -e 's|</dependencies>|\
        <dependency>\
            <groupId>org.terasoluna.gfw</groupId>\
            <artifactId>terasoluna-gfw-validator</artifactId>\
        </dependency>\
\
        <dependency>\
            <groupId>org.projectlombok</groupId>\
            <artifactId>lombok</artifactId>\
            <scope>provided</scope>\
        </dependency>\
\
        <dependency>\
            <groupId>org.passay</groupId>\
            <artifactId>passay</artifactId>\
            <version>1.1.0</version>\
        </dependency>\
    </dependencies>|' "$WEB_POM"

# applicationContext.xml
APPLICATION_CONTEXT=`find ./ -type f -name 'applicationContext.xml'`
sed -i -e 's|</beans>|\
    <bean id="inputValidationFilter" class="com.example.securelogin.app.common.filter.InputValidationFilter">\
        <constructor-arg index="0" value="${app.security.prohibitedChars}"/>\
        <constructor-arg index="1" value="${app.security.prohibitedCharsForFileName}"/>\
    </bean>\
\
    <bean id="lengthRule" class="org.passay.LengthRule">\
        <property name="minimumLength" value="${security.passwordMinimumLength}" />\
    </bean>\
\
    <bean id="upperCaseRule" class="org.passay.CharacterRule">\
        <constructor-arg name="data">\
            <util:constant static-field="org.passay.EnglishCharacterData.UpperCase" />\
        </constructor-arg>\
        <constructor-arg name="num" value="1" />\
    </bean>\
\
    <bean id="lowerCaseRule" class="org.passay.CharacterRule">\
        <constructor-arg name="data">\
            <util:constant static-field="org.passay.EnglishCharacterData.LowerCase" />\
        </constructor-arg>\
        <constructor-arg name="num" value="1" />\
    </bean>\
\
    <bean id="digitRule" class="org.passay.CharacterRule">\
        <constructor-arg name="data">\
            <util:constant static-field="org.passay.EnglishCharacterData.Digit" />\
        </constructor-arg>\
        <constructor-arg name="num" value="1" />\
    </bean>\
\
    <bean id="specialCharacterRule" class="org.passay.CharacterRule">\
        <constructor-arg name="data">\
            <util:constant static-field="org.passay.EnglishCharacterData.Special" />\
        </constructor-arg>\
        <constructor-arg name="num" value="1" />\
    </bean>\
\
    <bean id="characterCharacteristicsRule" class="org.passay.CharacterCharacteristicsRule">\
        <property name="rules">\
            <list>\
                <ref bean="upperCaseRule" />\
                <ref bean="lowerCaseRule" />\
                <ref bean="digitRule" />\
                <ref bean="specialCharacterRule" />\
            </list>\
        </property>\
        <property name="numberOfCharacteristics" value="3" />\
    </bean>\
\
    <bean id="usernameRule" class="org.passay.UsernameRule" />\
\
    <bean id="encodedPasswordHistoryRule"\
        class="com.example.securelogin.app.common.validation.rule.EncodedPasswordHistoryRule">\
        <constructor-arg name="passwordEncoder" ref="passwordEncoder" />\
    </bean>\
\
    <bean id="characteristicPasswordValidator" class="org.passay.PasswordValidator">\
        <constructor-arg name="rules">\
            <list>\
                <ref bean="lengthRule" />\
                <ref bean="characterCharacteristicsRule" />\
                <ref bean="usernameRule" />\
            </list>\
        </constructor-arg>\
    </bean>\
\
    <bean id="encodedPasswordHistoryValidator" class="org.passay.PasswordValidator">\
        <constructor-arg name="rules">\
            <list>\
                <ref bean="encodedPasswordHistoryRule" />\
            </list>\
        </constructor-arg>\
    </bean>\
\
    <bean id="passwordGenerator" class="org.passay.PasswordGenerator" />\
\
    <util:list id="passwordGenerationRules">\
        <ref bean="upperCaseRule" />\
        <ref bean="lowerCaseRule" />\
        <ref bean="digitRule" />\
    </util:list>\
\
    <bean id="expiredReissueInfoCleaner"\
        class="com.example.securelogin.domain.common.scheduled.UnnecessaryReissueInfoCleaner" />\
    <bean id="expiredReissueInfoCleanTrigger" class="org.springframework.scheduling.support.PeriodicTrigger">\
        <constructor-arg name="period" value="${security.reissueInfoCleanupSeconds}" />\
        <constructor-arg name="timeUnit" value="SECONDS" />\
    </bean>\
    <task:scheduler id="reissueInfoCleanupTaskScheduler" />\
\
    <task:scheduled-tasks scheduler="reissueInfoCleanupTaskScheduler">\
        <task:scheduled ref="expiredReissueInfoCleaner" method="cleanup"\
            trigger="expiredReissueInfoCleanTrigger" />\
    </task:scheduled-tasks>\
\
    <bean id="tempFileCleaner"\
        class="com.example.securelogin.domain.common.scheduled.TempFileCleaner" />\
    <bean id="tempFileCleanTrigger" class="org.springframework.scheduling.support.PeriodicTrigger">\
        <constructor-arg name="period" value="${security.tempFileCleanupSeconds}" />\
        <constructor-arg name="timeUnit" value="SECONDS" />\
    </bean>\
    <task:scheduler id="tempFileTaskScheduler" />\
    <task:scheduled-tasks scheduler="tempFileTaskScheduler">\
        <task:scheduled ref="tempFileCleaner" method="cleanup" trigger="tempFileCleanTrigger" />\
    </task:scheduled-tasks>\
</beans>|' "$APPLICATION_CONTEXT"

sed -i -e 's|xsi:schemaLocation|\
    xmlns:task="http://www.springframework.org/schema/task"\
    xmlns:util="http://www.springframework.org/schema/util"\
xsi:schemaLocation|' "$APPLICATION_CONTEXT"

sed -i -e 's|spring-beans\.xsd|spring-beans\.xsd\
        http://www.springframework.org/schema/task https://www.springframework.org/schema/task/spring-task.xsd\
        http://www.springframework.org/schema/util https://www.springframework.org/schema/util/spring-util.xsd|' "$APPLICATION_CONTEXT"

sed -i -e 's|<entry key="InvalidTransactionTokenException"|\
                <entry key="MultipartException" value="e.sl.fw.6001" />\
                <entry key="InvalidTransactionTokenException"|' "$APPLICATION_CONTEXT"

sed -i -e 's/e.xx.fw.5001/e.sl.fw.5001/g' "$APPLICATION_CONTEXT"
sed -i -e 's/e.xx.fw.7001/e.sl.fw.7001/g' "$APPLICATION_CONTEXT"
sed -i -e 's/e.xx.fw.8001/e.sl.fw.8001/g' "$APPLICATION_CONTEXT"
sed -i -e 's/e.xx.fw.9001/e.sl.fw.9001/g' "$APPLICATION_CONTEXT"
sed -i -e 's/e.xx.fw.9002/e.sl.fw.9002/g' "$APPLICATION_CONTEXT"

# spring-mvc.xml
SPRING_MVC=`find ./ -type f -name 'spring-mvc.xml'`
sed -i -e 's|</beans>|\
    <mvc:view-controller path="/login" view-name="login/login" />\
</beans>|' "$SPRING_MVC"

sed -i -e 's|</mvc:interceptors>|\
        <mvc:interceptor>\
            <mvc:mapping path="/**" />\
            <mvc:exclude-mapping path="/password/**" />\
            <mvc:exclude-mapping path="/reissue/**" />\
            <mvc:exclude-mapping path="/resources/**" />\
            <mvc:exclude-mapping path="/**/*.html" />\
            <bean\
                class="com.example.securelogin.app.common.interceptor.PasswordExpirationCheckInterceptor" />\
        </mvc:interceptor>\
    </mvc:interceptors>|' "$SPRING_MVC"

# spring-security.xml
SPRING_SECURITY=`find ./ -type f -name 'spring-security.xml'`
sed -i -e 's|</beans>|\
    <bean id="logoutSuccessHandler"\
        class="com.example.securelogin.app.common.security.CacheClearLogoutSuccessHandler">\
        <constructor-arg value="/" />\
    </bean>\
</beans>|' "$SPRING_SECURITY"

sed -i -e "s|</sec:http>|\
        <sec:intercept-url pattern=\"/login\" access=\"permitAll\" />\
        <sec:intercept-url pattern=\"/accounts/create\" access=\"permitAll\" />\
        <sec:intercept-url pattern=\"/reissue/**\" access=\"permitAll\" />\
        <sec:intercept-url pattern=\"/api/receivedmail\" access=\"permitAll\" />\
        <sec:intercept-url pattern=\"/unlock/**\" access=\"hasRole('ADMIN')\" />\
        <sec:intercept-url pattern=\"/**\" access=\"isAuthenticated()\" />\
</sec:http>|" "$SPRING_SECURITY"

sed -i -e 's|<sec:form-login.*/>|<sec:form-login login-page="/login"\
            login-processing-url="/login" username-parameter="username"\
            password-parameter="password" />|' "$SPRING_SECURITY"

sed -i -e 's|<sec:logout.*/>|<sec:logout logout-url="/logout" success-handler-ref="logoutSuccessHandler"\
            delete-cookies="JSESSIONID" />|' "$SPRING_SECURITY"

sed -i -e 's|<sec:authentication-manager.*/>|<sec:authentication-manager>\
        <sec:authentication-provider\
            user-service-ref="loggedInUserDetailsService">\
            <sec:password-encoder ref="passwordEncoder" />\
        </sec:authentication-provider>\
    </sec:authentication-manager>|' "$SPRING_SECURITY"

# web.xml
WEB_XML=`find ./ -type f -name 'web.xml'`
sed -i -e 's|</load-on-startup>|</load-on-startup>\
        <multipart-config>\
            <max-file-size>5242880</max-file-size>\
            <max-request-size>27262976</max-request-size>\
            <file-size-threshold>0</file-size-threshold>\
        </multipart-config>|' "$WEB_XML"

sed -i -e '/<?xml version="1.0" encoding="UTF-8"?>/,/<listener>/s|<listener>|<context-param>\
        <param-name>db.url</param-name>\
        <param-value>jdbc:h2:mem:'"${ARTIFACT_ID}"';DB_CLOSE_DELAY=-1;INIT=RUNSCRIPT FROM '"'"'classpath:logback-ddl.sql'"'"'</param-value>\
    </context-param>\
\
    <context-param>\
        <param-name>db.user</param-name>\
        <param-value>sa</param-value>\
    </context-param>\
\
    <context-param>\
        <param-name>db.password</param-name>\
        <param-value></param-value>\
    </context-param>\
\
    <context-param>\
        <param-name>db.tcpServer</param-name>\
        <param-value>-tcpAllowOthers -tcpPort 9212</param-value>\
    </context-param>\
\
    <listener>\
        <listener-class>org.h2.server.web.DbStarter</listener-class>\
    </listener>\
\
    <servlet>\
        <servlet-name>H2Console</servlet-name>\
        <servlet-class>org.h2.server.web.WebServlet</servlet-class>\
        <init-param>\
            <param-name>-webAllowOthers</param-name>\
            <param-value>true</param-value>\
        </init-param>\
        <load-on-startup>2</load-on-startup>\
    </servlet>\
\
    <servlet-mapping>\
        <servlet-name>H2Console</servlet-name>\
        <url-pattern>/admin/h2/*</url-pattern>\
    </servlet-mapping>\
    <listener>|' "$WEB_XML"

sed -i -e '/<filter-class>org.springframework.web.filter.CharacterEncodingFilter<\/filter-class>/,/<filter>/s|<filter>|<filter>\
        <filter-name>MultipartFilter</filter-name>\
        <filter-class>org.springframework.web.multipart.support.MultipartFilter</filter-class>\
    </filter>\
    <filter-mapping>\
            <filter-name>MultipartFilter</filter-name>\
            <url-pattern>/*</url-pattern>\
    </filter-mapping>\
\
    <filter>\
        <filter-name>inputValidationFilter</filter-name>\
        <filter-class>org.springframework.web.filter.DelegatingFilterProxy</filter-class>\
    </filter>\
    <filter-mapping>\
        <filter-name>inputValidationFilter</filter-name>\
        <url-pattern>/*</url-pattern>\
    </filter-mapping>\
\
    <filter>|' "$WEB_XML"

sed -i -e '/<location>\/WEB-INF\/views\/common\/error\/unhandledSystemError.html<\/location>/,/<\/error-page>/s|</error-page>|</error-page>\
\
    <error-page>\
        <exception-type>com.example.securelogin.app.common.filter.exception.InvalidCharacterException</exception-type>\
        <location>/WEB-INF/views/common/error/invalidCharacterError.jsp</location>\
    </error-page>|' "$WEB_XML"

if test -n "${TARGET_DIR}/${ARTIFACT_ID}"; then
  popd
fi
