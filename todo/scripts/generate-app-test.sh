#!/bin/bash

# make app test
# single without any DB configuration
mkdir -p ./target-project/todo/src/test/java/todo/selenium/todo
mkdir -p ./target-project/todo/src/test/resources/META-INF/spring
# mybatis3
mkdir -p ./target-project/todo-mybatis3/src/test/java/todo/selenium/todo
mkdir -p ./target-project/todo-mybatis3/src/test/resources/META-INF/spring
# mybatis3-multi
mkdir -p ./target-project/todo-mybatis3-multi/todo-mybatis3-multi-selenium/src/test/java/todo/selenium/todo
# jpa
mkdir -p ./target-project/todo-jpa/src/test/java/todo/selenium/todo
mkdir -p ./target-project/todo-jpa/src/test/resources/META-INF/spring
# jpa-multi
mkdir -p ./target-project/todo-jpa-multi/todo-jpa-multi-selenium/src/test/java/todo/selenium/todo

# cp test sources
# single without any DB configuration
cp -p ./todo/src/test/java/todo/selenium/todo/TodoTest.java ./target-project/todo/src/test/java/todo/selenium/todo
cp -p ./common-test/resources/META-INF/spring/selenium.properties ./target-project/todo/src/test/resources/META-INF/spring
cp -p ./common-test/resources/META-INF/spring/seleniumContext.xml ./target-project/todo/src/test/resources/META-INF/spring
cp -p ./common-test/java/selenium/FirefoxDriverFactoryBean.java ./target-project/todo/src/test/java/todo/selenium
cp -p ./common-test/java/selenium/FunctionTestSupport.java ./target-project/todo/src/test/java/todo/selenium
cp -p ./common-test/java/selenium/FunctionTestSupportForMultiBrowser.java ./target-project/todo/src/test/java/todo/selenium
cp -p ./common-test/java/selenium/Page.java ./target-project/todo/src/test/java/todo/selenium
cp -p ./common-test/java/selenium/PageOperations.java ./target-project/todo/src/test/java/todo/selenium
cp -p ./common-test/java/selenium/PageSource.java ./target-project/todo/src/test/java/todo/selenium
cp -p ./common-test/java/selenium/ScreenCapture.java ./target-project/todo/src/test/java/todo/selenium
cp -p ./common-test/java/selenium/ScreenCaptureWebDriverEventListener.java ./target-project/todo/src/test/java/todo/selenium
cp -p ./common-test/java/selenium/SystemErrorPage.java ./target-project/todo/src/test/java/todo/selenium
cp -p ./common-test/java/selenium/WebDriverCreator.java ./target-project/todo/src/test/java/todo/selenium
cp -p ./common-test/java/selenium/WebDriverEventListenerAdapter.java ./target-project/todo/src/test/java/todo/selenium
cp -p ./common-test/java/selenium/WebDriverInputFieldAccessor.java ./target-project/todo/src/test/java/todo/selenium
cp -p ./common-test/java/selenium/WebDriverOperations.java ./target-project/todo/src/test/java/todo/selenium
cp -p ./common-test/java/selenium/WebElementOperations.java ./target-project/todo/src/test/java/todo/selenium

