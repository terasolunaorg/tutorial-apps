#!/bin/bash
# Convert selenium test resource(s) on blank project.
# Required variables:
#   ${ARCHETYPE_ARTIFACT_ID}=Artifact ID of blank project's archetype.
#   ${ARTIFACT_ID}=Artifact ID of tutorial project.

TARGET_DIR=$1
if test -n "${TARGET_DIR}/${ARTIFACT_ID}"; then
  pushd "${TARGET_DIR}/${ARTIFACT_ID}"
fi

# WebDriverManager version
find ./pom.xml | xargs sed -i -e 's|</dependencies>|    <dependency>\
                <groupId>io.github.bonigarcia</groupId>\
                <artifactId>webdrivermanager</artifactId>\
                <version>${webdrivermanager.version}</version>\
            </dependency>\
\
        </dependencies>|'

find ./pom.xml | xargs sed -i -e 's|</properties>|    <webdrivermanager.version>3.1.1</webdrivermanager.version>\
    </properties>|'

if test `echo $ARCHETYPE_ARTIFACT_ID | grep multi`;then
  # multi project
  # project-selenium/pom.xml
  find ./$ARTIFACT_ID-selenium/pom.xml | xargs sed -i -e 's|</dependencies>|    <dependency>\
                <groupId>io.github.bonigarcia</groupId>\
                <artifactId>webdrivermanager</artifactId>\
                <scope>test</scope>\
            </dependency>\
\
        </dependencies>|'
fi

if test -n "${TARGET_DIR}/${ARTIFACT_ID}"; then
  popd
fi
