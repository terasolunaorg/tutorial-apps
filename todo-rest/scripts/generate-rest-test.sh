#!/bin/bash

# make app test
# single without any DB configuration
mkdir -p ./target-project/todo-api/src/test/java/todo/selenium/todo
mkdir -p ./target-project/todo-api/src/test/resources/META-INF/spring

# mybatis3
mkdir -p ./target-project/todo-api-mybatis3/src/test/java/todo/selenium/todo
mkdir -p ./target-project/todo-api-mybatis3/src/test/resources/META-INF/spring

# mybatis3-multi
mkdir -p ./target-project/todo-api-mybatis3-multi/todo-api-mybatis3-multi-selenium/src/test/java/todo/selenium/todo

# jpa
mkdir -p ./target-project/todo-api-jpa/src/test/java/todo/selenium/todo
mkdir -p ./target-project/todo-api-jpa/src/test/resources/META-INF/spring

# jpa-multi
mkdir -p ./target-project/todo-api-jpa-multi/todo-api-jpa-multi-selenium/src/test/java/todo/selenium/todo

# cp test sources
# single without any DB configuration
cp -p ./todo-rest/src/test/java/todo/selenium/todo/TodoApiTest.java ./target-project/todo-api/src/test/java/todo/selenium/todo
cp -p ./common-test/resources/META-INF/spring/selenium.properties ./target-project/todo-api/src/test/resources/META-INF/spring
cp -p ./common-test/resources/META-INF/spring/seleniumContext.xml ./target-project/todo-api/src/test/resources/META-INF/spring
cp -f -p -r ./common-test/java/selenium ./target-project/todo-api/src/test/java/todo

# mybatis3
cp -p ./todo-rest/src/test/java/todo/selenium/todo/TodoApiTest.java ./target-project/todo-api-mybatis3/src/test/java/todo/selenium/todo
cp -p ./common-test/resources/META-INF/spring/selenium.properties ./target-project/todo-api-mybatis3/src/test/resources/META-INF/spring
cp -p ./common-test/resources/META-INF/spring/seleniumContext.xml ./target-project/todo-api-mybatis3/src/test/resources/META-INF/spring
cp -f -p -r ./common-test/java/selenium ./target-project/todo-api-mybatis3/src/test/java/todo

# mybatis3-multi
cp -p ./todo-rest/src/test/java/todo/selenium/todo/TodoApiTest.java ./target-project/todo-api-mybatis3-multi/todo-api-mybatis3-multi-selenium/src/test/java/todo/selenium/todo/
cp -p ./common-test/resources/META-INF/spring/selenium.properties ./target-project/todo-api-mybatis3-multi/todo-api-mybatis3-multi-selenium/src/test/resources/META-INF/spring
cp -p ./common-test/resources/META-INF/spring/seleniumContext.xml ./target-project/todo-api-mybatis3-multi/todo-api-mybatis3-multi-selenium/src/test/resources/META-INF/spring
cp -f -p -r ./common-test/java/selenium ./target-project/todo-api-mybatis3-multi/todo-api-mybatis3-multi-selenium/src/test/java/todo

# jpa
cp -p ./todo-rest/src/test/java/todo/selenium/todo/TodoApiTest.java ./target-project/todo-api-jpa/src/test/java/todo/selenium/todo
cp -p ./common-test/resources/META-INF/spring/selenium.properties ./target-project/todo-api-jpa/src/test/resources/META-INF/spring
cp -p ./common-test/resources/META-INF/spring/seleniumContext.xml ./target-project/todo-api-jpa/src/test/resources/META-INF/spring
cp -f -p -r ./common-test/java/selenium ./target-project/todo-api-jpa/src/test/java/todo

# jpa-multi
cp -p ./todo-rest/src/test/java/todo/selenium/todo/TodoApiTest.java ./target-project/todo-api-jpa-multi/todo-api-jpa-multi-selenium/src/test/java/todo/selenium/todo
cp -p ./common-test/resources/META-INF/spring/selenium.properties ./target-project/todo-api-jpa-multi/todo-api-jpa-multi-selenium/src/test/resources/META-INF/spring
cp -p ./common-test/resources/META-INF/spring/seleniumContext.xml ./target-project/todo-api-jpa-multi/todo-api-jpa-multi-selenium/src/test/resources/META-INF/spring
cp -f -p -r ./common-test/java/selenium ./target-project/todo-api-jpa-multi/todo-api-jpa-multi-selenium/src/test/java/todo

