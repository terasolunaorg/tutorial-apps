#!/bin/bash
# Convert xml resource(s) on blank project.
# Parameters:
#   $1 : (Optional) Target project path to convert.

TARGET_DIR=$1
if test -n "${TARGET_DIR}/${ARTIFACT_ID}"; then
  pushd "${TARGET_DIR}/${ARTIFACT_ID}"
fi

# spring-security.xml
find ./ -type f -name 'spring-security.xml' | xargs sed -i -e 's|<sec:form-login/>|\
        <!-- (1) -->\
        <sec:form-login login-page="/login/loginForm"\
            authentication-failure-url="/login/loginForm?error=true" />\
        <!-- (2) -->\
        <sec:logout logout-success-url="/" delete-cookies="JSESSIONID" />\
        <!-- (3) -->\
        <sec:intercept-url pattern="/login/**"\
            access="permitAll" />\
        <sec:intercept-url pattern="/**" access="isAuthenticated()" />|'

# spring-security.xml
find ./ -type f -name 'spring-security.xml' | xargs sed -i -e 's|<sec:authentication-manager />|\
    <sec:authentication-manager>\
        <!-- com.example.security.domain.service.userdetails.SampleUserDetailsService \
            is scanned by component scan with @Service -->\
        <!-- (4) -->\
        <sec:authentication-provider\
            user-service-ref="sampleUserDetailsService">\
            <!-- (5) -->\
            <sec:password-encoder ref="passwordEncoder" />\
        </sec:authentication-provider>\
    </sec:authentication-manager>|'

if test -n "${TARGET_DIR}/${ARTIFACT_ID}"; then
  popd
fi
