#!/bin/bash

target[0]=./target-project/first-spring-security-mybatis3
target[1]=./target-project/first-spring-security-mybatis3-multi

# include.jsp
for i in "${target[*]}";do
find $i -type f -name 'include.jsp' | xargs sed -i -e 's|<%@ page session="false"|<%@ page session="true"|'
done

# home.jsp
# account変数
for i in "${target[*]}";do
find $i -type f -name 'home.jsp' | xargs sed -i -e 's|</head>|</head>\n\n<!-- (1) -->\n<sec:authentication property="principal.account" var="account" />|'
find $i -type f -name 'home.jsp' | xargs sed -i -e 's|<h1 id="title">Hello world!</h1>|<h1 id="title">Hello world!</h1>\n\n<!-- (1) -->\n<sec:authentication property="principal.account" var="account" />|'
done

# home.jsp
for i in "${target[*]}";do
find $i -type f -name 'home.jsp' | xargs sed -i -e 's|<p>The time on the server is ${serverTime}.</p>|\
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
done
