#selenium.properties
find . -type f -name 'selenium.properties' | xargs sed -i -e 's|selenium.applicationContextUrl=${selenium.serverUrl}/${selenium.contextName}|selenium.applicationContextUrl=${selenium.serverUrl}/${selenium.contextName}\nselenium.evidenceBaseDirectory=./evidence-${selenium.contextName}|'

#test source
find ./target-project/first-spring-security-mybatis3-multi/first-spring-security-mybatis3-multi-selenium/src/test/java/com/example/security/selenium/FirstSpringSecurityTest.java | xargs sed -i -e 's|assertThat(driver.findElement(By.tagName("h1"))|assertThat(driver.findElement(By.id("title"))|'