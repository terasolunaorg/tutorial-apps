#!/bin/bash
# Convert jsp resource(s) on blank project.
# Parameters:
#   $1 : (Optional) Target project path to convert.

TARGET_DIR=$1
if test -n $TARGET_DIR; then
  pushd "$TARGET_DIR"
fi

# include.jsp
find ./ -type f -name 'include.jsp' | xargs sed -i -e 's|<%@ page session="false"|<%@ page session="true"|'

# home.jsp
# account変数
find ./ -type f -name 'home.jsp' | xargs sed -i -e 's|</head>|</head>\n\n<!-- (1) -->\n<sec:authentication property="principal.account" var="account" />|'
find ./ -type f -name 'home.jsp' | xargs sed -i -e 's|<h1 id="title">Hello world!</h1>|<h1 id="title">Hello world!</h1>\n\n<!-- (1) -->\n<sec:authentication property="principal.account" var="account" />|'

# home.jsp
find ./ -type f -name 'home.jsp' | xargs sed -i -e 's|<p>The time on the server is ${serverTime}.</p>|\
    <p>The time on the server is ${serverTime}.</p>\
\
    <p>Welcome ${f:h(account.firstName)} ${f:h(account.lastName)} !!</p>\
\
    <p>\
        <form:form action="${pageContext.request.contextPath}/logout">\
            <button type="submit">Logout</button>\
        </form:form>\
    </p>\
\
    <ul>\
        <li><a href="${pageContext.request.contextPath}/account">view account</a></li>\
    </ul>|'

if test -n $TARGET_DIR; then
  popd
fi
