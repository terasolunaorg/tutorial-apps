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
  cp -p -r ../../todo/src/main/java/com/example/todo/app ./${ARTIFACT_ID}/${ARTIFACT_ID}-web/src/main/java/com/example/todo
  cp -p -r ../../todo/src/main/webapp ./${ARTIFACT_ID}/${ARTIFACT_ID}-web/src/main
  cp -p -r ../src/main/java/com/example/todo/api ./${ARTIFACT_ID}/${ARTIFACT_ID}-web/src/main/java/com/example/todo
  cp -p -r ../src/main/resources ./${ARTIFACT_ID}/${ARTIFACT_ID}-web/src/main
  # domain project
  cp -p -r ../src/main/java/com/example/todo/domain ./${ARTIFACT_ID}/${ARTIFACT_ID}-domain/src/main/java/com/example/todo
  # selenium project
  cp -p -r ../src/test ./${ARTIFACT_ID}/${ARTIFACT_ID}-selenium/src
  cp -p -r ../../common/src/test ./${ARTIFACT_ID}/${ARTIFACT_ID}-selenium/src
  
  case "${ARCHETYPE_ARTIFACT_ID}" in
    *mybatis3* )
      cp -p -r ../../todo/src-mybatis3/main/java/com/example/todo/domain/model ./${ARTIFACT_ID}/${ARTIFACT_ID}-domain/src/main/java/com/example/todo/domain
      cp -p -r ../../todo/src-mybatis3/main/java/com/example/todo/domain/repository ./${ARTIFACT_ID}/${ARTIFACT_ID}-domain/src/main/java/com/example/todo/domain
      cp -p -r ../../todo/src-mybatis3/main/resources/com/example/todo ./${ARTIFACT_ID}/${ARTIFACT_ID}-domain/src/main/resources/com/example
      cp -p -r ../src-mybatis3/main/java/com/example/todo/domain ./${ARTIFACT_ID}/${ARTIFACT_ID}-domain/src/main/java/com/example/todo
      ;;
    *jpa* )
      cp -p -r ../../todo/src-jpa/main/java/com/example/todo/domain/model ./${ARTIFACT_ID}/${ARTIFACT_ID}-domain/src/main/java/com/example/todo/domain
      cp -p -r ../../todo/src-jpa/main/java/com/example/todo/domain/repository ./${ARTIFACT_ID}/${ARTIFACT_ID}-domain/src/main/java/com/example/todo/domain
      cp -p -r ../src-jpa/main/java/com/example/todo/domain ./${ARTIFACT_ID}/${ARTIFACT_ID}-domain/src/main/java/com/example/todo
      ;;
    * )
      cp -p -r ../../todo/src-plain/main/java/com/example/todo/domain/model ./${ARTIFACT_ID}/${ARTIFACT_ID}-domain/src/main/java/com/example/todo/domain
      cp -p -r ../../todo/src-plain/main/java/com/example/todo/domain/repository ./${ARTIFACT_ID}/${ARTIFACT_ID}-domain/src/main/java/com/example/todo/domain
      cp -p -r ../src-plain/main/java/com/example/todo/domain ./${ARTIFACT_ID}/${ARTIFACT_ID}-domain/src/main/java/com/example/todo
      ;;
  esac
else
  # single project
  cp -p -r ../../todo/src/main/java/com/example/todo/app ./${ARTIFACT_ID}/src/main/java/com/example/todo
  cp -p -r ../../todo/src/main/webapp ./${ARTIFACT_ID}/src/main
  cp -p -r ../src/* ./${ARTIFACT_ID}/src
  cp -p -r ../../common/src/test ./${ARTIFACT_ID}/src
  
  case "${ARCHETYPE_ARTIFACT_ID}" in
    *mybatis3* )
      cp -p -r ../../todo/src-mybatis3/main/java/com/example/todo/domain/model ./${ARTIFACT_ID}/src/main/java/com/example/todo/domain
      cp -p -r ../../todo/src-mybatis3/main/java/com/example/todo/domain/repository ./${ARTIFACT_ID}/src/main/java/com/example/todo/domain
      cp -p -r ../../todo/src-mybatis3/main/resources/com/example/todo ./${ARTIFACT_ID}/src/main/resources/com/example
      cp -p -r ../src-mybatis3/* ./${ARTIFACT_ID}/src
      ;;
    *jpa* )
      cp -p -r ../../todo/src-jpa/main/java/com/example/todo/domain/model ./${ARTIFACT_ID}/src/main/java/com/example/todo/domain
      cp -p -r ../../todo/src-jpa/main/java/com/example/todo/domain/repository ./${ARTIFACT_ID}/src/main/java/com/example/todo/domain
      cp -p -r ../src-jpa/* ./${ARTIFACT_ID}/src
      ;;
    * )
      cp -p -r ../../todo/src-plain/main/java/com/example/todo/domain/model ./${ARTIFACT_ID}/src/main/java/com/example/todo/domain
      cp -p -r ../../todo/src-plain/main/java/com/example/todo/domain/repository ./${ARTIFACT_ID}/src/main/java/com/example/todo/domain
      cp -p -r ../src-plain/* ./${ARTIFACT_ID}/src
      ;;
  esac
fi

popd
