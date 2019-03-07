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
  # pom.xml
  find ./pom.xml | xargs sed -i -e 's|</dependencies>|    <dependency>\
                <groupId>org.seleniumhq.selenium</groupId>\
                <artifactId>selenium-java</artifactId>\
                <version>${selenium.version}</version>\
            </dependency>\
            <dependency>\
                <groupId>org.seleniumhq.selenium</groupId>\
                <artifactId>selenium-chrome-driver</artifactId>\
                <version>${selenium.version}</version>\
            </dependency>\
            <dependency>\
                <groupId>org.seleniumhq.selenium</groupId>\
                <artifactId>selenium-remote-driver</artifactId>\
                <version>${selenium.version}</version>\
            </dependency>\
            <dependency>\
                <groupId>org.seleniumhq.selenium</groupId>\
                <artifactId>selenium-api</artifactId>\
                <version>${selenium.version}</version>\
            </dependency>\
            <dependency>\
                <groupId>org.seleniumhq.selenium</groupId>\
                <artifactId>selenium-firefox-driver</artifactId>\
                <version>${selenium.version}</version>\
            </dependency>\
            <dependency>\
                <groupId>org.seleniumhq.selenium</groupId>\
                <artifactId>selenium-ie-driver</artifactId>\
                <version>${selenium.version}</version>\
            </dependency>\
            <dependency>\
                <groupId>org.seleniumhq.selenium</groupId>\
                <artifactId>selenium-safari-driver</artifactId>\
                <version>${selenium.version}</version>\
            </dependency>\
            <dependency>\
                <groupId>org.seleniumhq.selenium</groupId>\
                <artifactId>selenium-support</artifactId>\
                <version>${selenium.version}</version>\
  # project-selenium/pom.xml
  find ./$ARTIFACT_ID-selenium/pom.xml | xargs sed -i -e 's|</dependencies>|    <dependency>\
                <groupId>io.github.bonigarcia</groupId>\
                <artifactId>webdrivermanager</artifactId>\
                <scope>test</scope>\
            </dependency>\
\
        </dependencies>|'
else
  # single project
  # pom.xml
  find ./pom.xml | xargs sed -i -e 's|<artifactId>selenium-java</artifactId>|<artifactId>selenium-java</artifactId>\
            <version>${selenium.version}</version>\
            <scope>test</scope>\
        </dependency>\
        <dependency>\
            <groupId>org.seleniumhq.selenium</groupId>\
            <artifactId>selenium-chrome-driver</artifactId>\
            <version>${selenium.version}</version>\
            <scope>test</scope>\
        </dependency>\
        <dependency>\
            <groupId>org.seleniumhq.selenium</groupId>\
            <artifactId>selenium-remote-driver</artifactId>\
            <version>${selenium.version}</version>\
            <scope>test</scope>\
        </dependency>\
        <dependency>\
            <groupId>org.seleniumhq.selenium</groupId>\
            <artifactId>selenium-api</artifactId>\
            <version>${selenium.version}</version>\
            <scope>test</scope>\
        </dependency>\
        <dependency>\
            <groupId>org.seleniumhq.selenium</groupId>\
            <artifactId>selenium-firefox-driver</artifactId>\
            <version>${selenium.version}</version>\
            <scope>test</scope>\
        </dependency>\
        <dependency>\
            <groupId>org.seleniumhq.selenium</groupId>\
            <artifactId>selenium-ie-driver</artifactId>\
            <version>${selenium.version}</version>\
            <scope>test</scope>\
        </dependency>\
        <dependency>\
            <groupId>org.seleniumhq.selenium</groupId>\
            <artifactId>selenium-safari-driver</artifactId>\
            <version>${selenium.version}</version>\
            <scope>test</scope>\
        </dependency>\
        <dependency>\
            <groupId>org.seleniumhq.selenium</groupId>\
            <artifactId>selenium-support</artifactId>\
            <version>${selenium.version}</version>|'
fi

# pom.xml
find ./pom.xml | xargs sed -i -e 's|</properties>|    <selenium.version>2.46.0</selenium.version>\
    </properties>|'

if test -n "${TARGET_DIR}/${ARTIFACT_ID}"; then
  popd
fi
