#!/bin/bash

#${ARCHETYPE_VERSION}=terasoluna-gfw-blank-archetype-version
#${VERSION}=tutorial pj version

# settings of maven archetype catalog
if test `echo ${ARCHETYPE_VERSION} | tail -c8 ` = "RELEASE"; then
  ARCHETYPE_CATALOG=http://repo.terasoluna.org/nexus/content/repositories/terasoluna-gfw-releases
else
  case "${ARCHETYPE_VERSION:0:5}" in
    5.2* | 5.1* | 5.0* )
      ARCHETYPE_CATALOG=http://repo.terasoluna.org/nexus/content/repositories/terasoluna-gfw-snapshots;;
    * )
      ARCHETYPE_CATALOG=https://oss.sonatype.org/content/repositories/snapshots;;
  esac
fi

# single without any DB configuration, mybatis single, mybatis multi, jpa single, jpa multi
# single without any DB configuration
mvn org.apache.maven.plugins:maven-archetype-plugin:2.4:generate -B \
 -DarchetypeCatalog=${ARCHETYPE_CATALOG} \
 -DarchetypeGroupId=org.terasoluna.gfw.blank \
 -DarchetypeArtifactId=terasoluna-gfw-web-blank-archetype \
 -DarchetypeVersion=${ARCHETYPE_VERSION} \
 -DgroupId=todo \
 -DartifactId=todo-api \
 -Dversion=${VERSION}

# mybatis app single
mvn org.apache.maven.plugins:maven-archetype-plugin:2.4:generate -B \
 -DarchetypeCatalog=${ARCHETYPE_CATALOG} \
 -DarchetypeGroupId=org.terasoluna.gfw.blank \
 -DarchetypeArtifactId=terasoluna-gfw-web-blank-mybatis3-archetype \
 -DarchetypeVersion=${ARCHETYPE_VERSION} \
 -DgroupId=todo \
 -DartifactId=todo-api-mybatis3 \
 -Dversion=${VERSION}

# mybatis app multi
mvn org.apache.maven.plugins:maven-archetype-plugin:2.4:generate -B \
 -DarchetypeCatalog=${ARCHETYPE_CATALOG} \
 -DarchetypeGroupId=org.terasoluna.gfw.blank \
 -DarchetypeArtifactId=terasoluna-gfw-multi-web-blank-mybatis3-archetype \
 -DarchetypeVersion=${ARCHETYPE_VERSION} \
 -DgroupId=todo \
 -DartifactId=todo-api-mybatis3-multi \
 -Dversion=${VERSION}

# jpa app single
mvn org.apache.maven.plugins:maven-archetype-plugin:2.4:generate -B \
 -DarchetypeCatalog=${ARCHETYPE_CATALOG} \
 -DarchetypeGroupId=org.terasoluna.gfw.blank \
 -DarchetypeArtifactId=terasoluna-gfw-web-blank-jpa-archetype \
 -DarchetypeVersion=${ARCHETYPE_VERSION} \
 -DgroupId=todo \
 -DartifactId=todo-api-jpa \
 -Dversion=${VERSION}

# jpa multi single
mvn org.apache.maven.plugins:maven-archetype-plugin:2.4:generate -B \
 -DarchetypeCatalog=${ARCHETYPE_CATALOG} \
 -DarchetypeGroupId=org.terasoluna.gfw.blank \
 -DarchetypeArtifactId=terasoluna-gfw-multi-web-blank-jpa-archetype \
 -DarchetypeVersion=${ARCHETYPE_VERSION} \
 -DgroupId=todo \
 -DartifactId=todo-api-jpa-multi \
 -Dversion=${VERSION}
