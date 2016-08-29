#!/bin/bash

TARGET_FILE_NAME=secure-login-demo/secure-login-domain/src/main/resources/META-INF/mybatis/mybatis-config.xml
SED_FILE_NAME=secure-login-demo/scripts/${TARGET_FILE_NAME}.sed

END_TYPEALIASES_LINE=`sed -n '/<\/typeAliases>/=' target-project/$TARGET_FILE_NAME`
sed -i -e "s/__END_TYPEALIASES_LINE__/${END_TYPEALIASES_LINE}/g" $SED_FILE_NAME

END_TYPEHANDLERS_LINE=`sed -n '/<\/typeHandlers>/=' target-project/$TARGET_FILE_NAME`
sed -i -e "s/__END_TYPEHANDLERS_LINE__/${END_TYPEHANDLERS_LINE}/g" $SED_FILE_NAME

sed -i -f $SED_FILE_NAME target-project/$TARGET_FILE_NAME
