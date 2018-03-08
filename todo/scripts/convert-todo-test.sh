#!/bin/bash
# Convert selenium test resource(s) on blank project.
# Required variables:
#   ${ARCHETYPE_ARTIFACT_ID}=Artifact ID of blank project's archetype.
#   ${ARTIFACT_ID}=Artifact ID of tutorial project.
# Parameters:
#   $1 : (Optional) Target project path to convert.

APPLICATION_DIR=$1
if test -n $APPLICATION_DIR; then
  pushd "$APPLICATION_DIR"
fi

# selenium.properties
if test `echo $ARCHETYPE_ARTIFACT_ID | grep multi`;then
  find ./ -type f -name 'selenium.properties' | xargs sed -i -e "s|selenium.contextName=|selenium.contextName=${ARTIFACT_ID}-web|"
else
  find ./ -type f -name 'selenium.properties' | xargs sed -i -e "s|selenium.contextName=|selenium.contextName=${ARTIFACT_ID}|"
fi

if test -n $APPLICATION_DIR; then
  popd
fi
