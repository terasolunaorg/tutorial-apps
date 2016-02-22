#!/bin/bash
# create ${VERSION} session tutorial init App
# Please add a condition to change processing if version-diff of the script is occured.

echo "create version is ${VERSION}."

bash ./session-init/scripts/generate-session-init-project.sh

bash ./session-init/scripts/convert-session-init-css.sh

bash ./session-init/scripts/convert-session-init-jsp.sh

bash ./session-init/scripts/convert-session-init-properties.sh

bash ./session-init/scripts/convert-session-init-xml.sh