#!/bin/bash
# Copy tutorial source files to blank project.
# Required variables:
#   ${ARTIFACT_ID}=Artifact ID of tutorial project.

SCRIPT_DIR=`dirname "$0"`
pushd "${SCRIPT_DIR}/../target-project"

# multi project
# web project
cp -p -r ../src/session-tutorial-init-web/* ./${ARTIFACT_ID}/${ARTIFACT_ID}-web
# domain project
cp -p -r ../src/session-tutorial-init-domain/* ./${ARTIFACT_ID}/${ARTIFACT_ID}-domain
# env project
cp -p -r ../src/session-tutorial-init-env/* ./${ARTIFACT_ID}/${ARTIFACT_ID}-env
# selenium project
cp -p -r ../src/session-tutorial-init-selenium/* ./${ARTIFACT_ID}/${ARTIFACT_ID}-selenium
cp -p -r ../../common/src/test ./${ARTIFACT_ID}/${ARTIFACT_ID}-selenium/src

popd
