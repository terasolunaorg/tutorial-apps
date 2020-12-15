#!/bin/bash
# Convert java source(s) on todo app.
# Parameters:
#   $1 : (Optional) Target project path to convert.

TARGET_DIR=$1
if test -n "${TARGET_DIR}/${ARTIFACT_ID}"; then
  pushd "${TARGET_DIR}/${ARTIFACT_ID}"
fi

######### TodoService.java #########

# TodoService.java
find ./ -type f -name 'TodoService.java' | xargs sed -i -e 's|Collection<Todo> findAll();|Todo findOne(String todoId);\
\
    Collection<Todo> findAll();|'

# For TodoServiceImplImpl.java, prepare another file in todo-rest without using the sed command.

if test -n "${TARGET_DIR}/${ARTIFACT_ID}"; then
  popd
fi
