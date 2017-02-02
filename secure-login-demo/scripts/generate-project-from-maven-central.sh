#!/bin/bash

#${ARCHETYPE_VERSION}=terasoluna-gfw-blank-archetype-version
#${VERSION}=tutorial pj version

# mybatis app multi
mvn archetype:generate -B \
 -DarchetypeGroupId=org.terasoluna.gfw.blank \
 -DarchetypeArtifactId=terasoluna-gfw-multi-web-blank-mybatis3-archetype \
 -DarchetypeVersion=${ARCHETYPE_VERSION} \
 -DgroupId=org.terasoluna.securelogin \
 -DartifactId=secure-login \
 -Dversion=${VERSION}

