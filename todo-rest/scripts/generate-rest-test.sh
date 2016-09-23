#!/bin/bash

#${ARCHETYPE_VERSION}=terasoluna-gfw-blank-archetype-version

# make app test
# jpa
mkdir -p ./target-project/todo-api-jpa/src/test/java/todo/restfuse/todo/common
mkdir -p ./target-project/todo-api-jpa/src/test/resources/testData

# jpa-multi
mkdir -p ./target-project/todo-api-jpa-multi/todo-api-jpa-multi-selenium/src/test/java/todo/restfuse/todo/common
mkdir -p ./target-project/todo-api-jpa-multi/todo-api-jpa-multi-selenium/src/test/resources/testData

# mybatis3
mkdir -p ./target-project/todo-api-mybatis3/src/test/java/todo/restfuse/todo/common
mkdir -p ./target-project/todo-api-mybatis3/src/test/resources/testData

# mybatis3-multi
mkdir -p ./target-project/todo-api-mybatis3-multi/todo-api-mybatis3-multi-selenium/src/test/java/todo/restfuse/todo/common
mkdir -p ./target-project/todo-api-mybatis3-multi/todo-api-mybatis3-multi-selenium/src/test/resources/testData

# cp test sources
# jpa
cp -p ./todo-rest/src/test/resources/testData/CreateErr400Test.txt ./target-project/todo-api-jpa/src/test/resources/testData
cp -p ./todo-rest/src/test/resources/testData/CreateTest.txt ./target-project/todo-api-jpa/src/test/resources/testData
cp -p ./todo-rest/src/test/java/jpa/todo/restfuse/todo/CreateErr400Test.java ./target-project/todo-api-jpa/src/test/java/todo/restfuse/todo
cp -p ./todo-rest/src/test/java/jpa/todo/restfuse/todo/CreateTest.java ./target-project/todo-api-jpa/src/test/java/todo/restfuse/todo
cp -p ./todo-rest/src/test/java/jpa/todo/restfuse/todo/DeleteOneTest.java ./target-project/todo-api-jpa/src/test/java/todo/restfuse/todo
cp -p ./todo-rest/src/test/java/jpa/todo/restfuse/todo/GetAllTest.java ./target-project/todo-api-jpa/src/test/java/todo/restfuse/todo
cp -p ./todo-rest/src/test/java/jpa/todo/restfuse/todo/GetOneErr404Test.java ./target-project/todo-api-jpa/src/test/java/todo/restfuse/todo
cp -p ./todo-rest/src/test/java/jpa/todo/restfuse/todo/GetOneTest.java ./target-project/todo-api-jpa/src/test/java/todo/restfuse/todo
cp -p ./todo-rest/src/test/java/jpa/todo/restfuse/todo/PutErr405Test.java ./target-project/todo-api-jpa/src/test/java/todo/restfuse/todo
cp -p ./todo-rest/src/test/java/jpa/todo/restfuse/todo/PutErr409Test.java ./target-project/todo-api-jpa/src/test/java/todo/restfuse/todo
cp -p ./todo-rest/src/test/java/jpa/todo/restfuse/todo/PutTest.java ./target-project/todo-api-jpa/src/test/java/todo/restfuse/todo
cp -p ./todo-rest/src/test/java/jpa/todo/restfuse/todo/common/AllTests.java ./target-project/todo-api-jpa/src/test/java/todo/restfuse/todo/common
cp -p ./todo-rest/src/test/java/jpa/todo/restfuse/todo/common/ErrDetails.java ./target-project/todo-api-jpa/src/test/java/todo/restfuse/todo/common
cp -p ./todo-rest/src/test/java/jpa/todo/restfuse/todo/common/ErrInfo.java ./target-project/todo-api-jpa/src/test/java/todo/restfuse/todo/common
cp -p ./todo-rest/src/test/java/jpa/todo/restfuse/todo/common/Todo.java ./target-project/todo-api-jpa/src/test/java/todo/restfuse/todo/common

