#!/bin/bash
# create ${VERSION} TODO-Tutorial App
# Please add a condition to change processing if version-diff of the script is occured.

echo "create version is ${VERSION}."

# create dir for complete app
mkdir target-project

# generate app
bash ./todo/scripts/generate-app-project.sh

bash ./todo/scripts/convert-common-infra.sh

bash ./todo/scripts/convert-common-css.sh

#mv to target-project
mv ./todo-jpa ./target-project
mv ./todo-mybatis3 ./target-project
mv ./todo-jpa-multi ./target-project
mv ./todo-mybatis3-multi ./target-project
