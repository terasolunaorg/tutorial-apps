#!/bin/bash
# Build and test a multi project

# $1 : root pom
# $2 : option of maven test command

# settings of POM
export POM=$1

# settings of TESTOPTION
if test $# == 2;then
  if test $2 != "";then
    TESTOPTION=$2
    echo "TESTOPTION = $TESTOPTION"
  fi
fi

# settings of PROJECTNAME
PROJECTNAME=`echo $POM | cut -d "/" -f 3 `
if test `echo $PROJECTNAME|grep secure`;then
  PROJECTNAME=${PROJECTNAME%%-demo}
fi

# build
echo "build $POM"
mvn -U -f $POM -am -pl ${PROJECTNAME}-web install
buildResult=$?
if test ${buildResult} -ne 0 ; then
  echo "[ERROR] Failed a build."
  exit ${buildResult}
fi

# run
echo "Run $POM"
mvn cargo:daemon-start -f $POM -pl ${PROJECTNAME}-web
buildResult=$?
if test ${buildResult} -ne 0 ; then
  echo "[ERROR] Failed a build."
  exit ${buildResult}
fi

# test
echo "Test $POM"
mvn test -f $POM -pl ${PROJECTNAME}-selenium $TESTOPTION
buildResult=$?
if test ${buildResult} -ne 0 ; then
  echo "[ERROR] Failed a build."
  exit ${buildResult}
fi

# stop
echo "Stop $POM"
mvn cargo:daemon-stop -f $POM  -pl ${PROJECTNAME}-web -DskipTests=true
buildResult=$?
if test ${buildResult} -ne 0 ; then
  echo "[ERROR] Failed a build."
  exit ${buildResult}
fi
