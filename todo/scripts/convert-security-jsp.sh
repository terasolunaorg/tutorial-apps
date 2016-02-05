#!/bin/bash

#include.jspの paga session=の値をtrueに
find . -type f -name 'include.jsp' | xargs sed -i -e 's|<%@ page session="false"|<%@ page session="true"|'


# home.jspに以下を追加
# account変数
find . -type f -name 'home.jsp' | xargs sed -i -e 's|</head>|</head>\n\n<!-- (1) -->\n<sec:authentication property="principal.account" var="account" />|'

# アカウント情報と、ログアウトボタン追加
find . -type f -name 'home.jsp' | xargs sed -i -e 's|<p>The time on the server is ${serverTime}.</p>|\
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