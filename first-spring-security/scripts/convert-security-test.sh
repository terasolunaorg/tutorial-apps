#!/bin/bash

#selenium.properties
find ./target-project/first-spring-security-mybatis3/src/test/resources/META-INF/spring/selenium.properties | xargs sed -i -e 's|selenium.contextName=|selenium.contextName=first-spring-security-mybatis3|'
find ./target-project/first-spring-security-mybatis3-multi/first-spring-security-mybatis3-multi-selenium/src/test/resources/META-INF/spring/selenium.properties | xargs sed -i -e 's|selenium.contextName=|selenium.contextName=first-spring-security-mybatis3-multi-web|'

#test source
find ./target-project/first-spring-security-mybatis3-multi/first-spring-security-mybatis3-multi-selenium/src/test/java/com/example/security/selenium/security/FirstSpringSecurityTest.java | xargs sed -i -e 's|assertThat(webDriverOperations.getText(By.tagName("h1"))|assertThat(webDriverOperations.getText(By.id("title"))|'

find ./target-project/first-spring-security-mybatis3/src/test/java/com/example/security/selenium/FunctionTestSupport.java | xargs sed -i -e 's|return applicationContextUrl + "/" + simplePackageName + "/";|return applicationContextUrl + "/login.jsp";|'
find ./target-project/first-spring-security-mybatis3-multi/first-spring-security-mybatis3-multi-selenium/src/test/java/com/example/security/selenium/FunctionTestSupport.java | xargs sed -i -e 's|return applicationContextUrl + "/" + simplePackageName + "/";|return applicationContextUrl + "/login.jsp";|'
find ./target-project/first-spring-security-mybatis3 -name "*.java" -o -name "*.xml"| xargs sed -i -e 's|todo.selenium|com.example.security.selenium|'
find ./target-project/first-spring-security-mybatis3-multi -name "*.java" -o -name "*.xml"| xargs sed -i -e 's|todo.selenium|com.example.security.selenium|'

#pom.xml
if test ${ARCHETYPE_VERSION:0:3} = 5.1 ; then
    find ./target-project/first-spring-security-mybatis3/pom.xml | xargs sed -i -e 's|</dependencies>|    <dependency>\n            <groupId>org.seleniumhq.selenium</groupId>\n            <artifactId>selenium-java</artifactId>\n            <version>${selenium.version}</version>\n            <scope>test</scope>\n        </dependency>\n    </dependencies>|'
    find ./target-project/first-spring-security-mybatis3/pom.xml | xargs sed -i -e 's|</properties>|    <selenium.version>2.46.0</selenium.version>\n    </properties>|'
fi
