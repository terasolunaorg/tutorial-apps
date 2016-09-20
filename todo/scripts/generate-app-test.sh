#!/bin/bash

# make app test
# jpa
mkdir -p ./target-project/todo-jpa/src/test/java/todo/selenium/todo
mkdir -p ./target-project/todo-jpa/src/test/resources/META-INF/spring
# jpa-multi
mkdir -p ./target-project/todo-jpa-multi/todo-jpa-multi-selenium/src/test/java/todo/selenium/todo
# mybatis3
mkdir -p ./target-project/todo-mybatis3/src/test/java/todo/selenium/todo
mkdir -p ./target-project/todo-mybatis3/src/test/resources/META-INF/spring
# mybatis3-multi
mkdir -p ./target-project/todo-mybatis3-multi/todo-mybatis3-multi-selenium/src/test/java/todo/selenium/todo

# cp test sources
# jpa
cp -p ./todo/src/test/java/jpa/todo/selenium/todo/TodoTest.java ./target-project/todo-jpa/src/test/java/todo/selenium/todo
cp -p ./todo/src/test/resources/jpa/META-INF/spring/selenium.properties ./target-project/todo-jpa/src/test/resources/META-INF/spring
cp -p ./todo/src/test/resources/jpa/META-INF/spring/seleniumContext.xml ./target-project/todo-jpa/src/test/resources/META-INF/spring
# jpa-multi
cp -p ./todo/src/test/java/jpa-multi/todo/selenium/todo/TodoTest.java ./target-project/todo-jpa-multi/todo-jpa-multi-selenium/src/test/java/todo/selenium/todo
# mybatis3
cp -p ./todo/src/test/java/mybatis3/todo/selenium/todo/TodoTest.java ./target-project/todo-mybatis3/src/test/java/todo/selenium/todo
cp -p ./todo/src/test/resources/mybatis3/META-INF/spring/selenium.properties ./target-project/todo-mybatis3/src/test/resources/META-INF/spring
cp -p ./todo/src/test/resources/mybatis3/META-INF/spring/seleniumContext.xml ./target-project/todo-mybatis3/src/test/resources/META-INF/spring
# mybatis3-multi
cp -p ./todo/src/test/java/mybatis3-multi/todo/selenium/todo/TodoTest.java ./target-project/todo-mybatis3-multi/todo-mybatis3-multi-selenium/src/test/java/todo/selenium/todo

# modify pom
# jpa
find ./target-project/todo-jpa/pom.xml | xargs sed -i -e 's|</dependencies>|    <dependency>\n            <groupId>org.seleniumhq.selenium</groupId>\n            <artifactId>selenium-java</artifactId>\n            <version>2.46.0</version>\n            <scope>test</scope>\n        </dependency>\n    </dependencies>|'
# mybatis3
find ./target-project/todo-mybatis3/pom.xml | xargs sed -i -e 's|</dependencies>|    <dependency>\n            <groupId>org.seleniumhq.selenium</groupId>\n            <artifactId>selenium-java</artifactId>\n            <version>2.46.0</version>\n            <scope>test</scope>\n        </dependency>\n    </dependencies>|'
