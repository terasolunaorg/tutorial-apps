#!/bin/bash
# Create Secure Login tutorial completed app.
# Required variables:
#   ${ARCHETYPE_ARTIFACT_ID}=Artifact ID of blank project's archetype.
#   ${ARCHETYPE_VERSION}=Version of blank project's archetype.
#   ${GROUP_ID}=Group ID of tutorial project. This project's GROUP_ID must be set 'com.example.securelogin'.
#   ${ARTIFACT_ID}=Artifact ID of tutorial project. This project's ARTIFACT_ID must be set 'secure-login'.
#   ${VERSION}=Version of tutorial project.
#   ${HOST_IP}=IP address for access to this web application. Defaults to 'localhost'.
#   ${APSRV_WEB_PORT}=Port number for access to this web application. Defaults to '8080'.
#   ${APSRV_H2DB_PORT}=Port number for access to H2DB. Defaults to '9212'.

echo "create version is ${VERSION}."

SCRIPT_DIR=`dirname "$0"`
TARGET_DIR=${SCRIPT_DIR}/../target-project

# create dir for work
mkdir "$TARGET_DIR"
pushd "$TARGET_DIR"

if test -z $HOST_IP; then
  export HOST_IP=localhost
fi

if test -z $APSRV_WEB_PORT; then
  export APSRV_WEB_PORT=8080
fi

if test -z $APSRV_H2DB_PORT; then
  export APSRV_H2DB_PORT=9212
fi

bash ../../common/scripts/generate-project.sh

bash ../scripts/copy-sources.sh

bash ../scripts/convert-secure-login-xml.sh `pwd`

bash ../scripts/convert-secure-login-infra.sh `pwd`

bash ../scripts/convert-secure-login-test.sh `pwd`

popd
