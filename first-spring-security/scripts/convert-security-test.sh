#!/bin/bash

#selenium.properties
find ./target-project/first-spring-security-mybatis3/src/test/resources/META-INF/spring/selenium.properties | xargs sed -i -e 's|selenium.contextName=|selenium.contextName=first-spring-security-mybatis3|'
find ./target-project/first-spring-security-mybatis3-multi/first-spring-security-mybatis3-multi-selenium/src/test/resources/META-INF/spring/selenium.properties | xargs sed -i -e 's|selenium.contextName=|selenium.contextName=first-spring-security-mybatis3-multi-web|'

#test source
find ./target-project/first-spring-security-mybatis3-multi/first-spring-security-mybatis3-multi-selenium/src/test/java/com/example/security/selenium/security/FirstSpringSecurityTest.java | xargs sed -i -e 's|assertThat(driver.findElement(By.tagName("h1")).getText()|assertThat(driver.findElement(By.id("title")).getText()|'

find ./target-project/first-spring-security-mybatis3/src/test/java/com/example/security/selenium/FunctionTestSupport.java | xargs sed -i -e 's|return applicationContextUrl + "/" + simplePackageName + "/";|return applicationContextUrl + "/login.jsp";|'
find ./target-project/first-spring-security-mybatis3-multi/first-spring-security-mybatis3-multi-selenium/src/test/java/com/example/security/selenium/FunctionTestSupport.java | xargs sed -i -e 's|return applicationContextUrl + "/" + simplePackageName + "/";|return applicationContextUrl + "/login.jsp";|'
find ./target-project/first-spring-security-mybatis3 -name "*.java" -o -name "*.xml"| xargs sed -i -e 's|todo.selenium|com.example.security.selenium|'
find ./target-project/first-spring-security-mybatis3-multi -name "*.java" -o -name "*.xml"| xargs sed -i -e 's|todo.selenium|com.example.security.selenium|'

VER="${ARCHETYPE_VERSION:0:5}"

#pom.xml
case "${VER}" in
"1.0.0" | "1.0.1" | "1.0.2" | "1.0.3" | "1.0.4" | "1.0.5" | "5.0.0" | "5.0.1" | "5.0.2" | "5.1.0" | "5.1.1" ) 
    find ./target-project/first-spring-security-mybatis3/pom.xml | xargs sed -i -e 's|</dependencies>|    <dependency>\n            <groupId>org.seleniumhq.selenium</groupId>\n            <artifactId>selenium-java</artifactId>\n            <scope>test</scope>\n        </dependency>\n    </dependencies>|'
esac
