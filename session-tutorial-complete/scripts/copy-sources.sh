#!/bin/bash
# Copy tutorial source files to blank project.
# Required variables:
#   ${ARTIFACT_ID}=Artifact ID of tutorial project.

SCRIPT_DIR=`dirname "$0"`
pushd "${SCRIPT_DIR}/../target-project"

# multi project
###################  init  ###################
# web project
cp -p -r ../../session-tutorial-init/src/session-tutorial-init-web/* ./${ARTIFACT_ID}/${ARTIFACT_ID}-web
# domain project
cp -p -r ../../session-tutorial-init/src/session-tutorial-init-domain/* ./${ARTIFACT_ID}/${ARTIFACT_ID}-domain
# env project
cp -p -r ../../session-tutorial-init/src/session-tutorial-init-env/* ./${ARTIFACT_ID}/${ARTIFACT_ID}-env

###################  tutorial flow  ###################
# web project
cp -p -r ../src/session-tutorial-complete-web/* ./${ARTIFACT_ID}/${ARTIFACT_ID}-web
# selenium project
cp -p -r ../src/session-tutorial-complete-selenium/* ./${ARTIFACT_ID}/${ARTIFACT_ID}-selenium
cp -p -r ../../common/src/test/java/selenium ./${ARTIFACT_ID}/${ARTIFACT_ID}-selenium/src/test/java/com/example/session
cp -p -r ../../common/src/test/resources ./${ARTIFACT_ID}/${ARTIFACT_ID}-selenium/src/test

popd
