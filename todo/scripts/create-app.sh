#!/bin/bash
# create ${VERSION} TODO-Tutorial App
# Please add a condition to change processing if version-diff of the script is occured.

echo "create version is ${VERSION}."

# generate app
bash ./todo/scripts/generate-app-project.sh

bash ./todo/scripts/convert-common-infra.sh

bash ./todo/scripts/convert-common-css.sh
