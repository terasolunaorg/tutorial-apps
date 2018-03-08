#!/bin/bash
# Convert jsp resource(s) on blank project.
# Parameters:
#   $1 : (Optional) Target project path to convert.

TARGET_DIR=$1
if test -n $TARGET_DIR; then
  pushd "${TARGET_DIR}/${ARTIFACT_ID}"
fi

# include.jsp
find ./ -type f -name 'include.jsp' | xargs sed -i -e 's|<%@ page session="false"|<%@ page session="true"|'

# template.jsp
find ./ -type f -name 'template.jsp' | xargs sed -i -e 's|href="${pageContext.request.contextPath}/resources/app/css/styles.css">|href="${pageContext.request.contextPath}/resources/app/css/styles.css">\
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/vendor/bootstrap-3.0.0/css/bootstrap.css"\
    media="screen, projection">|'
    
if test -n $TARGET_DIR; then
  popd
fi
