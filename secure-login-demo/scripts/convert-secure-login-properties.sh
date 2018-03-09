#!/bin/bash
# Convert message resource(s) on blank project.
# Parameters:
#   $1 : (Optional) Target project path to convert.

TARGET_DIR=$1
if test -n $TARGET_DIR; then
  pushd "$TARGET_DIR"
fi

# application-messages.properties
find ./ -type f -name 'application-messages.properties' | xargs sed -i -e 's|e.xx.fw|e.sl.fw|g'

# application-messages.properties
find ./ -type f -name 'application-messages.properties' | xargs sed -i -e 's|e.sl.fw.5001 = Resource not found.|e.sl.fw.5001 = Resource not found.\
\
e.sl.fw.6001 = File upload error occurred!|'

# application-messages.properties
find ./ -type f -name 'application-messages.properties' | xargs sed -i -e 's|e.sl.fw.7004 = Missing CSRF detected!|e.sl.fw.7004 = Missing CSRF detected!\
e.sl.fw.7005 = Prohibited character detected!|'

# application-messages.properties
find ./ -type f -name 'application-messages.properties' | xargs sed -i -e 's|e.sl.fw.9002 = Data Access error!|e.sl.fw.9002 = Data Access error!\
\
e.sl.pr.2001 = The URL has expired.\
\
e.sl.pr.5001 = Given username does not match with token. \
e.sl.pr.5002 = Given token was not found.\
e.sl.pr.5003 = Invalid Secret.\
e.sl.pr.5004 = Max number of attempts was exceeded.\
\
e.sl.fa.5001 = Given account is not found! username : {0}.\
\
e.sl.ac.5001 = Given username already exists!\
\
w.sl.pe.0001 = Your password has expired. Please change.|'


# application.properties
for i in ` find ./ -type f -name 'application.properties' `; do echo -e '

app.security.prohibitedChars=&\\\\!"<>*
app.security.prohibitedCharsForFileName=&\\\\!"<>*;:' >>$i ; done


# ValidationMessages.properties
for i in ` find ./ -type f -name 'ValidationMessages.properties' `; do echo -e '

com.example.securelogin.app.common.validation.Confirm.message = Confirmation is failed.

com.example.securelogin.app.common.validation.ConfirmOldPassword.message = It'"'"'s not matched with current password.

com.example.securelogin.app.common.validation.StrongPassword.message = Password is not strong enough.

com.example.securelogin.app.common.validation.NotReusedPassword.message = You must not reuse password.

com.example.securelogin.app.common.validation.DomainRestrictedEmail.message = This domain is not allowed.

com.example.securelogin.app.common.validation.DomainRestrictedURL.message = This domain is not allowed.

com.example.securelogin.app.common.validation.NotContainControlChars.message = Control characters are not allowed.

com.example.securelogin.app.common.validation.NotContainControlCharsExceptNewlines.message = Control characters are not allowed.

com.example.securelogin.app.common.validation.FileExtension.message = The file extension is not allowed.

com.example.securelogin.app.common.validation.FileNamePattern.message = The file name is not allowed.

com.example.securelogin.app.common.validation.UploadFileRequired.message = The file is required.

com.example.securelogin.app.common.validation.UploadFileNotEmpty.message = The file must not be empty.

com.example.securelogin.app.common.validation.UploadFileMaxSize.message = The file size must be smaller than {value} bytes.' >>$i ; done


if test -n $TARGET_DIR; then
  popd
fi