# jpa-multi
cp -p ./todo-rest/src/test/resources/testData/CreateErr400Test.txt ./target-project/todo-api-jpa-multi/todo-api-jpa-multi-selenium/src/test/resources/testData
cp -p ./todo-rest/src/test/resources/testData/CreateTest.txt ./target-project/todo-api-jpa-multi/todo-api-jpa-multi-selenium/src/test/resources/testData
cp -p ./todo-rest/src/test/java/jpa-multi/todo/restfuse/todo/CreateErr400Test.java ./target-project/todo-api-jpa-multi/todo-api-jpa-multi-selenium/src/test/java/todo/restfuse/todo
cp -p ./todo-rest/src/test/java/jpa-multi/todo/restfuse/todo/CreateTest.java ./target-project/todo-api-jpa-multi/todo-api-jpa-multi-selenium/src/test/java/todo/restfuse/todo
cp -p ./todo-rest/src/test/java/jpa-multi/todo/restfuse/todo/DeleteOneTest.java ./target-project/todo-api-jpa-multi/todo-api-jpa-multi-selenium/src/test/java/todo/restfuse/todo
cp -p ./todo-rest/src/test/java/jpa-multi/todo/restfuse/todo/GetAllTest.java ./target-project/todo-api-jpa-multi/todo-api-jpa-multi-selenium/src/test/java/todo/restfuse/todo
cp -p ./todo-rest/src/test/java/jpa-multi/todo/restfuse/todo/GetOneErr404Test.java ./target-project/todo-api-jpa-multi/todo-api-jpa-multi-selenium/src/test/java/todo/restfuse/todo
cp -p ./todo-rest/src/test/java/jpa-multi/todo/restfuse/todo/GetOneTest.java ./target-project/todo-api-jpa-multi/todo-api-jpa-multi-selenium/src/test/java/todo/restfuse/todo
cp -p ./todo-rest/src/test/java/jpa-multi/todo/restfuse/todo/PutErr405Test.java ./target-project/todo-api-jpa-multi/todo-api-jpa-multi-selenium/src/test/java/todo/restfuse/todo
cp -p ./todo-rest/src/test/java/jpa-multi/todo/restfuse/todo/PutErr409Test.java ./target-project/todo-api-jpa-multi/todo-api-jpa-multi-selenium/src/test/java/todo/restfuse/todo
cp -p ./todo-rest/src/test/java/jpa-multi/todo/restfuse/todo/PutTest.java ./target-project/todo-api-jpa-multi/todo-api-jpa-multi-selenium/src/test/java/todo/restfuse/todo
cp -p ./todo-rest/src/test/java/jpa-multi/todo/restfuse/todo/common/AllTests.java ./target-project/todo-api-jpa-multi/todo-api-jpa-multi-selenium/src/test/java/todo/restfuse/todo/common
cp -p ./todo-rest/src/test/java/jpa-multi/todo/restfuse/todo/common/ErrDetails.java ./target-project/todo-api-jpa-multi/todo-api-jpa-multi-selenium/src/test/java/todo/restfuse/todo/common
cp -p ./todo-rest/src/test/java/jpa-multi/todo/restfuse/todo/common/ErrInfo.java ./target-project/todo-api-jpa-multi/todo-api-jpa-multi-selenium/src/test/java/todo/restfuse/todo/common
cp -p ./todo-rest/src/test/java/jpa-multi/todo/restfuse/todo/common/Todo.java ./target-project/todo-api-jpa-multi/todo-api-jpa-multi-selenium/src/test/java/todo/restfuse/todo/common

# mybatis3
cp -p ./todo-rest/src/test/resources/testData/CreateErr400Test.txt ./target-project/todo-api-mybatis3/src/test/resources/testData
cp -p ./todo-rest/src/test/resources/testData/CreateTest.txt ./target-project/todo-api-mybatis3/src/test/resources/testData
cp -p ./todo-rest/src/test/java/mybatis3/todo/restfuse/todo/CreateErr400Test.java ./target-project/todo-api-mybatis3/src/test/java/todo/restfuse/todo
cp -p ./todo-rest/src/test/java/mybatis3/todo/restfuse/todo/CreateTest.java ./target-project/todo-api-mybatis3/src/test/java/todo/restfuse/todo
cp -p ./todo-rest/src/test/java/mybatis3/todo/restfuse/todo/DeleteOneTest.java ./target-project/todo-api-mybatis3/src/test/java/todo/restfuse/todo
cp -p ./todo-rest/src/test/java/mybatis3/todo/restfuse/todo/GetAllTest.java ./target-project/todo-api-mybatis3/src/test/java/todo/restfuse/todo
cp -p ./todo-rest/src/test/java/mybatis3/todo/restfuse/todo/GetOneErr404Test.java ./target-project/todo-api-mybatis3/src/test/java/todo/restfuse/todo
cp -p ./todo-rest/src/test/java/mybatis3/todo/restfuse/todo/GetOneTest.java ./target-project/todo-api-mybatis3/src/test/java/todo/restfuse/todo
cp -p ./todo-rest/src/test/java/mybatis3/todo/restfuse/todo/PutErr405Test.java ./target-project/todo-api-mybatis3/src/test/java/todo/restfuse/todo
cp -p ./todo-rest/src/test/java/mybatis3/todo/restfuse/todo/PutErr409Test.java ./target-project/todo-api-mybatis3/src/test/java/todo/restfuse/todo
cp -p ./todo-rest/src/test/java/mybatis3/todo/restfuse/todo/PutTest.java ./target-project/todo-api-mybatis3/src/test/java/todo/restfuse/todo
cp -p ./todo-rest/src/test/java/mybatis3/todo/restfuse/todo/common/AllTests.java ./target-project/todo-api-mybatis3/src/test/java/todo/restfuse/todo/common
cp -p ./todo-rest/src/test/java/mybatis3/todo/restfuse/todo/common/ErrDetails.java ./target-project/todo-api-mybatis3/src/test/java/todo/restfuse/todo/common
cp -p ./todo-rest/src/test/java/mybatis3/todo/restfuse/todo/common/ErrInfo.java ./target-project/todo-api-mybatis3/src/test/java/todo/restfuse/todo/common
cp -p ./todo-rest/src/test/java/mybatis3/todo/restfuse/todo/common/Todo.java ./target-project/todo-api-mybatis3/src/test/java/todo/restfuse/todo/common

