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
cp -p -r ./common-test/resources ./target-project/todo/src/test
cp -p -r ./common-test/java/selenium ./target-project/todo/src/test/java/todo

# mybatis3
cp -p ./todo/src/test/java/todo/selenium/todo/TodoTest.java ./target-project/todo-mybatis3/src/test/java/todo/selenium/todo
cp -p -r ./common-test/resources ./target-project/todo-mybatis3/src/test
cp -p -r ./common-test/java/selenium ./target-project/todo-mybatis3/src/test/java/todo
# mybatis3-multi
cp -p ./todo/src/test/java/todo/selenium/todo/TodoTest.java ./target-project/todo-mybatis3-multi/todo-mybatis3-multi-selenium/src/test/java/todo/selenium/todo
cp -p -r ./common-test/resources ./target-project/todo-mybatis3-multi/todo-mybatis3-multi-selenium/src/test
cp -p -r ./common-test/java/selenium ./target-project/todo-mybatis3-multi/todo-mybatis3-multi-selenium/src/test/java/todo
# jpa
cp -p ./todo/src/test/java/todo/selenium/todo/TodoTest.java ./target-project/todo-jpa/src/test/java/todo/selenium/todo
cp -p -r ./common-test/resources ./target-project/todo-jpa/src/test
cp -p -r ./common-test/java/selenium ./target-project/todo-jpa/src/test/java/todo
# jpa-multi
cp -p ./todo/src/test/java/todo/selenium/todo/TodoTest.java ./target-project/todo-jpa-multi/todo-jpa-multi-selenium/src/test/java/todo/selenium/todo
cp -p -r ./common-test/resources ./target-project/todo-jpa-multi/todo-jpa-multi-selenium/src/test
cp -p -r ./common-test/java/selenium ./target-project/todo-jpa-multi/todo-jpa-multi-selenium/src/test/java/todo

VER="${ARCHETYPE_VERSION:0:5}"

# modify pom
# VER 1.0.x or 5.0.x or 5.1.x
case "${VER}" in
"1.0.0" | "1.0.1" | "1.0.2" | "1.0.3" | "1.0.4" | "1.0.5" | "5.0.0" | "5.0.1" | "5.0.2" | "5.1.0" | "5.1.1" ) 
# single without any DB configuration
find ./target-project/todo/pom.xml | xargs sed -i -e 's|</dependencies>|    <dependency>\n            <groupId>org.seleniumhq.selenium</groupId>\n            <artifactId>selenium-java</artifactId>\n            <scope>test</scope>\n        </dependency>\n    </dependencies>|'
# mybatis3
find ./target-project/todo-mybatis3/pom.xml | xargs sed -i -e 's|</dependencies>|    <dependency>\n            <groupId>org.seleniumhq.selenium</groupId>\n            <artifactId>selenium-java</artifactId>\n            <scope>test</scope>\n        </dependency>\n    </dependencies>|'
# jpa
find ./target-project/todo-jpa/pom.xml | xargs sed -i -e 's|</dependencies>|    <dependency>\n            <groupId>org.seleniumhq.selenium</groupId>\n            <artifactId>selenium-java</artifactId>\n            <scope>test</scope>\n        </dependency>\n    </dependencies>|'
esac

# modify selenium.properties
find ./target-project/todo/src/test/resources/META-INF/spring/selenium.properties | xargs sed -i -e 's|selenium.contextName=|selenium.contextName=todo|'
find ./target-project/todo-mybatis3/src/test/resources/META-INF/spring/selenium.properties | xargs sed -i -e 's|selenium.contextName=|selenium.contextName=todo-mybatis3|'
find ./target-project/todo-mybatis3-multi/todo-mybatis3-multi-selenium/src/test/resources/META-INF/spring/selenium.properties | xargs sed -i -e 's|selenium.contextName=|selenium.contextName=todo-mybatis3-multi-web|'
find ./target-project/todo-jpa/src/test/resources/META-INF/spring/selenium.properties | xargs sed -i -e 's|selenium.contextName=|selenium.contextName=todo-jpa|'
find ./target-project/todo-jpa-multi/todo-jpa-multi-selenium/src/test/resources/META-INF/spring/selenium.properties | xargs sed -i -e 's|selenium.contextName=|selenium.contextName=todo-jpa-multi-web|'
