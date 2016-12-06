#!/bin/bash
# Build and test a single project

# $1 : root pom

# settings of POM
POM=$1

# build
echo "build $POM"
mvn -U -f $POM install -DskipTests=true
buildResult=$?
if test ${buildResult} -ne 0 ; then
  echo "[ERROR] Failed a build."
  exit ${buildResult}
fi

# run
echo "Run $POM"
mvn cargo:daemon-start -f $POM
buildResult=$?
if test ${buildResult} -ne 0 ; then
  echo "[ERROR] Failed a build."
  exit ${buildResult}
fi

# test
echo "Test $POM"
mvn test -f $POM
buildResult=$?
if test ${buildResult} -ne 0 ; then
  echo "[ERROR] Failed a build."
  exit ${buildResult}
fi

# stop
echo "Stop $POM"
mvn verify cargo:daemon-stop -f $POM -DskipTests=true
buildResult=$?
if test ${buildResult} -ne 0 ; then
  echo "[ERROR] Failed a build."
  exit ${buildResult}
fi
