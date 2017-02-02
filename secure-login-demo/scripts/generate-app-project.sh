#!/bin/bash

#${ARCHETYPE_VERSION}=terasoluna-gfw-blank-archetype-version
#${VERSION}=tutorial pj version

mkdir target-project

# mybatis app multi
case "${ARCHETYPE_VERSION:0:5}" in
  5.2* | 5.0* )
    bash ./secure-login-demo/scripts/generate-project-from-nexus.sh;;
  * )
    if test `echo ${ARCHETYPE_VERSION} | tail -c8 ` = "RELEASE"; then
      bash ./secure-login-demo/scripts/generate-project-from-maven-central.sh
    else
      bash ./secure-login-demo/scripts/generate-project-from-nexus.sh
    fi;;
esac

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
cp -p ./common-test/resources/logback.xml ./target-project/secure-login-demo/secure-login-selenium/src/test/resources