# modify pom
# single without any DB configuration
find ./target-project/todo-api/pom.xml | xargs sed -i -e 's|<dependencies>|<dependencies>\n        <dependency>\n            <groupId>com.jayway.restassured</groupId>\n            <artifactId>rest-assured</artifactId>\n            <version>${com.jayway.restassured.version}</version>\n            <scope>test</scope>\n        </dependency>|'
find ./target-project/todo-api/pom.xml | xargs sed -i -e 's|</properties>|    <com.jayway.restassured.version>2.7.0</com.jayway.restassured.version>\n    </properties>|'

# mybatis3
find ./target-project/todo-api-mybatis3/pom.xml | xargs sed -i -e 's|<dependencies>|<dependencies>\n        <dependency>\n            <groupId>com.jayway.restassured</groupId>\n            <artifactId>rest-assured</artifactId>\n            <version>${com.jayway.restassured.version}</version>\n            <scope>test</scope>\n        </dependency>|'
find ./target-project/todo-api-mybatis3/pom.xml | xargs sed -i -e 's|</properties>|    <com.jayway.restassured.version>2.7.0</com.jayway.restassured.version>\n    </properties>|'

# mybatis3-multi
find ./target-project/todo-api-mybatis3-multi/todo-api-mybatis3-multi-selenium/pom.xml | xargs sed -i -e 's|<dependencies>|<dependencies>\n        <dependency>\n            <groupId>com.jayway.restassured</groupId>\n            <artifactId>rest-assured</artifactId>\n            <version>${com.jayway.restassured.version}</version>\n            <scope>test</scope>\n        </dependency>|'
find ./target-project/todo-api-mybatis3-multi/todo-api-mybatis3-multi-selenium/pom.xml | xargs sed -i -e 's|</dependencies>|</dependencies>\n\n    <properties>\n        <com.jayway.restassured.version>2.7.0</com.jayway.restassured.version>\n    </properties>|'

# jpa
find ./target-project/todo-api-jpa/pom.xml | xargs sed -i -e 's|<dependencies>|<dependencies>\n        <dependency>\n            <groupId>com.jayway.restassured</groupId>\n            <artifactId>rest-assured</artifactId>\n            <version>${com.jayway.restassured.version}</version>\n            <scope>test</scope>\n        </dependency>|'
find ./target-project/todo-api-jpa/pom.xml | xargs sed -i -e 's|</properties>|    <com.jayway.restassured.version>2.7.0</com.jayway.restassured.version>\n    </properties>|'

# jpa-multi
find ./target-project/todo-api-jpa-multi/todo-api-jpa-multi-selenium/pom.xml | xargs sed -i -e 's|<dependencies>|<dependencies>\n        <dependency>\n            <groupId>com.jayway.restassured</groupId>\n            <artifactId>rest-assured</artifactId>\n            <version>${com.jayway.restassured.version}</version>\n            <scope>test</scope>\n        </dependency>|'
find ./target-project/todo-api-jpa-multi/todo-api-jpa-multi-selenium/pom.xml | xargs sed -i -e 's|</dependencies>|</dependencies>\n\n    <properties>\n        <com.jayway.restassured.version>2.7.0</com.jayway.restassured.version>\n    </properties>|'


VER="${ARCHETYPE_VERSION:0:3}"

