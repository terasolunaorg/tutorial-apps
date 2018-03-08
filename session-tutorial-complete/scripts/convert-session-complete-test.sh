#!/bin/bash
# Convert selenium test resource(s) on blank project.
# Required variables:
#   ${ARTIFACT_ID}=Artifact ID of tutorial project.
# Parameters:
#   $1 : (Optional) Target project path to convert.

APPLICATION_DIR=$1
if test -n $APPLICATION_DIR; then
  pushd "$APPLICATION_DIR"
fi

# selenium.properties
find ./ -type f -name 'selenium.properties' | xargs sed -i -e "s|selenium.contextName=|selenium.contextName=${ARTIFACT_ID}-web|"

if test -n $APPLICATION_DIR; then
  popd
fi
