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
  if test $1 -ne 0 ; then
    echo "[ERROR] Failed a build."
    exit $1
  fi
}

# build
echo "build $POM"
mvn -U -f $POM install -Dmaven.test.skip=true
buildResultCheck $?

# run
echo "Run $POM"
mvn cargo:daemon-start -f $POM
buildResultCheck $?

# test
echo "Test $POM"
mvn test -f $POM $TESTOPTION
buildResultCheck $?

# stop
echo "Stop $POM"
mvn cargo:daemon-stop -f $POM
buildResultCheck $?
