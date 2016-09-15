#!/bin/bash

#selenium.properties
find . -type f -name 'selenium.properties' | xargs sed -i -e 's|selenium.applicationContextUrl=${selenium.serverUrl}/${selenium.contextName}|selenium.applicationContextUrl=${selenium.serverUrl}/${selenium.contextName}\nselenium.evidenceBaseDirectory=./evidence-${selenium.contextName}|'

#test source
find ./target-project/first-spring-security-mybatis3-multi/first-spring-security-mybatis3-multi-selenium/src/test/java/com/example/security/selenium/FirstSpringSecurityTest.java | xargs sed -i -e 's|assertThat(driver.findElement(By.tagName("h1"))|assertThat(driver.findElement(By.id("title"))|'

#pom.xml
if test ${ARCHETYPE_VERSION:0:3} -eq 5.1 ; then
    find ./target-project/first-spring-security-mybatis3/pom.xml | xargs sed -i -e 's|</dependencies>|<dependency>\n            <groupId>org.seleniumhq.selenium</groupId>\n            <artifactId>selenium-java</artifactId>\n            <version>${selenium.version}</version>\n            <scope>test</scope>\n        </dependency>\n    </dependencies>|'
fi
