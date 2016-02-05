#!/bin/bash

#${ARCHETYPE_VERSION}=terasoluna-gfw-blank-archetype-version
#${VERSION}=tutorial pj version


# mybatis app singleの方を作成
mvn archetype:generate -B \
 -DarchetypeCatalog=http://repo.terasoluna.org/nexus/content/repositories/terasoluna-gfw-releases \
 -DarchetypeGroupId=org.terasoluna.gfw.blank \
 -DarchetypeArtifactId=terasoluna-gfw-web-blank-mybatis3-archetype \
 -DarchetypeVersion=${ARCHETYPE_VERSION} \
 -DgroupId=com.example.security \
 -DartifactId=first-spring-security-mybatis3 \
 -Dversion=${VERSION}

# mybatis app multi
mvn archetype:generate -B \
 -DarchetypeCatalog=http://repo.terasoluna.org/nexus/content/repositories/terasoluna-gfw-releases \
 -DarchetypeGroupId=org.terasoluna.gfw.blank \
 -DarchetypeArtifactId=terasoluna-gfw-multi-web-blank-mybatis3-archetype \
 -DarchetypeVersion=${ARCHETYPE_VERSION} \
 -DgroupId=com.example.security \
 -DartifactId=first-spring-security-mybatis3-multi \
 -Dversion=${VERSION}


# チュートリアルで作成するディレクトリを事前にmake
mkdir -p ./first-spring-security-mybatis3/src/main/java/com/example/security/domain/model
mkdir -p ./first-spring-security-mybatis3-multi/first-spring-security-mybatis3-multi-domain/src/main/java/com/example/security/domain/model

mkdir -p ./first-spring-security-mybatis3/src/main/java/com/example/security/domain/repository/account/
mkdir -p ./first-spring-security-mybatis3-multi/first-spring-security-mybatis3-multi-domain/src/main/java/com/example/security/domain/repository/account/

mkdir -p ./first-spring-security-mybatis3/src/main/resources/com/example/security/domain/repository/account/
mkdir -p ./first-spring-security-mybatis3-multi/first-spring-security-mybatis3-multi-domain/src/main/resources/com/example/security/domain/repository/account/

mkdir -p ./first-spring-security-mybatis3/src/main/java/com/example/security/domain/service/account/
mkdir -p ./first-spring-security-mybatis3-multi/first-spring-security-mybatis3-multi-domain/src/main/java/com/example/security/domain/service/account/

mkdir -p ./first-spring-security-mybatis3/src/main/java/com/example/security/domain/service/userdetails/
mkdir -p ./first-spring-security-mybatis3-multi/first-spring-security-mybatis3-multi-domain/src/main/java/com/example/security/domain/service/userdetails/

mkdir -p ./first-spring-security-mybatis3/src/main/java/com/example/security/app/account/
mkdir -p ./first-spring-security-mybatis3-multi/first-spring-security-mybatis3-multi-web/src/main/java/com/example/security/app/account/

mkdir -p ./first-spring-security-mybatis3/src/main/resources/database/
mkdir -p ./first-spring-security-mybatis3-multi/first-spring-security-mybatis3-multi-env/src/main/resources/database/

mkdir -p ./first-spring-security-mybatis3/src/main/webapp/WEB-INF/views/account/
mkdir -p ./first-spring-security-mybatis3-multi/first-spring-security-mybatis3-multi-web/src/main/webapp/WEB-INF/views/account/

# ソースの移動
cp -p ./todo/java/Account.java ./first-spring-security-mybatis3/src/main/java/com/example/security/domain/model
cp -p ./todo/java/Account.java ./first-spring-security-mybatis3-multi/first-spring-security-mybatis3-multi-domain/src/main/java/com/example/security/domain/model

cp -p ./todo/java/AccountRepository.java ./first-spring-security-mybatis3/src/main/java/com/example/security/domain/repository/account/
cp -p ./todo/java/AccountRepository.java ./first-spring-security-mybatis3-multi/first-spring-security-mybatis3-multi-domain/src/main/java/com/example/security/domain/repository/account/

cp -p ./todo/xml/AccountRepository.xml ./first-spring-security-mybatis3/src/main/resources/com/example/security/domain/repository/account/
cp -p ./todo/xml/AccountRepository.xml ./first-spring-security-mybatis3-multi/first-spring-security-mybatis3-multi-domain/src/main/resources/com/example/security/domain/repository/account/

cp -p ./todo/java/AccountSharedService.java ./first-spring-security-mybatis3/src/main/java/com/example/security/domain/service/account/
cp -p ./todo/java/AccountSharedService.java ./first-spring-security-mybatis3-multi/first-spring-security-mybatis3-multi-domain/src/main/java/com/example/security/domain/service/account/

cp -p ./todo/java/AccountSharedServiceImpl.java ./first-spring-security-mybatis3/src/main/java/com/example/security/domain/service/account/
cp -p ./todo/java/AccountSharedServiceImpl.java ./first-spring-security-mybatis3-multi/first-spring-security-mybatis3-multi-domain/src/main/java/com/example/security/domain/service/account/

cp -p ./todo/java/AccountController.java ./first-spring-security-mybatis3/src/main/java/com/example/security/app/account/
cp -p ./todo/java/AccountController.java ./first-spring-security-mybatis3-multi/first-spring-security-mybatis3-multi-web/src/main/java/com/example/security/app/account/

cp -p ./todo/java/SampleUserDetails.java ./first-spring-security-mybatis3/src/main/java/com/example/security/domain/service/userdetails/
cp -p ./todo/java/SampleUserDetails.java ./first-spring-security-mybatis3-multi/first-spring-security-mybatis3-multi-domain/src/main/java/com/example/security/domain/service/userdetails/

cp -p ./todo/java/SampleUserDetailsService.java ./first-spring-security-mybatis3/src/main/java/com/example/security/domain/service/userdetails/
cp -p ./todo/java/SampleUserDetailsService.java ./first-spring-security-mybatis3-multi/first-spring-security-mybatis3-multi-domain/src/main/java/com/example/security/domain/service/userdetails/

cp -p ./todo/sql/H2-*.sql ./first-spring-security-mybatis3/src/main/resources/database/
cp -p ./todo/sql/H2-*.sql ./first-spring-security-mybatis3-multi/first-spring-security-mybatis3-multi-env/src/main/resources/database/

cp -p ./todo/jsp/login.jsp ./first-spring-security-mybatis3/src/main/webapp/
cp -p ./todo/jsp/login.jsp ./first-spring-security-mybatis3-multi/first-spring-security-mybatis3-multi-web/src/main/webapp/

cp -p ./todo/jsp/view.jsp ./first-spring-security-mybatis3/src/main/webapp/WEB-INF/views/account/
cp -p ./todo/jsp/view.jsp ./first-spring-security-mybatis3-multi/first-spring-security-mybatis3-multi-web/src/main/webapp/WEB-INF/views/account/
