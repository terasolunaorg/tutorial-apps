#!/bin/bash
# create ${VERSION} First-spring-security Tutorial App
# Please add a condition to change processing if version-diff of the script is occured.

echo "create version is ${VERSION}."

# generate app
bash ./first-spring-security/scripts/generate-security-project.sh

bash ./todo/scripts/convert-common-infra.sh

bash ./todo/scripts/convert-common-css.sh

bash ./first-spring-security/scripts/convert-security-jsp.sh

bash ./first-spring-security/scripts/convert-security-xml.sh

bash ./first-spring-security/scripts/convert-security-test.sh