#!/bin/bash

# make app test
# jpa
mkdir -p ./target-project/todo-api-jpa/src/test/java/todo/selenium/todo
mkdir -p ./target-project/todo-api-jpa/src/test/resources/META-INF/spring

# jpa-multi
mkdir -p ./target-project/todo-api-jpa-multi/todo-api-jpa-multi-selenium/src/test/java/todo/selenium/todo

# mybatis3
mkdir -p ./target-project/todo-api-mybatis3/src/test/java/todo/selenium/todo
mkdir -p ./target-project/todo-api-mybatis3/src/test/resources/META-INF/spring

# mybatis3-multi
mkdir -p ./target-project/todo-api-mybatis3-multi/todo-api-mybatis3-multi-selenium/src/test/java/todo/selenium/todo

# map
mkdir -p ./target-project/todo-api/src/test/java/todo/selenium/todo
mkdir -p ./target-project/todo-api/src/test/resources/META-INF/spring

# cp test sources
# jpa
cp -p ./todo-rest/src/test/java/todo/selenium/todo/TodoApiTest.java ./target-project/todo-api-jpa/src/test/java/todo/selenium/todo
cp -p ./todo-rest/src/test/resources/jpa/META-INF/spring/selenium.properties ./target-project/todo-api-jpa/src/test/resources/META-INF/spring
cp -p ./todo-rest/src/test/resources/jpa/META-INF/spring/seleniumContext.xml ./target-project/todo-api-jpa/src/test/resources/META-INF/spring

# jpa-multi
cp -p ./todo-rest/src/test/java/todo/selenium/todo/TodoApiTest.java ./target-project/todo-api-jpa-multi/todo-api-jpa-multi-selenium/src/test/java/todo/selenium/todo

# mybatis3
cp -p ./todo-rest/src/test/java/todo/selenium/todo/TodoApiTest.java ./target-project/todo-api-mybatis3/src/test/java/todo/selenium/todo
cp -p ./todo-rest/src/test/resources/mybatis3/META-INF/spring/selenium.properties ./target-project/todo-api-mybatis3/src/test/resources/META-INF/spring
cp -p ./todo-rest/src/test/resources/mybatis3/META-INF/spring/seleniumContext.xml ./target-project/todo-api-mybatis3/src/test/resources/META-INF/spring

# mybatis3-multi
cp -p ./todo-rest/src/test/java/todo/selenium/todo/TodoApiTest.java ./target-project/todo-api-mybatis3-multi/todo-api-mybatis3-multi-selenium/src/test/java/todo/selenium/todo/

# map
cp -p ./todo-rest/src/test/java/todo/selenium/todo/TodoApiTest.java ./target-project/todo-api-mybatis3/src/test/java/todo/selenium/todo
cp -p ./todo-rest/src/test/resources/map/META-INF/spring/selenium.properties ./target-project/todo-api/src/test/resources/META-INF/spring
cp -p ./todo-rest/src/test/resources/map/META-INF/spring/seleniumContext.xml ./target-project/todo-api/src/test/resources/META-INF/spring

# modify pom
# jpa-multi
find ./target-project/todo-api-jpa-multi/todo-api-jpa-multi-selenium/pom.xml | xargs sed -i -e 's|<dependencies>|<dependencies>\n    <dependency>\n            <groupId>io.rest-assured</groupId>\n            <artifactId>rest-assured</artifactId>\n            <version>${rest-assured.version}</version>\n            <scope>test</scope>\n        </dependency>|'
find ./target-project/todo-api-jpa-multi/todo-api-jpa-multi-selenium/pom.xml | xargs sed -i -e 's|</dependencies>|</dependencies>\n\n    <properties>\n        <rest-assured.version>3.0.1</rest-assured.version>\n    </properties>|'

# mybatis3-multi
find ./target-project/todo-api-mybatis3-multi/todo-api-mybatis3-multi-selenium/pom.xml | xargs sed -i -e 's|<dependencies>|<dependencies>\n    <dependency>\n            <groupId>io.rest-assured</groupId>\n            <artifactId>rest-assured</artifactId>\n            <version>${rest-assured.version}</version>\n            <scope>test</scope>\n        </dependency>|'
find ./target-project/todo-api-mybatis3-multi/todo-api-mybatis3-multi-selenium/pom.xml | xargs sed -i -e 's|</dependencies>|</dependencies>\n\n    <properties>\n        <rest-assured.version>3.0.1</rest-assured.version>\n    </properties>|'

# jpa
find ./target-project/todo-api-jpa/pom.xml | xargs sed -i -e 's|<dependencies>|<dependencies>\n    <dependency>\n            <groupId>io.rest-assured</groupId>\n            <artifactId>rest-assured</artifactId>\n            <version>${rest-assured.version}</version>\n            <scope>test</scope>\n        </dependency>|'
find ./target-project/todo-api-jpa/pom.xml | xargs sed -i -e 's|</properties>|    <rest-assured.version>3.0.1</rest-assured.version>\n    </properties>|'

# mybatis3
find ./target-project/todo-api-mybatis3/pom.xml | xargs sed -i -e 's|<dependencies>|<dependencies>\n    <dependency>\n            <groupId>io.rest-assured</groupId>\n            <artifactId>rest-assured</artifactId>\n            <version>${rest-assured.version}</version>\n            <scope>test</scope>\n        </dependency>|'
find ./target-project/todo-api-mybatis3/pom.xml | xargs sed -i -e 's|</properties>|    <rest-assured.version>3.0.1</rest-assured.version>\n    </properties>|'

# map
find ./target-project/todo-api/pom.xml | xargs sed -i -e 's|<dependencies>|<dependencies>\n    <dependency>\n            <groupId>io.rest-assured</groupId>\n            <artifactId>rest-assured</artifactId>\n            <version>${rest-assured.version}</version>\n            <scope>test</scope>\n        </dependency>|'
find ./target-project/todo-api/pom.xml | xargs sed -i -e 's|</properties>|    <rest-assured.version>3.0.1</rest-assured.version>\n    </properties>|'

# modify selenium.properties
# jpa-multi
echo "selenium.todoApiUrl=\${selenium.applicationContextUrl}/api/v1/todos" >> ./target-project/todo-api-jpa-multi/todo-api-jpa-multi-selenium/src/test/resources/META-INF/spring/selenium.properties

#mybatis3-multi
echo "selenium.todoApiUrl=\${selenium.applicationContextUrl}/api/v1/todos" >> ./target-project/todo-api-mybatis3-multi/todo-api-mybatis3-multi-selenium/src/test/resources/META-INF/spring/selenium.properties
