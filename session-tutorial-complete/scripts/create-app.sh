#!/bin/bash
# create ${VERSION} session tutorial complete App
# Please add a condition to change processing if version-diff of the script is occured.

echo "create version is ${VERSION}."

mkdir -p target-project

bash ./session-tutorial-complete/scripts/generate-session-complete-project.sh

bash ./session-tutorial-complete/scripts/convert-session-complete-css.sh

bash ./session-tutorial-complete/scripts/convert-session-complete-jsp.sh

bash ./session-tutorial-complete/scripts/convert-session-complete-properties.sh

bash ./session-tutorial-complete/scripts/convert-session-complete-xml.sh

bash ./session-tutorial-complete/scripts/convert-session-complete-java.sh