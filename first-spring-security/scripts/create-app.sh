#!/bin/bash
# Create Spring Security tutorial completed app.
# Required variables:
#   ${ARCHETYPE_ARTIFACT_ID}=Artifact ID of blank project's archetype.
#   ${ARCHETYPE_VERSION}=Version of blank project's archetype.
#   ${GROUP_ID}=Group ID of tutorial project. This project's GROUP_ID must be set 'com.example.security'.
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

bash ../../common/scripts/convert-common-test.sh `pwd`

bash ../scripts/convert-security-jsp.sh `pwd`

bash ../scripts/convert-security-xml.sh `pwd`

bash ../scripts/convert-security-test.sh `pwd`

popd
