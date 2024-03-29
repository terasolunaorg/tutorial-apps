<!DOCTYPE html>
<html class="no-js">
<head>
<meta charset="utf-8" />
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
<meta name="viewport" content="width=device-width" />
<script type="text/javascript">
    
</script>
<c:set var="titleKey" value="title.login.login" />
<title><spring:message code="${titleKey}" text="secure-login" /></title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/app/css/styles.css">
</head>
<body>
    <div class="container">
    <jsp:include page="../layout/header.jsp" />
    <div id="wrapper">
        <h3>Login with Username and Password</h3>
        
        <c:if test="${param.containsKey('error')}">
            <span id="loginError">
            <t:messagesPanel messagesType="error"
                messagesAttributeName="SPRING_SECURITY_LAST_EXCEPTION" />
            </span>
        </c:if>
        
        <form:form action="${f:h(pageContext.request.contextPath)}/login">
            <table>
                <tr>
                    <td><label for="username">User:</label></td>
                    <td><input type="text" id="username" name="username" value='demo'>(demo)</td>
                </tr>
                <tr>
                    <td><label for="password">Password:</label></td>
                    <td><input type="password" id="password" name="password" value='demo'>(demo)</td>
                </tr>
                <tr>
                    <td>&nbsp;</td>
                    <td><input id="login" name="submit" type="submit" value="login" /></td>
                </tr>
                <tr>
                    <td>&nbsp;</td>
                    <td><a id="create" href="${f:h(pageContext.request.contextPath)}/accounts/create?form">Create new account</a></td>
                </tr>
                <tr>
                    <td>&nbsp;</td>
                    <td><a id="forgotten" href="${f:h(pageContext.request.contextPath)}/reissue/create?form">I've forgotten my password</a></td>
                </tr>
            </table>
        </form:form>
    </div>
        <hr>
        <p style="text-align: center; background: #e5eCf9;">Copyright &copy; 20XX CompanyName</p>
    </div>
</body>
</html>