#!/bin/bash
# Build and test a single project

# $1 : root pom
# $2 : option of maven test command

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
  if test ${buildResult} -ne 0 ; then
    echo "[ERROR] Failed a build."
    exit ${buildResult}
  fi
}

# build
echo "build $POM"
mvn -U -f $POM install -DskipTests=true
buildResult=$?
buildResultCheck

# run
echo "Run $POM"
mvn cargo:daemon-start -f $POM
buildResult=$?
buildResultCheck

# test
echo "Test $POM"
mvn test -f $POM $TESTOPTION
buildResult=$?
buildResultCheck

# stop
echo "Stop $POM"
mvn cargo:daemon-stop -f $POM -Dmaven.test.skip=true
buildResult=$?
buildResultCheck
