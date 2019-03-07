#!/bin/bash
# Convert selenium test resource(s).
# Required variables:
#   ${ARTIFACT_ID}=Artifact ID of tutorial project.
#   ${HOST_IP}=IP address for access to this web application.
#   ${APSRV_H2DB_PORT}=Port number for access to H2DB.
# Parameters:
#   $1 : (Optional) Target project path to convert.

TARGET_DIR=$1
if test -n "${TARGET_DIR}/${ARTIFACT_ID}"; then
  pushd "${TARGET_DIR}/${ARTIFACT_ID}"
fi

# selenium/pom.xml
SELENIUM_POM=`find ./${ARTIFACT_ID}-selenium -type f -name 'pom.xml'`
sed -i -e 's|</configuration>|\
                    <argLine>-Dhttps.proxyHost=${http.proxyHost} -Dhttps.proxyPort=${http.proxyPort}</argLine>\
                </configuration>|' "$SELENIUM_POM"

sed -i -e 's|</dependencies>|\
        <dependency>\
            <groupId>org.hamcrest</groupId>\
            <artifactId>hamcrest-library</artifactId>\
            <scope>test</scope>\
        </dependency>\
\
        <dependency>\
            <groupId>com.h2database</groupId>\
            <artifactId>h2</artifactId>\
            <scope>test</scope>\
        </dependency>\
    </dependencies>|' "$SELENIUM_POM"

sed -i -e 's|</project>|\
    <profiles>\
        <profile>\
            <id>use_jvm_param</id>\
            <activation>\
                <property>\
                    <name>http.proxyHost</name>\
                </property>\
            </activation>\
        </profile>\
        <profile>\
        <id>use_mavenplugin_param</id>\
        <activation>\
            <activeByDefault>true</activeByDefault>\
        </activation>\
        <properties>\
            <http.proxyHost>localhost</http.proxyHost>\
            <http.proxyPort>8080</http.proxyPort>\
        </properties>\
        </profile>\
    </profiles>\
    <properties>\
        <project.root.basedir>${project.parent.basedir}</project.root.basedir>\
    </properties>\
</project>|' "$SELENIUM_POM"


# selenium.properties
SELENIUM_PROPERTIES="./${ARTIFACT_ID}-selenium/src/test/resources/META-INF/spring/selenium.properties"
for i in ${SELENIUM_PROPERTIES}; do echo -e 'selenium.enableCapture=false
selenium.enablePageSource=false
selenium.enableDbLog=false
selenium.evidenceBaseDirectory=./evidence
selenium.dbHost='"${HOST_IP}"'
selenium.dbPort='"${APSRV_H2DB_PORT}"'

# Allowable value is STANDARD or JAVASCRIPT(Default). See the JavaDoc of org.terasoluna.gfw.tutorial.selenium.WebDriverInputFieldAccessor.
# STANDARD   : for release.
# JAVASCRIPT : for development and iteration testing.
selenium.webDriverInputFieldAccessor=JAVASCRIPT

# ----------------
# Timeout & wait time settings
# ----------------
selenium.waitForLogAssertion.offsetSeconds=0
selenium.timeoutForImplicitlyWait.offsetSeconds=0

# Time unit is milliseconds.
# If specified value is -1, use default value of URLConnection(system'"'"'s default timeout).
selenium.restOperations.connectTimeout=-1

# Time unit is milliseconds.
# If specified value is -1, use default value of URLConnection(system'"'"'s default timeout).
selenium.restOperations.readTimeout=-1

# reset application database
selenium.dbResetScript=database/H2-datareload.sql' >> $i ;done

