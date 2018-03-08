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
  find ./ -type f -name 'selenium.properties' | xargs sed -i -e "s|selenium.contextName=|selenium.contextName=${ARTIFACT_ID}-web|"
  find ./ -type f -name 'FirstSpringSecurityTest.java' | xargs sed -i -e 's|assertThat(driver.findElement(By.tagName("h1")).getText()|assertThat(driver.findElement(By.id("title")).getText()|'
else
  find ./ -type f -name 'selenium.properties' | xargs sed -i -e "s|selenium.contextName=|selenium.contextName=${ARTIFACT_ID}|"
fi

find ./ -type f -name 'FunctionTestSupport.java' | xargs sed -i -e 's|return applicationContextUrl + "/" + simplePackageName + "/";|return applicationContextUrl + "/login/loginForm";|'
find ./ -name "*.java" -o -name "*.xml" | xargs sed -i -e 's|todo.selenium|com.example.security.selenium|'

if test -n $TARGET_DIR; then
  popd
fi
