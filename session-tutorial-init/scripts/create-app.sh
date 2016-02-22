#!/bin/bash
# create ${VERSION} session tutorial init App
# Please add a condition to change processing if version-diff of the script is occured.

echo "create version is ${VERSION}."

mkdir -p target-project

bash ./session-tutorial-init/scripts/generate-session-init-project.sh

bash ./session-tutorial-init/scripts/convert-session-init-css.sh

bash ./session-tutorial-init/scripts/convert-session-init-jsp.sh

bash ./session-tutorial-init/scripts/convert-session-init-properties.sh

bash ./session-tutorial-init/scripts/convert-session-init-xml.sh