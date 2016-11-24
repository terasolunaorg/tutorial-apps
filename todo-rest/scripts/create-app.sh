#!/bin/bash
# create ${VERSION} TODO-rest Tutorial App
# Please add a condition to change processing if version-diff of the script is occured.

echo "create version is ${VERSION}."

# settings of target tutorial
export TARGET_TUTORIAL=todo-rest

# generate app
bash ./todo-rest/scripts/generate-rest-project.sh

bash ./todo/scripts/convert-common-infra.sh

bash ./todo/scripts/convert-common-css.sh

bash ./todo-rest/scripts/convert-rest-msg.sh

bash ./todo-rest/scripts/convert-rest-xml.sh

bash ./todo-rest/scripts/generate-rest-test.sh
