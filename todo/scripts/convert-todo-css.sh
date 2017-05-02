#!/bin/bash
# Convert css resource(s) on blank project.
# Parameters:
#   $1 : (Optional) Target project path to convert.

TARGET_DIR=$1
if test -n $TARGET_DIR; then
  pushd "$TARGET_DIR"
fi

# styles.css
for i in ` find ./ -type f -name 'styles.css' `; do echo -e ' 
#todoList li {
    margin-top: 5px;
}
' >> $i ;done

if test -n $TARGET_DIR; then
  popd
fi
