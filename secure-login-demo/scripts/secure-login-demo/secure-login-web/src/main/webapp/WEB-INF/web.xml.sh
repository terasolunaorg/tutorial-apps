#!/bin/bash

TARGET_FILE_NAME=secure-login-demo/secure-login-web/src/main/webapp/WEB-INF/web.xml
SED_FILE_NAME=secure-login-demo/scripts/${TARGET_FILE_NAME}.sed

END_SERVLET_DEFINITION_LINE=`sed -n '/<load-on-startup>1</=' target-project/$TARGET_FILE_NAME`
sed -i -e "s/__END_SERVLET_DEFINITION_LINE__/${END_SERVLET_DEFINITION_LINE}/g" $SED_FILE_NAME

END_WEB_APP_DEFINITION_LINE=`sed -n '/<\/web-app>/=' target-project/$TARGET_FILE_NAME`
sed -i -e "s/__END_WEB_APP_DEFINITION_LINE__/${END_WEB_APP_DEFINITION_LINE}/g" $SED_FILE_NAME

FILTERCHAIN_LINE=`sed -n '/<filter-name>springSecurityFilterChain/=' target-project/$TARGET_FILE_NAME | head -n 1`
sed -i -e "s/__FILTERCHAIN_LINE__/${FILTERCHAIN_LINE}/g" $SED_FILE_NAME

END_ERRORPAGE_LINE=`sed -n '/<\/error-page>/=' target-project/$TARGET_FILE_NAME | tail -n 1`
sed -i -e "s/__END_ERRORPAGE_LINE__/${END_ERRORPAGE_LINE}/g" $SED_FILE_NAME

sed -i -f $SED_FILE_NAME target-project/$TARGET_FILE_NAME
