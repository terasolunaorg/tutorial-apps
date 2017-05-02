#!/bin/bash
# Copy tutorial source files to blank project.
# Required variables:
#   ${ARCHETYPE_ARTIFACT_ID}=Artifact ID of blank project's archetype.
#   ${ARTIFACT_ID}=Artifact ID of tutorial project.

SCRIPT_DIR=`dirname "$0"`
pushd "${SCRIPT_DIR}/../target-project"

if test `echo $ARCHETYPE_ARTIFACT_ID | grep multi`;then
  # multi project
  # delete unuse (or conflict) test sources in blank project
  rm -rf ./${ARTIFACT_ID}/${ARTIFACT_ID}-selenium/src/test/java/org
  # web project
  cp -p -r ../src/main/java/com/example/security/app ./${ARTIFACT_ID}/${ARTIFACT_ID}-web/src/main/java/com/example/security
  cp -p -r ../src/main/webapp ./${ARTIFACT_ID}/${ARTIFACT_ID}-web/src/main
  # domain project
  cp -p -r ../src/main/java/com/example/security/domain ./${ARTIFACT_ID}/${ARTIFACT_ID}-domain/src/main/java/com/example/security
  cp -p -r ../src/main/resources/com ./${ARTIFACT_ID}/${ARTIFACT_ID}-domain/src/main/resources
  # env project
  cp -p -r ../src/main/resources/database ./${ARTIFACT_ID}/${ARTIFACT_ID}-env/src/main/resources
  # selenium project
  cp -p -r ../src/test ./${ARTIFACT_ID}/${ARTIFACT_ID}-selenium/src
  cp -p -r ../../common/src/test/java/selenium ./${ARTIFACT_ID}/${ARTIFACT_ID}-selenium/src/test/java/com/example/security
  cp -p -r ../../common/src/test/resources ./${ARTIFACT_ID}/${ARTIFACT_ID}-selenium/src/test
else
  # single project
  cp -p -r ../src/* ./${ARTIFACT_ID}/src
  cp -p -r ../../common/src/test/java/selenium ./${ARTIFACT_ID}/src/test/java/com/example/security
  cp -p -r ../../common/src/test/resources ./${ARTIFACT_ID}/src/test
fi

popd
