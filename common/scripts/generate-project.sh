#!/bin/bash
# Generate blank project from specified archetype.
# Required variables:
#   ${ARCHETYPE_ARTIFACT_ID}=Artifact ID of blank project's archetype.
#   ${ARCHETYPE_VERSION}=Version of blank project's archetype.
#   ${GROUP_ID}=Group ID of tutorial project.
#   ${ARTIFACT_ID}=Artifact ID of tutorial project.
#   ${VERSION}=Version of tutorial project.

mvn archetype:generate -B \
 -DarchetypeGroupId=org.terasoluna.gfw.blank \
 -DarchetypeArtifactId=${ARCHETYPE_ARTIFACT_ID} \
 -DarchetypeVersion=${ARCHETYPE_VERSION} \
 -DgroupId=${GROUP_ID} \
 -DartifactId=${ARTIFACT_ID} \
 -Dversion=${VERSION}
