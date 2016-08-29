#!/bin/bash

TARGET_FILE_NAME=secure-login-demo/pom.xml
SED_FILE_NAME=secure-login-demo/scripts/${TARGET_FILE_NAME}.sed

JAVA_VERSION_LINE=`sed -n '/<java-version>/=' target-project/$TARGET_FILE_NAME`
sed -i -e "s/__JAVA_VERSION_LINE__/${JAVA_VERSION_LINE}/g" $SED_FILE_NAME

sed -i -f $SED_FILE_NAME target-project/$TARGET_FILE_NAME