# mybatis3
cp -p ./todo/src/test/java/todo/selenium/todo/TodoTest.java ./target-project/todo-mybatis3/src/test/java/todo/selenium/todo
cp -p ./common-test/resources/META-INF/spring/selenium.properties ./target-project/todo-mybatis3/src/test/resources/META-INF/spring
cp -p ./common-test/resources/META-INF/spring/seleniumContext.xml ./target-project/todo-mybatis3/src/test/resources/META-INF/spring
cp -p ./common-test/java/selenium/FirefoxDriverFactoryBean.java ./target-project/todo-mybatis3/src/test/java/todo/selenium
cp -p ./common-test/java/selenium/FunctionTestSupport.java ./target-project/todo-mybatis3/src/test/java/todo/selenium
cp -p ./common-test/java/selenium/FunctionTestSupportForMultiBrowser.java ./target-project/todo-mybatis3/src/test/java/todo/selenium
cp -p ./common-test/java/selenium/Page.java ./target-project/todo-mybatis3/src/test/java/todo/selenium
cp -p ./common-test/java/selenium/PageOperations.java ./target-project/todo-mybatis3/src/test/java/todo/selenium
cp -p ./common-test/java/selenium/PageSource.java ./target-project/todo-mybatis3/src/test/java/todo/selenium
cp -p ./common-test/java/selenium/ScreenCapture.java ./target-project/todo-mybatis3/src/test/java/todo/selenium
cp -p ./common-test/java/selenium/ScreenCaptureWebDriverEventListener.java ./target-project/todo-mybatis3/src/test/java/todo/selenium
cp -p ./common-test/java/selenium/SystemErrorPage.java ./target-project/todo-mybatis3/src/test/java/todo/selenium
cp -p ./common-test/java/selenium/WebDriverCreator.java ./target-project/todo-mybatis3/src/test/java/todo/selenium
cp -p ./common-test/java/selenium/WebDriverEventListenerAdapter.java ./target-project/todo-mybatis3/src/test/java/todo/selenium
cp -p ./common-test/java/selenium/WebDriverInputFieldAccessor.java ./target-project/todo-mybatis3/src/test/java/todo/selenium
cp -p ./common-test/java/selenium/WebDriverOperations.java ./target-project/todo-mybatis3/src/test/java/todo/selenium
cp -p ./common-test/java/selenium/WebElementOperations.java ./target-project/todo-mybatis3/src/test/java/todo/selenium
# mybatis3-multi
cp -p ./todo/src/test/java/todo/selenium/todo/TodoTest.java ./target-project/todo-mybatis3-multi/todo-mybatis3-multi-selenium/src/test/java/todo/selenium/todo
cp -p ./common-test/resources/META-INF/spring/selenium.properties ./target-project/todo-mybatis3-multi/todo-mybatis3-multi-selenium/src/test/resources/META-INF/spring
cp -p ./common-test/resources/META-INF/spring/seleniumContext.xml ./target-project/todo-mybatis3-multi/todo-mybatis3-multi-selenium/src/test/resources/META-INF/spring
cp -p ./common-test/java/selenium/FirefoxDriverFactoryBean.java ./target-project/todo-mybatis3-multi/todo-mybatis3-multi-selenium/src/test/java/todo/selenium
cp -p ./common-test/java/selenium/FunctionTestSupport.java ./target-project/todo-mybatis3-multi/todo-mybatis3-multi-selenium/src/test/java/todo/selenium
cp -p ./common-test/java/selenium/FunctionTestSupportForMultiBrowser.java ./target-project/todo-mybatis3-multi/todo-mybatis3-multi-selenium/src/test/java/todo/selenium
cp -p ./common-test/java/selenium/Page.java ./target-project/todo-mybatis3-multi/todo-mybatis3-multi-selenium/src/test/java/todo/selenium
cp -p ./common-test/java/selenium/PageOperations.java ./target-project/todo-mybatis3-multi/todo-mybatis3-multi-selenium/src/test/java/todo/selenium
cp -p ./common-test/java/selenium/PageSource.java ./target-project/todo-mybatis3-multi/todo-mybatis3-multi-selenium/src/test/java/todo/selenium
cp -p ./common-test/java/selenium/ScreenCapture.java ./target-project/todo-mybatis3-multi/todo-mybatis3-multi-selenium/src/test/java/todo/selenium
cp -p ./common-test/java/selenium/ScreenCaptureWebDriverEventListener.java ./target-project/todo-mybatis3-multi/todo-mybatis3-multi-selenium/src/test/java/todo/selenium
cp -p ./common-test/java/selenium/SystemErrorPage.java ./target-project/todo-mybatis3-multi/todo-mybatis3-multi-selenium/src/test/java/todo/selenium
cp -p ./common-test/java/selenium/WebDriverCreator.java ./target-project/todo-mybatis3-multi/todo-mybatis3-multi-selenium/src/test/java/todo/selenium
cp -p ./common-test/java/selenium/WebDriverEventListenerAdapter.java ./target-project/todo-mybatis3-multi/todo-mybatis3-multi-selenium/src/test/java/todo/selenium
cp -p ./common-test/java/selenium/WebDriverInputFieldAccessor.java ./target-project/todo-mybatis3-multi/todo-mybatis3-multi-selenium/src/test/java/todo/selenium
cp -p ./common-test/java/selenium/WebDriverOperations.java ./target-project/todo-mybatis3-multi/todo-mybatis3-multi-selenium/src/test/java/todo/selenium
cp -p ./common-test/java/selenium/WebElementOperations.java ./target-project/todo-mybatis3-multi/todo-mybatis3-multi-selenium/src/test/java/todo/selenium
# jpa
cp -p ./todo/src/test/java/todo/selenium/todo/TodoTest.java ./target-project/todo-jpa/src/test/java/todo/selenium/todo
cp -p ./common-test/resources/META-INF/spring/selenium.properties ./target-project/todo-jpa/src/test/resources/META-INF/spring
cp -p ./common-test/resources/META-INF/spring/seleniumContext.xml ./target-project/todo-jpa/src/test/resources/META-INF/spring
cp -p ./common-test/java/selenium/FirefoxDriverFactoryBean.java ./target-project/todo-jpa/src/test/java/todo/selenium
cp -p ./common-test/java/selenium/FunctionTestSupport.java ./target-project/todo-jpa/src/test/java/todo/selenium
cp -p ./common-test/java/selenium/FunctionTestSupportForMultiBrowser.java ./target-project/todo-jpa/src/test/java/todo/selenium
cp -p ./common-test/java/selenium/Page.java ./target-project/todo-jpa/src/test/java/todo/selenium
cp -p ./common-test/java/selenium/PageOperations.java ./target-project/todo-jpa/src/test/java/todo/selenium
cp -p ./common-test/java/selenium/PageSource.java ./target-project/todo-jpa/src/test/java/todo/selenium
cp -p ./common-test/java/selenium/ScreenCapture.java ./target-project/todo-jpa/src/test/java/todo/selenium
cp -p ./common-test/java/selenium/ScreenCaptureWebDriverEventListener.java ./target-project/todo-jpa/src/test/java/todo/selenium
cp -p ./common-test/java/selenium/SystemErrorPage.java ./target-project/todo-jpa/src/test/java/todo/selenium
cp -p ./common-test/java/selenium/WebDriverCreator.java ./target-project/todo-jpa/src/test/java/todo/selenium
cp -p ./common-test/java/selenium/WebDriverEventListenerAdapter.java ./target-project/todo-jpa/src/test/java/todo/selenium
cp -p ./common-test/java/selenium/WebDriverInputFieldAccessor.java ./target-project/todo-jpa/src/test/java/todo/selenium
cp -p ./common-test/java/selenium/WebDriverOperations.java ./target-project/todo-jpa/src/test/java/todo/selenium
cp -p ./common-test/java/selenium/WebElementOperations.java ./target-project/todo-jpa/src/test/java/todo/selenium
# jpa-multi
cp -p ./todo/src/test/java/todo/selenium/todo/TodoTest.java ./target-project/todo-jpa-multi/todo-jpa-multi-selenium/src/test/java/todo/selenium/todo
cp -p ./common-test/resources/META-INF/spring/selenium.properties ./target-project/todo-jpa-multi/todo-jpa-multi-selenium/src/test/resources/META-INF/spring
cp -p ./common-test/resources/META-INF/spring/seleniumContext.xml ./target-project/todo-jpa-multi/todo-jpa-multi-selenium/src/test/resources/META-INF/spring
cp -p ./common-test/java/selenium/FirefoxDriverFactoryBean.java ./target-project/todo-jpa-multi/todo-jpa-multi-selenium/src/test/java/todo/selenium
cp -p ./common-test/java/selenium/FunctionTestSupport.java ./target-project/todo-jpa-multi/todo-jpa-multi-selenium/src/test/java/todo/selenium
cp -p ./common-test/java/selenium/FunctionTestSupportForMultiBrowser.java ./target-project/todo-jpa-multi/todo-jpa-multi-selenium/src/test/java/todo/selenium
cp -p ./common-test/java/selenium/Page.java ./target-project/todo-jpa-multi/todo-jpa-multi-selenium/src/test/java/todo/selenium
cp -p ./common-test/java/selenium/PageOperations.java ./target-project/todo-jpa-multi/todo-jpa-multi-selenium/src/test/java/todo/selenium
cp -p ./common-test/java/selenium/PageSource.java ./target-project/todo-jpa-multi/todo-jpa-multi-selenium/src/test/java/todo/selenium
cp -p ./common-test/java/selenium/ScreenCapture.java ./target-project/todo-jpa-multi/todo-jpa-multi-selenium/src/test/java/todo/selenium
cp -p ./common-test/java/selenium/ScreenCaptureWebDriverEventListener.java ./target-project/todo-jpa-multi/todo-jpa-multi-selenium/src/test/java/todo/selenium
cp -p ./common-test/java/selenium/SystemErrorPage.java ./target-project/todo-jpa-multi/todo-jpa-multi-selenium/src/test/java/todo/selenium
cp -p ./common-test/java/selenium/WebDriverCreator.java ./target-project/todo-jpa-multi/todo-jpa-multi-selenium/src/test/java/todo/selenium
cp -p ./common-test/java/selenium/WebDriverEventListenerAdapter.java ./target-project/todo-jpa-multi/todo-jpa-multi-selenium/src/test/java/todo/selenium
cp -p ./common-test/java/selenium/WebDriverInputFieldAccessor.java ./target-project/todo-jpa-multi/todo-jpa-multi-selenium/src/test/java/todo/selenium
cp -p ./common-test/java/selenium/WebDriverOperations.java ./target-project/todo-jpa-multi/todo-jpa-multi-selenium/src/test/java/todo/selenium
cp -p ./common-test/java/selenium/WebElementOperations.java ./target-project/todo-jpa-multi/todo-jpa-multi-selenium/src/test/java/todo/selenium

