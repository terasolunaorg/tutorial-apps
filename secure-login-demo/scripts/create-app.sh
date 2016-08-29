#!/bin/bash
# create ${VERSION} Secure Login App
# Please add a condition to change processing if version-diff of the script is occured.

echo "create version is ${VERSION}."

if [[ "$ARCHETYPE_VERSION" =~ ^5\.1\..*$ ]]; then
    rm -r ./secure-login-demo/scripts/secure-login-demo
    rm -r ./secure-login-demo/secure-login-domain
    rm -r ./secure-login-demo/secure-login-env
    rm -r ./secure-login-demo/secure-login-initdb
    rm -r ./secure-login-demo/secure-login-selenium
    rm -r ./secure-login-demo/secure-login-web
    cp -r ./secure-login-demo/scripts/old-resources/5.1.x/scripts/secure-login-demo ./secure-login-demo/scripts/
    cp -r ./secure-login-demo/scripts/old-resources/5.1.x/secure-login-domain ./secure-login-demo/
    cp -r ./secure-login-demo/scripts/old-resources/5.1.x/secure-login-env ./secure-login-demo/
    cp -r ./secure-login-demo/scripts/old-resources/5.1.x/secure-login-initdb ./secure-login-demo/
    cp -r ./secure-login-demo/scripts/old-resources/5.1.x/secure-login-selenium ./secure-login-demo/
    cp -r ./secure-login-demo/scripts/old-resources/5.1.x/secure-login-web ./secure-login-demo/
    cp ./secure-login-demo/scripts/old-resources/5.1.x/scripts/generate-app-project.sh ./secure-login-demo/scripts/
    cp ./secure-login-demo/scripts/old-resources/5.1.x/scripts/convert-settings.sh ./secure-login-demo/scripts/
fi

rm -r ./secure-login-demo/scripts/old-resources

bash ./secure-login-demo/scripts/generate-app-project.sh

bash ./secure-login-demo/scripts/convert-settings.sh
