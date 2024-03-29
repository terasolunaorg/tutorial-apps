<!DOCTYPE html>
<html class="no-js">
<head>
<meta charset="utf-8" />
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
<meta name="viewport" content="width=device-width" />
<script type="text/javascript">
    
</script>
<c:set var="titleKey" value="title.login.loginForm" />
<title><spring:message code="${titleKey}" text="session-tutorial-complete" /></title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/app/css/styles.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/vendor/bootstrap-3.0.0/css/bootstrap.css"
    media="screen, projection">
</head>
<body>
    <div class="container">
    <jsp:include page="../layout/header.jsp" />
<div id="wrapper">
    <h3>Login with Username and Password</h3>

    <c:if test="${f:h(param.error)}">
        <t:messagesPanel messagesType="error" messagesAttributeName="SPRING_SECURITY_LAST_EXCEPTION" />
    </c:if>

    <form:form action="${pageContext.request.contextPath}/authenticate">
        <table>
            <tr>
                <td><label for="email">E-mail</label></td>
                <td><label for="password">Password</label></td>

            </tr>
            <tr>
                <td><input type="text" id="email" name="email"></td>
                <td><input type="password" id="password" name="password" /></td>
            </tr>
            <tr>
                <td>&nbsp;</td>
                <td><input name="submit" type="submit" id="login" value="Login" /></td>
            </tr>
        </table>
    </form:form>
    Account create page is <a id="createAccount" href="${pageContext.request.contextPath}/account/create?form">here</a>
</div>
        <hr>
        <p style="text-align: center; background: #e5eCf9;">Copyright &copy; 20XX CompanyName</p>
    </div>
</body>
</html>