VER="${ARCHETYPE_VERSION:0:3}"

# modify pom
# VER 1.0.x or 5.0.x or 5.1.x
if test ${VER} = 1.0 ||  test ${VER} = 5.0 || test ${VER} = 5.1 ; then
# single without any DB configuration
find ./target-project/todo/pom.xml | xargs sed -i -e 's|</dependencies>|    <dependency>\n            <groupId>org.seleniumhq.selenium</groupId>\n            <artifactId>selenium-java</artifactId>\n            <version>${selenium.version}</version>\n            <scope>test</scope>\n        </dependency>\n    </dependencies>|'
find ./target-project/todo/pom.xml | xargs sed -i -e 's|</properties>|    <selenium.version>2.46.0</selenium.version>\n    </properties>|'
# mybatis3
find ./target-project/todo-mybatis3/pom.xml | xargs sed -i -e 's|</dependencies>|    <dependency>\n            <groupId>org.seleniumhq.selenium</groupId>\n            <artifactId>selenium-java</artifactId>\n            <version>${selenium.version}</version>\n            <scope>test</scope>\n        </dependency>\n    </dependencies>|'
find ./target-project/todo-mybatis3/pom.xml | xargs sed -i -e 's|</properties>|    <selenium.version>2.46.0</selenium.version>\n    </properties>|'
# jpa
find ./target-project/todo-jpa/pom.xml | xargs sed -i -e 's|</dependencies>|    <dependency>\n            <groupId>org.seleniumhq.selenium</groupId>\n            <artifactId>selenium-java</artifactId>\n            <version>${selenium.version}</version>\n            <scope>test</scope>\n        </dependency>\n    </dependencies>|'
find ./target-project/todo-jpa/pom.xml | xargs sed -i -e 's|</properties>|    <selenium.version>2.46.0</selenium.version>\n    </properties>|'
fi

