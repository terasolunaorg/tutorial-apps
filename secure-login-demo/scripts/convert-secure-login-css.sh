#!/bin/bash
# Convert css resource(s) on blank project.
# Parameters:
#   $1 : (Optional) Target project path to convert.

TARGET_DIR=$1
if test -n "${TARGET_DIR}/${ARTIFACT_ID}"; then
  pushd "${TARGET_DIR}/${ARTIFACT_ID}"
fi

# styles.css
for i in ` find ./ -type f -name 'styles.css' `; do echo -e '
.test-description{
    background-color: #d4e4e5;
}

.alert {
    border: 1px solid;
}

.alert-error {
    background-color: #c60f13;
    border-color: #970b0e;
    color: white;
}

.form-horizontal input {
    display: block;
    float: left;
}

.form-horizontal label {
    display: block;
    float: left;
    text-align: right;
    float: left;
}

.form-horizontal br {
    clear: left;
}

.error-label {
    color: #b94a48;
}

.error-input {
    border-color: #b94a48;
    margin-left: 5px;
}

.error-messages {
    color: #b94a48;
    display: block;
    padding-left: 5px;
    overflow-x: auto;
}

.alert-warning {
  background: #fffceb;
  color: #e28327;
  border-color: rgba(226, 131, 39, 0.3);
}' >> $i ;done

if test -n "${TARGET_DIR}/${ARTIFACT_ID}"; then
  popd
fi