# mybatis3-multi
cp -p ./todo-rest/src/test/resources/testData/CreateErr400Test.txt ./target-project/todo-api-mybatis3-multi/todo-api-mybatis3-multi-selenium/src/test/resources/testData
cp -p ./todo-rest/src/test/resources/testData/CreateTest.txt ./target-project/todo-api-mybatis3-multi/todo-api-mybatis3-multi-selenium/src/test/resources/testData
cp -p ./todo-rest/src/test/java/mybatis3-multi/todo/restfuse/todo/CreateErr400Test.java ./target-project/todo-api-mybatis3-multi/todo-api-mybatis3-multi-selenium/src/test/java/todo/restfuse/todo/
cp -p ./todo-rest/src/test/java/mybatis3-multi/todo/restfuse/todo/CreateTest.java ./target-project/todo-api-mybatis3-multi/todo-api-mybatis3-multi-selenium/src/test/java/todo/restfuse/todo/
cp -p ./todo-rest/src/test/java/mybatis3-multi/todo/restfuse/todo/DeleteOneTest.java ./target-project/todo-api-mybatis3-multi/todo-api-mybatis3-multi-selenium/src/test/java/todo/restfuse/todo/
cp -p ./todo-rest/src/test/java/mybatis3-multi/todo/restfuse/todo/GetAllTest.java ./target-project/todo-api-mybatis3-multi/todo-api-mybatis3-multi-selenium/src/test/java/todo/restfuse/todo/
cp -p ./todo-rest/src/test/java/mybatis3-multi/todo/restfuse/todo/GetOneErr404Test.java ./target-project/todo-api-mybatis3-multi/todo-api-mybatis3-multi-selenium/src/test/java/todo/restfuse/todo/
cp -p ./todo-rest/src/test/java/mybatis3-multi/todo/restfuse/todo/GetOneTest.java ./target-project/todo-api-mybatis3-multi/todo-api-mybatis3-multi-selenium/src/test/java/todo/restfuse/todo/
cp -p ./todo-rest/src/test/java/mybatis3-multi/todo/restfuse/todo/PutErr405Test.java ./target-project/todo-api-mybatis3-multi/todo-api-mybatis3-multi-selenium/src/test/java/todo/restfuse/todo/
cp -p ./todo-rest/src/test/java/mybatis3-multi/todo/restfuse/todo/PutErr409Test.java ./target-project/todo-api-mybatis3-multi/todo-api-mybatis3-multi-selenium/src/test/java/todo/restfuse/todo/
cp -p ./todo-rest/src/test/java/mybatis3-multi/todo/restfuse/todo/PutTest.java ./target-project/todo-api-mybatis3-multi/todo-api-mybatis3-multi-selenium/src/test/java/todo/restfuse/todo/
cp -p ./todo-rest/src/test/java/mybatis3-multi/todo/restfuse/todo/common/AllTests.java ./target-project/todo-api-mybatis3-multi/todo-api-mybatis3-multi-selenium/src/test/java/todo/restfuse/todo/common
cp -p ./todo-rest/src/test/java/mybatis3-multi/todo/restfuse/todo/common/ErrDetails.java ./target-project/todo-api-mybatis3-multi/todo-api-mybatis3-multi-selenium/src/test/java/todo/restfuse/todo/common
cp -p ./todo-rest/src/test/java/mybatis3-multi/todo/restfuse/todo/common/ErrInfo.java ./target-project/todo-api-mybatis3-multi/todo-api-mybatis3-multi-selenium/src/test/java/todo/restfuse/todo/common
cp -p ./todo-rest/src/test/java/mybatis3-multi/todo/restfuse/todo/common/Todo.java ./target-project/todo-api-mybatis3-multi/todo-api-mybatis3-multi-selenium/src/test/java/todo/restfuse/todo/common

