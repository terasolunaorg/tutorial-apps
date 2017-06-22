#!/bin/bash
# Convert infra property definitions.
# Required variables:
#   ${ARTIFACT_ID}=Artifact ID of tutorial project.
#   ${HOST_IP}=IP address for access to this web application.
#   ${APSRV_WEB_PORT}=Port number for access to this web application.
# Parameters:
#   $1 : (Optional) Target project path to convert.

TARGET_DIR=$1
if test -n $TARGET_DIR; then
  pushd "$TARGET_DIR"
fi

# env/-infra.properties
ENV_INFRA_PROPERTIES=`find ./${ARTIFACT_ID}/${ARTIFACT_ID}-env -type f -name "${ARTIFACT_ID}-infra.properties"`
sed -i -e "s|app\.host = localhost|app.host = ${HOST_IP}|" "$ENV_INFRA_PROPERTIES"
sed -i -e "s|app\.port = 8080|app.port = ${APSRV_WEB_PORT}|" "$ENV_INFRA_PROPERTIES"
sed -i -e "s|app\.contextPath = /secure-login-web|app.contextPath = /${ARTIFACT_ID}-web|" "$ENV_INFRA_PROPERTIES"

if test -n $TARGET_DIR; then
  popd
fi
