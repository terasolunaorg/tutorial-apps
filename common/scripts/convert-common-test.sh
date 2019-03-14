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

find ./pom.xml | xargs sed -i -e 's|</project>|  <profiles>\
    <profile>\
      <id>jdk11</id>\
      <activation>\
        <jdk>11</jdk>\
      </activation>\
      <properties>\
        <java-version>11</java-version>\
        <cargo.jvmargs>-Xms512m -Xmx1024m -Djava.locale.providers=COMPAT,CLDR,SPI</cargo.jvmargs>\
      </properties>\
      <build>\
        <pluginManagement>\
          <plugins>\
            <plugin>\
              <groupId>org.apache.maven.plugins</groupId>\
              <artifactId>maven-surefire-plugin</artifactId>\
              <configuration>\
                <systemProperties>\
                  <jdk.tls.client.protocols>TLSv1.2</jdk.tls.client.protocols>\
                </systemProperties>\
              </configuration>\
            </plugin>\
          </plugins>\
        </pluginManagement>\
      </build>\
    </profile>\
  </profiles>\
</project>|'

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
