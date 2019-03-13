#!/bin/bash
# Build and test a multi project
# Required variables:
#   ${ARTIFACT_ID}=Artifact ID of tutorial project.
# Parameters:
#   $1 : Root pom.
#   $2 : (Optional) Option of maven test command.

# settings of POM
POM=$1

# settings of TESTOPTION
if test $# == 2;then
  if test $2 != "";then
    TESTOPTION=$2
    echo "TESTOPTION = $TESTOPTION"
  fi
fi

# Definition of function
# Check the build result and exit the script if it ends abnormally
buildResultCheck(){
  if test $1 -ne 0 ; then
    echo "[ERROR] Failed a build."
    exit $1
  fi
}

# build
echo "build $POM"
mvn -U -f "$POM" -am -pl ${ARTIFACT_ID}-web install -P local
buildResultCheck $?

# run
echo "Run $POM"
mvn cargo:daemon-start -f "$POM" -pl ${ARTIFACT_ID}-web
buildResultCheck $?

# test
echo "Test $POM"
mvn test -f "$POM" -pl ${ARTIFACT_ID}-selenium $TESTOPTION
buildResultCheck $?

# stop
echo "Stop $POM"
mvn cargo:daemon-stop -f "$POM" -pl ${ARTIFACT_ID}-web
buildResultCheck $?
