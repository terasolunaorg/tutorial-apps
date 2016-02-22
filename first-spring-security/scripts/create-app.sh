#!/bin/bash
# create ${VERSION} First-spring-security Tutorial App
# Please add a condition to change processing if version-diff of the script is occured.

echo "create version is ${VERSION}."

# create dir for complete app
mkdir target-project

# generate app
bash ./first-spring-security/scripts/generate-security-project.sh

bash ./todo/scripts/convert-common-infra.sh

bash ./todo/scripts/convert-common-css.sh

bash ./first-spring-security/scripts/convert-security-jsp.sh

bash ./first-spring-security/scripts/convert-security-xml.sh

# mv to target-project
mv ./first-spring-security-mybatis3 ./target-project
mv ./first-spring-security-mybatis3-multi ./target-project
