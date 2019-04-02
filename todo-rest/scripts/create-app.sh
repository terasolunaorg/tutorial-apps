#!/bin/bash
# Create Todo(REST) tutorial completed app.
# Required variables:
#   ${ARCHETYPE_ARTIFACT_ID}=Artifact ID of blank project's archetype.
#   ${ARCHETYPE_VERSION}=Version of blank project's archetype. If not specified, the default value is set.
#   ${ARTIFACT_ID}=Artifact ID of tutorial project. If not specified, the default value is set.
#   ${VERSION}=Version of tutorial project. If not specified, the default value is set.

case "$ARCHETYPE_ARTIFACT_ID" in
    terasoluna-gfw-web-blank-archetype )
        export ARTIFACT_ID=${ARTIFACT_ID:=todo-api}
        ;;
    terasoluna-gfw-web-blank-mybatis3-archetype )
        export ARTIFACT_ID=${ARTIFACT_ID:=todo-api-mybatis3}
        ;;
    terasoluna-gfw-multi-web-blank-mybatis3-archetype )
        export ARTIFACT_ID=${ARTIFACT_ID:=todo-api-mybatis3-multi}
        ;;
    terasoluna-gfw-web-blank-jpa-archetype )
        export ARTIFACT_ID=${ARTIFACT_ID:=todo-api-jpa}
        ;;
    terasoluna-gfw-multi-web-blank-jpa-archetype )
        export ARTIFACT_ID=${ARTIFACT_ID:=todo-api-jpa-multi}
        ;;
    * )
        echo "[ERROR] ARCHETYPE_ARTIFACT_ID [${ARCHETYPE_ARTIFACT_ID}] is not available in this tutorial."
        exit 1
        ;;
esac

export ARCHETYPE_VERSION=${ARCHETYPE_VERSION:=5.5.2-SNAPSHOT}
export GROUP_ID=com.example.todo
export VERSION=${VERSION:=1.0.0-SNAPSHOT}

echo "create groupId is ${GROUP_ID}."
echo "create artifactId is ${ARTIFACT_ID}."
echo "create version is ${VERSION}."

SCRIPT_DIR=`dirname "$0"`
TARGET_DIR=${SCRIPT_DIR}/../target-project

# create dir for work
rm -rf "${TARGET_DIR}/${ARTIFACT_ID}"
mkdir "$TARGET_DIR"
pushd "$TARGET_DIR"

bash ../../common/scripts/generate-project.sh

bash ../scripts/copy-sources.sh

bash ../../common/scripts/convert-common-test.sh `pwd`

bash ../scripts/convert-rest-test.sh `pwd`

case "$ARCHETYPE_ARTIFACT_ID" in
    *mybatis3* | *jpa* ) bash ../../todo/scripts/convert-todo-infra.sh `pwd` ;;
    * ) ;;
esac

bash ../../todo/scripts/convert-todo-css.sh `pwd`

bash ../scripts/convert-rest-java.sh `pwd`

bash ../scripts/convert-rest-msg.sh `pwd`

bash ../scripts/convert-rest-xml.sh `pwd`

popd
