#!/bin/bash

# make app test
# jpa
mkdir -p ./target-project/todo-api-jpa/src/test/java/todo/selenium/todo

# jpa-multi
mkdir -p ./target-project/todo-api-jpa-multi/todo-api-jpa-multi-selenium/src/test/java/todo/selenium/todo

# mybatis3
mkdir -p ./target-project/todo-api-mybatis3/src/test/java/todo/selenium/todo

# mybatis3-multi
mkdir -p ./target-project/todo-api-mybatis3-multi/todo-api-mybatis3-multi-selenium/src/test/java/todo/selenium/todo

# cp test sources
# jpa
cp -p ./todo-rest/src/test/java/jpa/todo/selenium/todo/TodoApiTest.java ./target-project/todo-api-jpa/src/test/java/todo/selenium/todo

# jpa-multi
cp -p ./todo-rest/src/test/java/jpa-multi/todo/selenium/todo/TodoApiTest.java ./target-project/todo-api-jpa-multi/todo-api-jpa-multi-selenium/src/test/java/todo/selenium/todo

# mybatis3
cp -p ./todo-rest/src/test/java/mybatis3/todo/selenium/todo/TodoApiTest.java ./target-project/todo-api-mybatis3/src/test/java/todo/selenium/todo

# mybatis3-multi
cp -p ./todo-rest/src/test/java/mybatis3-multi/todo/selenium/todo/TodoApiTest.java ./target-project/todo-api-mybatis3-multi/todo-api-mybatis3-multi-selenium/src/test/java/todo/selenium/todo/

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

