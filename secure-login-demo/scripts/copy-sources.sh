#!/bin/bash
# Copy tutorial source files to blank project.
# Required variables:
#   ${ARTIFACT_ID}=Artifact ID of tutorial project.

SCRIPT_DIR=`dirname "$0"`
pushd "${SCRIPT_DIR}/../target-project"

# multi project
# remove files
rm ./${ARTIFACT_ID}/${ARTIFACT_ID}-web/src/main/java/com/example/securelogin/app/welcome/HelloController.java
rm -r ./${ARTIFACT_ID}/${ARTIFACT_ID}-domain/src/main/resources/com/example/securelogin/domain/repository/sample
rm -r ./${ARTIFACT_ID}/${ARTIFACT_ID}-selenium/src/test/java/com/example/securelogin/selenium/welcome
# web project
cp -p -r ../src/secure-login-web/* ./${ARTIFACT_ID}/${ARTIFACT_ID}-web
# domain project
cp -p -r ../src/secure-login-domain/* ./${ARTIFACT_ID}/${ARTIFACT_ID}-domain
# env project
cp -p -r ../src/secure-login-env/* ./${ARTIFACT_ID}/${ARTIFACT_ID}-env
# initdb project
cp -p -r ../src/secure-login-initdb/* ./${ARTIFACT_ID}/${ARTIFACT_ID}-initdb
# selenium project
cp -p -r ../src/secure-login-selenium/* ./${ARTIFACT_ID}/${ARTIFACT_ID}-selenium
cp -p -r ../../common/src/test/java ./${ARTIFACT_ID}/${ARTIFACT_ID}-selenium/src/test
cp -p -r ../../common/src/test/resources/logback.xml ./${ARTIFACT_ID}/${ARTIFACT_ID}-selenium/src/test/resources

popd
