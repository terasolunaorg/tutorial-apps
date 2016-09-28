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
cp -p ./todo/src/test/java/todo/selenium/todo/TodoTest.java ./target-project/todo-jpa/src/test/java/todo/selenium/todo
cp -p ./todo/src/test/resources/jpa/META-INF/spring/selenium.properties ./target-project/todo-jpa/src/test/resources/META-INF/spring
cp -p ./todo/src/test/resources/jpa/META-INF/spring/seleniumContext.xml ./target-project/todo-jpa/src/test/resources/META-INF/spring
# jpa-multi
cp -p ./todo/src/test/java/todo/selenium/todo/TodoTest.java ./target-project/todo-jpa-multi/todo-jpa-multi-selenium/src/test/java/todo/selenium/todo
# mybatis3
cp -p ./todo/src/test/java/todo/selenium/todo/TodoTest.java ./target-project/todo-mybatis3/src/test/java/todo/selenium/todo
cp -p ./todo/src/test/resources/mybatis3/META-INF/spring/selenium.properties ./target-project/todo-mybatis3/src/test/resources/META-INF/spring
cp -p ./todo/src/test/resources/mybatis3/META-INF/spring/seleniumContext.xml ./target-project/todo-mybatis3/src/test/resources/META-INF/spring
# mybatis3-multi
cp -p ./todo/src/test/java/todo/selenium/todo/TodoTest.java ./target-project/todo-mybatis3-multi/todo-mybatis3-multi-selenium/src/test/java/todo/selenium/todo

VER="${ARCHETYPE_VERSION:0:3}"

# modify pom
# VER 1.0.x or 5.0.x or 5.1.x
if test ${VER} = 1.0 ||  test ${VER} = 5.0 || test ${VER} = 5.1 ; then
# jpa
find ./target-project/todo-jpa/pom.xml | xargs sed -i -e 's|</dependencies>|    <dependency>\n            <groupId>org.seleniumhq.selenium</groupId>\n            <artifactId>selenium-java</artifactId>\n            <version>${selenium.version}</version>\n            <scope>test</scope>\n        </dependency>\n    </dependencies>|'
find ./target-project/todo-jpa/pom.xml | xargs sed -i -e 's|</properties>|    <selenium.version>2.46.0</selenium.version>\n    </properties>|'
# mybatis3
find ./target-project/todo-mybatis3/pom.xml | xargs sed -i -e 's|</dependencies>|    <dependency>\n            <groupId>org.seleniumhq.selenium</groupId>\n            <artifactId>selenium-java</artifactId>\n            <version>${selenium.version}</version>\n            <scope>test</scope>\n        </dependency>\n    </dependencies>|'
find ./target-project/todo-mybatis3/pom.xml | xargs sed -i -e 's|</properties>|    <selenium.version>2.46.0</selenium.version>\n    </properties>|'
fi

# modify selenium.properties
# mybatis3-multi
echo "selenium.todoListUrl=\${selenium.applicationContextUrl}/todo/list" >> ./target-project/todo-mybatis3-multi/todo-mybatis3-multi-selenium/src/test/resources/META-INF/spring/selenium.properties
# jpa-multi
echo "selenium.todoListUrl=\${selenium.applicationContextUrl}/todo/list" >> ./target-project/todo-jpa-multi/todo-jpa-multi-selenium/src/test/resources/META-INF/spring/selenium.properties

# modify seleniumContext.xml
# mybatis3-multi
find ./target-project/todo-mybatis3-multi/todo-mybatis3-multi-selenium/src/test/resources/META-INF/spring/seleniumContext.xml | xargs sed -i -e 's|prototype|singleton|'
# jpa-multi
find ./target-project/todo-jpa-multi/todo-jpa-multi-selenium/src/test/resources/META-INF/spring/seleniumContext.xml | xargs sed -i -e 's|prototype|singleton|'
