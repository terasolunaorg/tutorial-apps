#!/bin/bash

#${ARCHETYPE_VERSION}=terasoluna-gfw-blank-archetype-version
#${VERSION}=tutorial pj version

# single without any DB configuration, mybatis single, mybatis multi, jpa single, jpa multi
# create dir for complete map single
mkdir tmp
cd ./tmp

# single without any DB configuration
mvn archetype:generate -B \
 -DarchetypeGroupId=org.terasoluna.gfw.blank \
 -DarchetypeArtifactId=terasoluna-gfw-web-blank-archetype \
 -DarchetypeVersion=${ARCHETYPE_VERSION} \
 -DgroupId=todo \
 -DartifactId=todo \
 -Dversion=${VERSION}
cd ..

# mybatis app single
mvn archetype:generate -B \
 -DarchetypeGroupId=org.terasoluna.gfw.blank \
 -DarchetypeArtifactId=terasoluna-gfw-web-blank-mybatis3-archetype \
 -DarchetypeVersion=${ARCHETYPE_VERSION} \
 -DgroupId=todo \
 -DartifactId=todo-mybatis3 \
 -Dversion=${VERSION}

# mybatis app multi
mvn archetype:generate -B \
 -DarchetypeGroupId=org.terasoluna.gfw.blank \
 -DarchetypeArtifactId=terasoluna-gfw-multi-web-blank-mybatis3-archetype \
 -DarchetypeVersion=${ARCHETYPE_VERSION} \
 -DgroupId=todo \
 -DartifactId=todo-mybatis3-multi \
 -Dversion=${VERSION}

# jpa app single
mvn archetype:generate -B \
 -DarchetypeGroupId=org.terasoluna.gfw.blank \
 -DarchetypeArtifactId=terasoluna-gfw-web-blank-jpa-archetype \
 -DarchetypeVersion=${ARCHETYPE_VERSION} \
 -DgroupId=todo \
 -DartifactId=todo-jpa \
 -Dversion=${VERSION}

# jpa multi single
mvn archetype:generate -B \
 -DarchetypeGroupId=org.terasoluna.gfw.blank \
 -DarchetypeArtifactId=terasoluna-gfw-multi-web-blank-jpa-archetype \
 -DarchetypeVersion=${ARCHETYPE_VERSION} \
 -DgroupId=todo \
 -DartifactId=todo-jpa-multi \
 -Dversion=${VERSION}
