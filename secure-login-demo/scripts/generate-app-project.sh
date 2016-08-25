#!/bin/bash

#${ARCHETYPE_VERSION}=terasoluna-gfw-blank-archetype-version
#${VERSION}=tutorial pj version

mkdir target-project

# mybatis app multi
mvn archetype:generate -B \
 -DarchetypeCatalog=http://repo.terasoluna.org/nexus/content/repositories/terasoluna-gfw-releases \
 -DarchetypeGroupId=org.terasoluna.gfw.blank \
 -DarchetypeArtifactId=terasoluna-gfw-multi-web-blank-mybatis3-archetype \
 -DarchetypeVersion=${ARCHETYPE_VERSION} \
 -DgroupId=org.terasoluna.securelogin \
 -DartifactId=secure-login \
 -Dversion=${VERSION}

mv secure-login target-project/secure-login-demo

# remove files
rm ./target-project/secure-login-demo/secure-login-web/src/main/java/org/terasoluna/securelogin/app/welcome/HelloController.java
rm -r ./target-project/secure-login-demo/secure-login-domain/src/main/resources/org/terasoluna/securelogin/domain/repository/sample
rm -r ./target-project/secure-login-demo/secure-login-selenium/src/test/java/org/terasoluna/securelogin/selenium/welcome

# copy source files
find ./secure-login-demo/ -name "*.java" | xargs -i cp -pf --parent {} ./target-project/
find ./secure-login-demo/ -name "*.properties" | xargs -i cp -pf --parent {} ./target-project/
find ./secure-login-demo/ -name "*.sql" | xargs -i cp -pf --parent {} ./target-project/
find ./secure-login-demo/ -name "*.jsp" | xargs -i cp -pf --parent {} ./target-project/
find ./secure-login-demo/ -name "*.css" | xargs -i cp -pf --parent {} ./target-project/
find ./secure-login-demo/ -name "*.xml" | xargs -i cp -pf --parent {} ./target-project/
find ./secure-login-demo/ -name "*.png" | xargs -i cp -pf --parent {} ./target-project/
find ./secure-login-demo/ -name "*.txt" | xargs -i cp -pf --parent {} ./target-project/
