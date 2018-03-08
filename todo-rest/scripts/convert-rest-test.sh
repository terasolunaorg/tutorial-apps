#!/bin/bash
# Convert selenium test resource(s) on blank project.
# Required variables:
#   ${ARCHETYPE_ARTIFACT_ID}=Artifact ID of blank project's archetype.
#   ${ARTIFACT_ID}=Artifact ID of tutorial project.
# Parameters:
#   $1 : (Optional) Target project path to convert.

TARGET_DIR=$1
if test -n $TARGET_DIR; then
  pushd "${TARGET_DIR}/${ARTIFACT_ID}"
fi

if test `echo $ARCHETYPE_ARTIFACT_ID | grep multi`;then
  # selenium/pom.xml
  find ./${ARTIFACT_ID}-selenium/pom.xml | xargs sed -i -e 's|<dependencies>|<dependencies>\n        <dependency>\n            <groupId>com.jayway.restassured</groupId>\n            <artifactId>rest-assured</artifactId>\n            <version>${com.jayway.restassured.version}</version>\n            <scope>test</scope>\n        </dependency>|'
  find ./${ARTIFACT_ID}-selenium/pom.xml | xargs sed -i -e 's|</dependencies>|</dependencies>\n\n    <properties>\n        <com.jayway.restassured.version>2.7.0</com.jayway.restassured.version>\n    </properties>|'
  # selenium.properties
  find ./ -type f -name 'selenium.properties' | xargs sed -i -e "s|selenium.contextName=|selenium.contextName=${ARTIFACT_ID}-web|"
else
  # pom.xml
  find ./pom.xml | xargs sed -i -e 's|<dependencies>|<dependencies>\n        <dependency>\n            <groupId>com.jayway.restassured</groupId>\n            <artifactId>rest-assured</artifactId>\n            <version>${com.jayway.restassured.version}</version>\n            <scope>test</scope>\n        </dependency>|'
  find ./pom.xml | xargs sed -i -e 's|</properties>|    <com.jayway.restassured.version>2.7.0</com.jayway.restassured.version>\n    </properties>|'
  # selenium.properties
  find ./ -type f -name 'selenium.properties' | xargs sed -i -e "s|selenium.contextName=|selenium.contextName=${ARTIFACT_ID}|"
fi

if test -n $TARGET_DIR; then
  popd
fi
