#!/bin/bash

TARGET_FILE_NAME=secure-login-demo/secure-login-web/src/main/webapp/WEB-INF/web.xml
SED_FILE_NAME=secure-login-demo/scripts/${TARGET_FILE_NAME}.sed

END_WEB_APP_DEFINITION_LINE=`sed -n '/<\/web-app>/=' target-project/$TARGET_FILE_NAME`
sed -i -e "s/__END_WEB_APP_DEFINITION_LINE__/${END_WEB_APP_DEFINITION_LINE}/g" $SED_FILE_NAME

sed -i -f $SED_FILE_NAME target-project/$TARGET_FILE_NAME
