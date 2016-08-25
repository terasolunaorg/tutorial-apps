#!/bin/bash

TARGET_FILE_NAME=secure-login-demo/secure-login-domain/src/main/resources/META-INF/spring/secure-login-domain.xml
SED_FILE_NAME=secure-login-demo/scripts/${TARGET_FILE_NAME}.sed

END_BEAN_DEFINITION_LINE=`sed -n '/<\/beans>/=' target-project/$TARGET_FILE_NAME`
sed -i -e "s/__END_BEAN_DEFINITION_LINE__/${END_BEAN_DEFINITION_LINE}/g" $SED_FILE_NAME

sed -i -f $SED_FILE_NAME target-project/$TARGET_FILE_NAME
