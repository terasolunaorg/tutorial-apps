#!/bin/bash
# create ${VERSION} TODO-rest Tutorial App
# Please add a condition to change processing if version-diff of the script is occured.

echo "create version is ${VERSION}."

# create dir for complete app
mkdir target-project

# generate app
bash ./todo-rest/scripts/generate-rest-project.sh

bash ./todo/scripts/convert-common-infra.sh

bash ./todo/scripts/convert-common-css.sh

bash ./todo-rest/scripts/convert-rest-msg.sh

bash ./todo-rest/scripts/convert-rest-xml.sh

# mv to target-project
mv ./todo-jpa-api ./target-project
mv ./todo-mybatis3-api ./target-project
mv ./todo-jpa-api-multi ./target-project
mv ./todo-mybatis3-api-multi ./target-project