# seleniumContext.xml
SELENIUM_CONTEXT=`find ./ -type f -name 'seleniumContext.xml'`
sed -i -e 's|</beans>|\
    <bean id="realDataSource" class="org.apache.commons.dbcp2.BasicDataSource"\
        destroy-method="close">\
        <property name="driverClassName" value="org.h2.Driver" />\
        <property name="url" value="jdbc:h2:tcp://${selenium.dbHost}:${selenium.dbPort}/mem:'"${ARTIFACT_ID}"'" />\
        <property name="username" value="sa" />\
        <property name="password" value="" />\
        <property name="defaultAutoCommit" value="false" />\
    </bean>\
\
    <bean id="dataSource" class="net.sf.log4jdbc.Log4jdbcProxyDataSource">\
        <constructor-arg index="0" ref="realDataSource" />\
    </bean>\
\
    <bean id="transactionManager" class="org.springframework.jdbc.datasource.DataSourceTransactionManager">\
        <property name="dataSource" ref="dataSource" />\
    </bean>\
\
    <tx:annotation-driven/>\
\
    <bean id="dbResetScript" class="org.springframework.core.io.ClassPathResource">\
        <constructor-arg index="0" value="${selenium.dbResetScript}" />\
    </bean>\
\
    <bean id="resourceDatabasePopulator" class="org.springframework.jdbc.datasource.init.ResourceDatabasePopulator">\
        <constructor-arg index="0" ref="dbResetScript" />\
    </bean>\
\
    <bean id="restOperations" class="org.springframework.web.client.RestTemplate">\
        <property name="requestFactory">\
            <bean class="org.springframework.http.client.SimpleClientHttpRequestFactory">\
                <property name="connectTimeout" value="${selenium.restOperations.connectTimeout:-1}" />\
                <property name="readTimeout" value="${selenium.restOperations.readTimeout:-1}" />\
            </bean>\
        </property>\
    </bean>\
\
    <bean id="dbLogAssertOperations" class="org.terasoluna.gfw.tutorial.selenium.DBLogAssertOperations">\
        <constructor-arg index="0">\
            <bean id="jdbcTemplate" class="org.springframework.jdbc.core.JdbcTemplate">\
                <property name="dataSource" ref="dataSource" />\
                <property name="fetchSize" value="100" />\
            </bean>\
        </constructor-arg>\
    </bean>\
\
    <bean id="screenCapture"\
        class="org.terasoluna.gfw.tutorial.selenium.ScreenCapture" />\
\
    <bean id="dbLog" class="org.terasoluna.gfw.tutorial.selenium.DBLog">\
        <property name="dataSource" ref="dataSource" />\
    </bean>\
\
    <bean id="pageSource"\
        class="org.terasoluna.gfw.tutorial.selenium.PageSource" />\
\
    <bean id="dbLogCleaner" class="org.terasoluna.gfw.tutorial.selenium.DBLogCleaner">\
        <property name="dataSource" ref="dataSource" />\
    </bean>\
\
    <bean class="org.terasoluna.gfw.tutorial.selenium.WebDriverCreator" />\
\
  <bean class="org.terasoluna.gfw.tutorial.selenium.WebDriverManagerConfigurer">\
    <property name="propertyFileLocation" value="wdm.properties" />\
  </bean>\
\
  <beans profile="firefox default">\
    <bean id="webDriver" class="org.terasoluna.gfw.tutorial.selenium.FirefoxDriverFactoryBean"\
      scope="prototype">\
      <property name="propertyFileLocation" value="wdm.properties" />\
    </bean>\
  </beans>\
\
</beans>|' "$SELENIUM_CONTEXT"

sed -i -e 's|xsi:schemaLocation|\
    xmlns:tx="http://www.springframework.org/schema/tx"\
    xsi:schemaLocation|' "$SELENIUM_CONTEXT"

sed -i -e 's|spring-beans\.xsd|spring-beans\.xsd\
                        http://www.springframework.org/schema/tx http://www.springframework.org/schema/tx/spring-tx.xsd|' "$SELENIUM_CONTEXT"

if test -n "${TARGET_DIR}/${ARTIFACT_ID}"; then
  popd
fi
