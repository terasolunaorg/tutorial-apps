#!/bin/bash

TARGET_FILE_NAME=secure-login-demo/secure-login-selenium/pom.xml
SED_FILE_NAME=secure-login-demo/scripts/${TARGET_FILE_NAME}.sed

END_DEPENDENCIES_LINE=`sed -n '/<\/dependencies>/=' target-project/$TARGET_FILE_NAME`
sed -i -e "s/__END_DEPENDENCIES_LINE__/${END_DEPENDENCIES_LINE}/g" $SED_FILE_NAME

sed -i -f $SED_FILE_NAME target-project/$TARGET_FILE_NAME
