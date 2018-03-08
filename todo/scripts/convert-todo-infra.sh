#!/bin/bash
# Convert infra property definitions on blank project.
# Parameters:
#   $1 : (Optional) Target project path to convert.

TARGET_DIR=$1
if test -n $TARGET_DIR; then
  pushd "${TARGET_DIR}/${ARTIFACT_ID}"
fi

# -infra.properties
find ./ -type f -name '*infra.properties' | xargs sed -i 's/DB_CLOSE_DELAY=-1*/DB_CLOSE_DELAY=-1;INIT=create table if not exists todo(todo_id varchar(36) primary key, todo_title varchar(30), finished boolean, created_at timestamp)/g'

if test -n $TARGET_DIR; then
  popd
fi
