#!/bin/bash
# create ${VERSION} session tutorial complete App
# Please add a condition to change processing if version-diff of the script is occured.

echo "create version is ${VERSION}."

bash ./session-complete/scripts/generate-session-complete-project.sh

bash ./session-complete/scripts/convert-session-complete-css.sh

bash ./session-complete/scripts/convert-session-complete-jsp.sh

bash ./session-complete/scripts/convert-session-complete-properties.sh

bash ./session-complete/scripts/convert-session-complete-xml.sh

bash ./session-complete/scripts/convert-session-complete-java.sh