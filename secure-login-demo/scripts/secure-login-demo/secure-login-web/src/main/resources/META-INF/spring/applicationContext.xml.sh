#!/bin/bash

TARGET_FILE_NAME=secure-login-demo/secure-login-web/src/main/resources/META-INF/spring/applicationContext.xml
SED_FILE_NAME=secure-login-demo/scripts/${TARGET_FILE_NAME}.sed

END_BEAN_DEFINITION_LINE=`sed -n '/<\/beans>/=' target-project/$TARGET_FILE_NAME`
sed -i -e "s/__END_BEAN_DEFINITION_LINE__/${END_BEAN_DEFINITION_LINE}/g" $SED_FILE_NAME

END_NAMESPACE_LINE=`sed -n '/xsi:schemaLocation/=' target-project/$TARGET_FILE_NAME`
sed -i -e "s/__END_NAMESPACE_LINE__/${END_NAMESPACE_LINE}/g" $SED_FILE_NAME

SCHEMALOCATION_LINE=`sed -n '/spring-beans.xsd/=' target-project/$TARGET_FILE_NAME`
sed -i -e "s/__SCHEMALOCATION_LINE__/${SCHEMALOCATION_LINE}/g" $SED_FILE_NAME

EXCEPTIONCODE_LINE=`sed -n '/InvalidTransactionTokenException/=' target-project/$TARGET_FILE_NAME`
sed -i -e "s/__EXCEPTIONCODE_LINE__/${EXCEPTIONCODE_LINE}/g" $SED_FILE_NAME

sed -i -f $SED_FILE_NAME target-project/$TARGET_FILE_NAME