# modify pom
# VER 1.0.x or 5.0.x or 5.1.x
if test ${VER} = 1.0 ||  test ${VER} = 5.0 || test ${VER} = 5.1 ; then
# single without any DB configuration
find ./target-project/todo-api/pom.xml | xargs sed -i -e 's|</dependencies>|    <dependency>\n            <groupId>org.seleniumhq.selenium</groupId>\n            <artifactId>selenium-java</artifactId>\n            <version>${selenium.version}</version>\n            <scope>test</scope>\n        </dependency>\n    </dependencies>|'
find ./target-project/todo-api/pom.xml | xargs sed -i -e 's|</properties>|    <selenium.version>2.46.0</selenium.version>\n    </properties>|'
# mybatis3
find ./target-project/todo-api-mybatis3/pom.xml | xargs sed -i -e 's|</dependencies>|    <dependency>\n            <groupId>org.seleniumhq.selenium</groupId>\n            <artifactId>selenium-java</artifactId>\n            <version>${selenium.version}</version>\n            <scope>test</scope>\n        </dependency>\n    </dependencies>|'
find ./target-project/todo-api-mybatis3/pom.xml | xargs sed -i -e 's|</properties>|    <selenium.version>2.46.0</selenium.version>\n    </properties>|'
# jpa
find ./target-project/todo-api-jpa/pom.xml | xargs sed -i -e 's|</dependencies>|    <dependency>\n            <groupId>org.seleniumhq.selenium</groupId>\n            <artifactId>selenium-java</artifactId>\n            <version>${selenium.version}</version>\n            <scope>test</scope>\n        </dependency>\n    </dependencies>|'
find ./target-project/todo-api-jpa/pom.xml | xargs sed -i -e 's|</properties>|    <selenium.version>2.46.0</selenium.version>\n    </properties>|'
fi

# modify selenium.properties
# single without any DB configuration
find ./target-project/todo-api/src/test/resources/META-INF/spring/selenium.properties | xargs sed -i -e 's|selenium.contextName=|selenium.contextName=todo-api|'

# mybatis3
find ./target-project/todo-api-mybatis3/src/test/resources/META-INF/spring/selenium.properties | xargs sed -i -e 's|selenium.contextName=|selenium.contextName=todo-api-mybatis3|'

# mybatis3-multi
find ./target-project/todo-api-mybatis3-multi/todo-api-mybatis3-multi-selenium/src/test/resources/META-INF/spring/selenium.properties | xargs sed -i -e 's|selenium.contextName=|selenium.contextName=todo-api-mybatis3-multi-web|'

# jpa
find ./target-project/todo-api-jpa/src/test/resources/META-INF/spring/selenium.properties | xargs sed -i -e 's|selenium.contextName=|selenium.contextName=todo-api-jpa|'

# jpa-multi
find ./target-project/todo-api-jpa-multi/todo-api-jpa-multi-selenium/src/test/resources/META-INF/spring/selenium.properties | xargs sed -i -e 's|selenium.contextName=|selenium.contextName=todo-api-jpa-multi-web|'

# modify seleniumContext.xml
# single without any DB configuration
find ./target-project/todo-api/src/test/resources/META-INF/spring/seleniumContext.xml | xargs sed -i -e 's|<bean class="todo.selenium.WebDriverCreator" />|<bean id="restLog"\n        class="todo.selenium.RestLog" />\n\n    <bean class="todo.selenium.WebDriverCreator" />|'

# mybatis3
find ./target-project/todo-api-mybatis3/src/test/resources/META-INF/spring/seleniumContext.xml | xargs sed -i -e 's|<bean class="todo.selenium.WebDriverCreator" />|<bean id="restLog"\n        class="todo.selenium.RestLog" />\n\n    <bean class="todo.selenium.WebDriverCreator" />|'

# mybatis3-multi
find ./target-project/todo-api-mybatis3-multi/todo-api-mybatis3-multi-selenium/src/test/resources/META-INF/spring/seleniumContext.xml | xargs sed -i -e 's|<bean class="todo.selenium.WebDriverCreator" />|<bean id="restLog"\n        class="todo.selenium.RestLog" />\n\n    <bean class="todo.selenium.WebDriverCreator" />|'

# jpa
find ./target-project/todo-api-jpa/src/test/resources/META-INF/spring/seleniumContext.xml | xargs sed -i -e 's|<bean class="todo.selenium.WebDriverCreator" />|<bean id="restLog"\n        class="todo.selenium.RestLog" />\n\n    <bean class="todo.selenium.WebDriverCreator" />|'

# jpa-multi
find ./target-project/todo-api-jpa-multi/todo-api-jpa-multi-selenium/src/test/resources/META-INF/spring/seleniumContext.xml | xargs sed -i -e 's|<bean class="todo.selenium.WebDriverCreator" />|<bean id="restLog"\n        class="todo.selenium.RestLog" />\n\n    <bean class="todo.selenium.WebDriverCreator" />|'
