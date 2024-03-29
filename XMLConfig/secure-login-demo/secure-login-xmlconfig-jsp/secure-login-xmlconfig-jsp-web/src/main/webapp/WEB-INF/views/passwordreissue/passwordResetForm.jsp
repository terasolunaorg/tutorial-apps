<!DOCTYPE html>
<html class="no-js">
<head>
<meta charset="utf-8" />
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
<meta name="viewport" content="width=device-width" />
<script type="text/javascript">
    
</script>
<c:set var="titleKey" value="title.passwordreissue.createResetForm" />
<title><spring:message code="${titleKey}" text="secure-login" /></title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/app/css/styles.css">
</head>
<body>
    <div class="container">
    <jsp:include page="../layout/header.jsp" />
    <div id="wrapper">
        <h1>Reset Password</h1>
        <t:messagesPanel />
        <form:form
            action="${f:h(pageContext.request.contextPath)}/reissue/resetpassword"
            method="POST" modelAttribute="passwordResetForm">
            <table>
                <tr>
                    <th><form:label path="username">Username</form:label></th>
                    <td>${f:h(passwordResetForm.username)} <form:hidden
                            path="username" value="${f:h(passwordResetForm.username)}" />
                    </td>
                    <td></td>
                </tr>
                <form:hidden path="token" value="${f:h(passwordResetForm.token)}" />
                <tr>
                    <th><form:label path="secret" cssErrorClass="error-label">Secret</form:label>
                    </th>
                    <td><form:password path="secret" cssErrorClass="error-input" /></td>
                    <td><form:errors path="secret" cssClass="error-messages" /></td>
                </tr>
                <tr>
                    <th><form:label path="newPassword" cssErrorClass="error-label">New password</form:label>
                    </th>
                    <td><form:password path="newPassword"
                            cssErrorClass="error-input" /></td>
                    <td><form:errors path="newPassword" cssClass="error-messages" /></td>
                </tr>
                <tr>
                    <th><form:label path="confirmNewPassword"
                            cssErrorClass="error-label">New password(Confirm)</form:label></th>
                    <td><form:password path="confirmNewPassword"
                            cssErrorClass="error-input" /></td>
                    <td><form:errors path="confirmNewPassword"
                            cssClass="error-messages" /></td>
                </tr>
            </table>

            <input id="submit" type="submit" value="Reset password" />
        </form:form>
    </div>
        <hr>
        <p style="text-align: center; background: #e5eCf9;">Copyright &copy; 20XX CompanyName</p>
    </div>
</body>
</html>