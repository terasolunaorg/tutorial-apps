#!/bin/bash
# Create Session tutorial initial app.
# Required variables:
#   ${ARCHETYPE_ARTIFACT_ID}=Artifact ID of blank project's archetype.
#   ${ARCHETYPE_VERSION}=Version of blank project's archetype.
#   ${VERSION}=Version of tutorial project.

case "$ARCHETYPE_ARTIFACT_ID" in
    terasoluna-gfw-multi-web-blank-mybatis3-archetype )
        export GROUP_ID=com.example.session
        export ARTIFACT_ID=session-tutorial-init
        ;;
    * )
        echo "You can not select the specified ARCHETYPE_ARTIFACT_ID."
        exit 1
        ;;
esac

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

bash ../scripts/convert-session-init-css.sh `pwd`

bash ../scripts/convert-session-init-jsp.sh `pwd`

bash ../scripts/convert-session-init-properties.sh `pwd`

bash ../scripts/convert-session-init-xml.sh `pwd`

bash ../scripts/convert-session-init-test.sh `pwd`

popd
