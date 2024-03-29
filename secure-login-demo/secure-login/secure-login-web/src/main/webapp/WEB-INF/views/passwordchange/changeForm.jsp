<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8" />
<title>Change Password</title>
<link rel="stylesheet" href="${f:h(pageContext.request.contextPath)}/resources/app/css/styles.css" />
</head>
<body>
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
</body>
</html>