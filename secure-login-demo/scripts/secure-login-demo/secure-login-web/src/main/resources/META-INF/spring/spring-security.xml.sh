#!/bin/bash

TARGET_FILE_NAME=secure-login-demo/secure-login-web/src/main/resources/META-INF/spring/spring-security.xml
SED_FILE_NAME=secure-login-demo/scripts/${TARGET_FILE_NAME}.sed

END_BEAN_DEFINITION_LINE=`sed -n '/<\/beans>/=' target-project/$TARGET_FILE_NAME`
sed -i -e "s/__END_BEAN_DEFINITION_LINE__/${END_BEAN_DEFINITION_LINE}/g" $SED_FILE_NAME

END_SEC_HTTP_DEFINITION_LINE=`sed -n '/<\/sec:http>/=' target-project/$TARGET_FILE_NAME`
sed -i -e "s/__END_SEC_HTTP_DEFINITION_LINE__/${END_SEC_HTTP_DEFINITION_LINE}/g" $SED_FILE_NAME

FORM_LOGIN_LINE=`sed -n '/<sec:form-login/=' target-project/$TARGET_FILE_NAME`
sed -i -e "s/__FORM_LOGIN_LINE__/${FORM_LOGIN_LINE}/g" $SED_FILE_NAME

LOGOUT_LINE=`sed -n '/<sec:logout/=' target-project/$TARGET_FILE_NAME`
sed -i -e "s/__LOGOUT_LINE__/${LOGOUT_LINE}/g" $SED_FILE_NAME

AUTHENTICATION_MANAGER_LINE=`sed -n '/<sec:authentication-manager/=' target-project/$TARGET_FILE_NAME`
sed -i -e "s/__AUTHENTICATION_MANAGER_LINE__/${AUTHENTICATION_MANAGER_LINE}/g" $SED_FILE_NAME

sed -i -f $SED_FILE_NAME target-project/$TARGET_FILE_NAME
