#!/bin/bash
# Copy and edit maven settings file.
# Required variables:
#   ${ARCHETYPE_VERSION}=Version of blank project's archetype.

if test `echo ${ARCHETYPE_VERSION} | tail -c8 ` != "RELEASE"; then
  SCRIPT_DIR=`dirname "$0"`
  pushd "${SCRIPT_DIR}"
  cp ~/.m2/settings.xml ./

  sed -i -e 's|</profiles>|\
    <profile>\
      <id>archetype</id>\
      <repositories>\
        <repository>\
          <id>archetype</id>\
          <url>https://oss.sonatype.org/content/repositories/snapshots</url>\
        </repository>\
      </repositories>\
    </profile>\
  </profiles>|g' ./settings.xml

  popd
fi
