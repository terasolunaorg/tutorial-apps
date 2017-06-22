#!/bin/bash
# Convert selenium test resource(s) on blank project.
# Required variables:
#   ${ARTIFACT_ID}=Artifact ID of tutorial project.
# Parameters:
#   $1 : (Optional) Target project path to convert.

TARGET_DIR=$1
if test -n $TARGET_DIR; then
  pushd "$TARGET_DIR"
fi

# selenium.properties
find ./ -type f -name 'selenium.properties' | xargs sed -i -e "s|selenium.contextName=|selenium.contextName=${ARTIFACT_ID}-web|"

find ./ -name "*.java" -o -name "*.xml"| xargs sed -i -e 's|todo.selenium|com.example.session.selenium|'

if test -n $TARGET_DIR; then
  popd
fi