# modify pom
# jpa-multi
find ./target-project/todo-api-jpa-multi/todo-api-jpa-multi-selenium/pom.xml | xargs sed -i -e 's|</dependencies>|    <dependency>\n            <groupId>com.restfuse</groupId>\n            <artifactId>com.eclipsesource.restfuse</artifactId>\n            <version>${restfuse.version}</version>\n            <scope>test</scope>\n        </dependency>\n    </dependencies>|'
find ./target-project/todo-api-jpa-multi/todo-api-jpa-multi-selenium/pom.xml | xargs sed -i -e 's|</dependencies>|</dependencies>\n\n    <properties>\n        <restfuse.version>1.2.0</restfuse.version>\n    </properties>|'

# mybatis3-multi
find ./target-project/todo-api-mybatis3-multi/todo-api-mybatis3-multi-selenium/pom.xml | xargs sed -i -e 's|</dependencies>|    <dependency>\n            <groupId>com.restfuse</groupId>\n            <artifactId>com.eclipsesource.restfuse</artifactId>\n            <version>${restfuse.version}</version>\n            <scope>test</scope>\n        </dependency>\n    </dependencies>|'
find ./target-project/todo-api-mybatis3-multi/todo-api-mybatis3-multi-selenium/pom.xml | xargs sed -i -e 's|</dependencies>|</dependencies>\n\n    <properties>\n        <restfuse.version>1.2.0</restfuse.version>\n    </properties>|'

VER="${ARCHETYPE_VERSION:0:3}"
# VER 1.0.x or 5.0.x or 5.1.x
if test ${VER} = 1.0 ||  test ${VER} = 5.0 || test ${VER} = 5.1 ; then
# jpa
find ./target-project/todo-api-jpa/pom.xml | xargs sed -i -e 's|</dependencies>|    <dependency>\n            <groupId>com.restfuse</groupId>\n            <artifactId>com.eclipsesource.restfuse</artifactId>\n            <version>${restfuse.version}</version>\n            <scope>test</scope>\n        </dependency>\n    </dependencies>|'
find ./target-project/todo-api-jpa/pom.xml | xargs sed -i -e 's|</properties>|    <restfuse.version>1.2.0</restfuse.version>\n    </properties>|'
# mybatis3
find ./target-project/todo-api-mybatis3/pom.xml | xargs sed -i -e 's|</dependencies>|    <dependency>\n            <groupId>com.restfuse</groupId>\n            <artifactId>com.eclipsesource.restfuse</artifactId>\n            <version>${restfuse.version}</version>\n            <scope>test</scope>\n        </dependency>\n    </dependencies>|'
find ./target-project/todo-api-mybatis3/pom.xml | xargs sed -i -e 's|</properties>|    <restfuse.version>1.2.0</restfuse.version>\n    </properties>|'
# VER 5.2.x or laster
else
# jpa
find ./target-project/todo-api-jpa/pom.xml | xargs sed -i -e 's|</dependencies>|    <dependency>\n            <groupId>com.restfuse</groupId>\n            <artifactId>com.eclipsesource.restfuse</artifactId>\n            <version>${restfuse.version}</version>\n            <scope>test</scope>\n        </dependency>\n    </dependencies>|'
find ./target-project/todo-api-jpa/pom.xml | xargs sed -i -e 's|</selenium.version>|</selenium.version>\n        <restfuse.version>1.2.0</restfuse.version>|'
# mybatis3
find ./target-project/todo-api-mybatis3/pom.xml | xargs sed -i -e 's|</dependencies>|    <dependency>\n            <groupId>com.restfuse</groupId>\n            <artifactId>com.eclipsesource.restfuse</artifactId>\n            <version>${restfuse.version}</version>\n            <scope>test</scope>\n        </dependency>\n    </dependencies>|'
find ./target-project/todo-api-mybatis3/pom.xml | xargs sed -i -e 's|</selenium.version>|</selenium.version>\n        <restfuse.version>1.2.0</restfuse.version>|'
fi