# modify selenium.properties
find ./target-project/todo/src/test/resources/META-INF/spring/selenium.properties | xargs sed -i -e 's|selenium.contextName=|selenium.contextName=todo|'
find ./target-project/todo-mybatis3/src/test/resources/META-INF/spring/selenium.properties | xargs sed -i -e 's|selenium.contextName=|selenium.contextName=todo-mybatis3|'
find ./target-project/todo-mybatis3-multi/todo-mybatis3-multi-selenium/src/test/resources/META-INF/spring/selenium.properties | xargs sed -i -e 's|selenium.contextName=|selenium.contextName=todo-mybatis3-multi-web|'
find ./target-project/todo-jpa/src/test/resources/META-INF/spring/selenium.properties | xargs sed -i -e 's|selenium.contextName=|selenium.contextName=todo-jpa|'
find ./target-project/todo-jpa-multi/todo-jpa-multi-selenium/src/test/resources/META-INF/spring/selenium.properties | xargs sed -i -e 's|selenium.contextName=|selenium.contextName=todo-jpa-multi-web|'

# modify java
# modify default page
find ./target-project/todo/src/test/java/todo/selenium/FunctionTestSupport.java | xargs sed -i -e 's|webDriverOperations.displayPage(getPackageRootUrl());|webDriverOperations.displayPage(getPackageRootUrl() + "list");|'
find ./target-project/todo-mybatis3/src/test/java/todo/selenium/FunctionTestSupport.java | xargs sed -i -e 's|webDriverOperations.displayPage(getPackageRootUrl());|webDriverOperations.displayPage(getPackageRootUrl() + "list");|'
find ./target-project/todo-mybatis3-multi/todo-mybatis3-multi-selenium/src/test/java/todo/selenium/FunctionTestSupport.java | xargs sed -i -e 's|webDriverOperations.displayPage(getPackageRootUrl());|webDriverOperations.displayPage(getPackageRootUrl() + "list");|'
find ./target-project/todo-jpa/src/test/java/todo/selenium/FunctionTestSupport.java | xargs sed -i -e 's|webDriverOperations.displayPage(getPackageRootUrl());|webDriverOperations.displayPage(getPackageRootUrl() + "list");|'
find ./target-project/todo-jpa-multi/todo-jpa-multi-selenium/src/test/java/todo/selenium/FunctionTestSupport.java | xargs sed -i -e 's|webDriverOperations.displayPage(getPackageRootUrl());|webDriverOperations.displayPage(getPackageRootUrl() + "list");|'