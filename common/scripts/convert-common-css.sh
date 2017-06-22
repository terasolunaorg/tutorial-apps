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
.strike {
    text-decoration: line-through;
}

.alert {
    border: 1px solid;
    margin-bottom: 5px;
}

.alert-error {
    background-color: #c60f13;
    border-color: #970b0e;
    color: white;
}

.alert-success {
    background-color: #5da423;
    border-color: #457a1a;
    color: white;
}

.text-error {
    color: #c60f13;
}

.alert ul {
    margin: 15px 0px 15px 0px;
}
' >> $i ;done

if test -n $TARGET_DIR; then
  popd
fi
