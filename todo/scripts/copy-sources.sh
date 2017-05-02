#!/bin/bash
# Copy tutorial source files to blank project.
# Required variables:
#   ${ARCHETYPE_ARTIFACT_ID}=Artifact ID of blank project's archetype.
#   ${ARTIFACT_ID}=Artifact ID of tutorial project.

SCRIPT_DIR=`dirname "$0"`
pushd "${SCRIPT_DIR}/../target-project"

if test `echo $ARCHETYPE_ARTIFACT_ID | grep multi`;then
  # multi project
  # web project
  cp -p -r ../src/main/java/todo/app ./${ARTIFACT_ID}/${ARTIFACT_ID}-web/src/main/java/todo
  cp -p -r ../src/main/webapp ./${ARTIFACT_ID}/${ARTIFACT_ID}-web/src/main
  # domain project
  cp -p -r ../src/main/java/todo/domain ./${ARTIFACT_ID}/${ARTIFACT_ID}-domain/src/main/java/todo
  # selenium project
  cp -p -r ../src/test ./${ARTIFACT_ID}/${ARTIFACT_ID}-selenium/src
  cp -p -r ../../common/src/test/java/selenium ./${ARTIFACT_ID}/${ARTIFACT_ID}-selenium/src/test/java/todo
  cp -p -r ../../common/src/test/resources ./${ARTIFACT_ID}/${ARTIFACT_ID}-selenium/src/test
  
  case "${ARCHETYPE_ARTIFACT_ID}" in
    *mybatis3* )
      cp -p -r ../src-mybatis3/main/java/todo/domain ./${ARTIFACT_ID}/${ARTIFACT_ID}-domain/src/main/java/todo
      cp -p -r ../src-mybatis3/main/resources/todo ./${ARTIFACT_ID}/${ARTIFACT_ID}-domain/src/main/resources
      ;;
    *jpa* )
      cp -p -r ../src-jpa/main/java/todo/domain ./${ARTIFACT_ID}/${ARTIFACT_ID}-domain/src/main/java/todo
      ;;
    * )
      cp -p -r ../src-plain/main/java/todo/domain ./${ARTIFACT_ID}/${ARTIFACT_ID}-domain/src/main/java/todo
      ;;
  esac
else
  # single project
  cp -p -r ../src/* ./${ARTIFACT_ID}/src
  cp -p -r ../../common/src/test/java/selenium ./${ARTIFACT_ID}/src/test/java/todo
  cp -p -r ../../common/src/test/resources ./${ARTIFACT_ID}/src/test
  
  case "${ARCHETYPE_ARTIFACT_ID}" in
    *mybatis3* )
      cp -p -r ../src-mybatis3/* ./${ARTIFACT_ID}/src
      ;;
    *jpa* )
      cp -p -r ../src-jpa/* ./${ARTIFACT_ID}/src
      ;;
    * )
      cp -p -r ../src-plain/* ./${ARTIFACT_ID}/src
      ;;
  esac
fi

popd
