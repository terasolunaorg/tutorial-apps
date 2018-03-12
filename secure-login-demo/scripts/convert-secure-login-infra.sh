#!/bin/bash
# Convert infra property definitions.
# Required variables:
#   ${ARTIFACT_ID}=Artifact ID of tutorial project.
#   ${HOST_IP}=IP address for access to this web application.
#   ${APSRV_WEB_PORT}=Port number for access to this web application.
# Parameters:
#   $1 : (Optional) Target project path to convert.

TARGET_DIR=$1
if test -n "${TARGET_DIR}/${ARTIFACT_ID}"; then
  pushd "${TARGET_DIR}/${ARTIFACT_ID}"
fi

# env/-infra.properties
ENV_INFRA_PROPERTIES=`find ./${ARTIFACT_ID}-env -type f -name "${ARTIFACT_ID}-infra.properties"`
for i in ${ENV_INFRA_PROPERTIES} ; do echo -e "
mail.host=localhost
mail.port=3025
mail.from=info@example.com
mail.subject=Password Reissue URL

app.host = ${HOST_IP}
app.port = ${APSRV_WEB_PORT}
app.contextPath = /${ARTIFACT_ID}-web
app.passwordReissueProtocol = http" >>$i ; done

if test -n "${TARGET_DIR}/${ARTIFACT_ID}"; then
  popd
fi
