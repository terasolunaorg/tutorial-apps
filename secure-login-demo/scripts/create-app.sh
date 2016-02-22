#!/bin/bash
# create ${VERSION} Secure Login App
# Please add a condition to change processing if version-diff of the script is occured.

echo "create version is ${VERSION}."

bash ./secure-login-demo/scripts/generate-app-project.sh

bash ./secure-login-demo/scripts/convert-settings.sh
