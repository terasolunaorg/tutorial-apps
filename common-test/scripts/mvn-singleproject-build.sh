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
mvn test -f $POM $TESTOPTION
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
