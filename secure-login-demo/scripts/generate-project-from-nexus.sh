#!/bin/bash

#${ARCHETYPE_VERSION}=terasoluna-gfw-blank-archetype-version
#${VERSION}=tutorial pj version

# settings of maven archetype catalog
if test `echo ${ARCHETYPE_VERSION} | tail -c8 ` = "RELEASE"; then
  ARCHETYPE_CATALOG=http://repo.terasoluna.org/nexus/content/repositories/terasoluna-gfw-releases
else
  case "${ARCHETYPE_VERSION:0:5}" in
    5.2* | 5.0* )
      ARCHETYPE_CATALOG=http://repo.terasoluna.org/nexus/content/repositories/terasoluna-gfw-snapshots;;
    * )
      ARCHETYPE_CATALOG=https://oss.sonatype.org/content/repositories/snapshots;;
  esac
fi

# mybatis app multi
mvn archetype:generate -B \
 -DarchetypeCatalog=${ARCHETYPE_CATALOG} \
 -DarchetypeGroupId=org.terasoluna.gfw.blank \
 -DarchetypeArtifactId=terasoluna-gfw-multi-web-blank-mybatis3-archetype \
 -DarchetypeVersion=${ARCHETYPE_VERSION} \
 -DgroupId=org.terasoluna.securelogin \
 -DartifactId=secure-login \
 -Dversion=${VERSION}
