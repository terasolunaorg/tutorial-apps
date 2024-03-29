<!DOCTYPE html>
<html class="no-js">
<head>
<meta charset="utf-8" />
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
<meta name="viewport" content="width=device-width" />
<script type="text/javascript">
    
</script>
<c:set var="titleKey" value="title.passwordchange.changeForm" />
<title><spring:message code="${titleKey}" text="secure-login" /></title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/app/css/styles.css">
</head>
<body>
    <div class="container">
    <jsp:include page="../layout/header.jsp" />
    <div id="wrapper">
        <h1>Change Password</h1>
        <form:form action="${f:h(pageContext.request.contextPath)}/password"
            method="POST" modelAttribute="passwordChangeForm">
            <table>
                <tr>
                    <th><form:label path="username">Username</form:label></th>
                    <td>${f:h(account.username)} <form:hidden path="username"
                            value="${f:h(account.username)}" />
                    </td>
                    <td></td>
                </tr>
                <tr>
                    <th><form:label path="oldPassword" cssErrorClass="error-label">Old password</form:label>
                    </th>
                    <td><form:password path="oldPassword"
                            cssErrorClass="error-input" /></td>
                    <td><form:errors path="oldPassword" cssClass="error-messages" /></td>
                </tr>
                <tr>
                    <th><form:label path="newPassword" cssErrorClass="error-label">New password</form:label>
                    </th>
                    <td><form:password path="newPassword"
                            cssErrorClass="error-input" /></td>
                    <td><form:errors path="newPassword" cssClass="error-messages"/></td>
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

            <input id="submit" type="submit" value="Change password" />
        </form:form>
    </div>
        <hr>
        <p style="text-align: center; background: #e5eCf9;">Copyright &copy; 20XX CompanyName</p>
    </div>
</body>
</html>