#!/bin/bash

#${ARCHETYPE_VERSION}=terasoluna-gfw-blank-archetype-version
#${VERSION}=tutorial pj version

# mybatis app single
mvn archetype:generate -B \
 -DarchetypeGroupId=org.terasoluna.gfw.blank \
 -DarchetypeArtifactId=terasoluna-gfw-web-blank-mybatis3-archetype \
 -DarchetypeVersion=${ARCHETYPE_VERSION} \
 -DgroupId=com.example.security \
 -DartifactId=first-spring-security-mybatis3 \
 -Dversion=${VERSION}

# mybatis app multi
mvn archetype:generate -B \
 -DarchetypeGroupId=org.terasoluna.gfw.blank \
 -DarchetypeArtifactId=terasoluna-gfw-multi-web-blank-mybatis3-archetype \
 -DarchetypeVersion=${ARCHETYPE_VERSION} \
 -DgroupId=com.example.security \
 -DartifactId=first-spring-security-mybatis3-multi \
 -Dversion=${VERSION}
