#!/bin/bash
# Convert jsp resource(s) on blank project.
# Parameters:
#   $1 : (Optional) Target project path to convert.

APPLICATION_DIR=$1
if test -n $APPLICATION_DIR; then
  pushd "$APPLICATION_DIR"
fi

# errorPage.jsp
find ./ -type f -name '*Error.jsp' | xargs sed -i -e 's|e.xx.fw|e.sl.fw|g'


# include.jsp
find ./ -type f -name 'include.jsp' | xargs sed -i -e 's|<%@ page session="false"%>|<%@ page session="true"%>|g'

# include.jsp
for i in ` find ./ -type f -name 'include.jsp' `; do echo -e '<%@ taglib uri="http://www.joda.org/joda/time/tags" prefix="joda"%>' >> $i ;done


# header.jsp
find ./ -type f -name 'header.jsp' | xargs sed -i -e 's|<h1>|<c:if test="${!empty param.testdescription}">\
<h1 class="test-description">|g'

# header.jsp
find ./ -type f -name 'header.jsp' | xargs sed -i -e 's|    <a href="${pageContext.request.contextPath}/">secure-login</a>|Test : ${f:h(param.testdescription)}|g'

# header.jsp
find ./ -type f -name 'header.jsp' | xargs sed -i -e 's|</h1>|</h1>\
</c:if>|g'


# home.jsp
find ./ -type f -name 'home.jsp' | xargs sed -i -e 's|<div id="wrapper">|<!DOCTYPE html>\
<html>\
<head>\
<meta charset="utf-8">\
<title>Home</title>\
<link rel="stylesheet" href="${f:h(pageContext.request.contextPath)}/resources/app/css/styles.css">\
</head>\
<body>\
    <div id="wrapper">\
        <span id="expiredMessage"> <t:messagesPanel />\
        </span>|g'

# home.jsp
find ./ -type f -name 'home.jsp' | xargs sed -i -e 's|<h1 id="title">Hello world!</h1>|    <h1 id="title">Hello world!</h1>|g'

# home.jsp
find ./ -type f -name 'home.jsp' | xargs sed -i -e 's|    <p>The time on the server is ${serverTime}.</p>|\
        <p>Welcome ${f:h(account.firstName)} ${f:h(account.lastName)}</p>\
\
        <c:if test="${!empty lastLoginDate}">\
            <p id="lastLogin">Last login date is ${f:h(lastLoginDate)}.</p>\
        </c:if>\
\
        <div>\
            <a id="info" href="${f:h(pageContext.request.contextPath)}/accounts" />\
                Account Information\
            </a>\
        </div>\
\
        <sec:authorize url="/unlock">\
        <div>\
            <a id="unlock" href="${f:h(pageContext.request.contextPath)}/unlock?form">\
                Unlock Account\
            </a>\
        </div>\
        </sec:authorize>\
\
        <form:form action="${f:h(pageContext.request.contextPath)}/logout">\
            <button id="logout">Logout</button>\
        </form:form>|g'

# home.jsp
find ./ -type f -name 'home.jsp' | xargs sed -i -e '/<button id="logout">Logout<\/button>/,/<\/html>/s|</div>|    </div>\
</body>\
</html>|g'


if test -n $APPLICATION_DIR; then
  popd
fi
