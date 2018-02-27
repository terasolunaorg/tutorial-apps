#!/bin/bash
# Create Todo(REST) tutorial completed app.
# Required variables:
#   ${ARCHETYPE_ARTIFACT_ID}=Artifact ID of blank project's archetype.
#   ${ARCHETYPE_VERSION}=Version of blank project's archetype.
#   ${GROUP_ID}=Group ID of tutorial project. This project's GROUP_ID must be set 'com.example.todo'.
#   ${ARTIFACT_ID}=Artifact ID of tutorial project.
#   ${VERSION}=Version of tutorial project.

echo "create version is ${VERSION}."

SCRIPT_DIR=`dirname "$0"`
TARGET_DIR=${SCRIPT_DIR}/../target-project

# create dir for work
mkdir "$TARGET_DIR"
pushd "$TARGET_DIR"

bash ../../common/scripts/generate-project.sh

bash ../scripts/copy-sources.sh

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
