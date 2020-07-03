#!/bin/bash
# Create Secure Login tutorial completed app.
# Required variables:
#   ${ARCHETYPE_ARTIFACT_ID}=Artifact ID of blank project's archetype.
#   ${ARCHETYPE_VERSION}=Version of blank project's archetype. If not specified, the default value is set.
#   ${ARTIFACT_ID}=Artifact ID of tutorial project. If not specified, the default value is set.
#   ${VERSION}=Version of tutorial project. If not specified, the default value is set.
#   ${HOST_IP}=IP address for access to this web application. Defaults to 'localhost'.
#   ${APSRV_WEB_PORT}=Port number for access to this web application. Defaults to '8080'.
#   ${APSRV_H2DB_PORT}=Port number for access to H2DB. Defaults to '9212'.

case "$ARCHETYPE_ARTIFACT_ID" in
    terasoluna-gfw-multi-web-blank-mybatis3-archetype )
        export ARTIFACT_ID=${ARTIFACT_ID:=secure-login}
        ;;
    * )
        echo "[ERROR] ARCHETYPE_ARTIFACT_ID [${ARCHETYPE_ARTIFACT_ID}] is not available in this tutorial."
        exit 1
        ;;
esac

export ARCHETYPE_VERSION=${ARCHETYPE_VERSION:=5.6.1-SNAPSHOT}
export GROUP_ID=com.example.securelogin
export VERSION=${VERSION:=1.0.0-SNAPSHOT}
export HOST_IP=${HOST_IP:=localhost}
export APSRV_WEB_PORT=${APSRV_WEB_PORT:=8080}
export APSRV_H2DB_PORT=${APSRV_H2DB_PORT:=9212}

echo "create groupId is ${GROUP_ID}."
echo "create artifactId is ${ARTIFACT_ID}."
echo "create version is ${VERSION}."
echo "IP address for this web application is ${HOST_IP}"
echo "port number for this web application is ${APSRV_WEB_PORT}"
echo "port number for H2DB is ${APSRV_H2DB_PORT}"

SCRIPT_DIR=`dirname "$0"`
TARGET_DIR=${SCRIPT_DIR}/../target-project

# create dir for work
rm -rf "${TARGET_DIR}/${ARTIFACT_ID}"
mkdir "$TARGET_DIR"
pushd "$TARGET_DIR"

bash ../../common/scripts/generate-project.sh

bash ../scripts/copy-sources.sh

bash ../../common/scripts/convert-common-test.sh `pwd`

bash ../scripts/convert-secure-login-xml.sh `pwd`

bash ../scripts/convert-secure-login-jsp.sh `pwd`

bash ../scripts/convert-secure-login-infra.sh `pwd`

bash ../scripts/convert-secure-login-properties.sh `pwd`

bash ../scripts/convert-secure-login-css.sh `pwd`

bash ../scripts/convert-secure-login-test.sh `pwd`

popd
