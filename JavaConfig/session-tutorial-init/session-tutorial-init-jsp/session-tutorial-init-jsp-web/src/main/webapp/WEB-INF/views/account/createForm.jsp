<!DOCTYPE html>
<html class="no-js">
<head>
<meta charset="utf-8" />
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
<meta name="viewport" content="width=device-width" />
<script type="text/javascript">
    
</script>
<c:set var="titleKey" value="title.account.createForm" />
<title><spring:message code="${titleKey}" text="session-tutorial-complete" /></title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/app/css/styles.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/vendor/bootstrap-3.0.0/css/bootstrap.css"
    media="screen, projection">
</head>
<body>
    <div class="container">
    <jsp:include page="../layout/header.jsp" />
<div>

    <form:form action="${pageContext.request.contextPath}/account/create" method="post" modelAttribute="accountCreateForm">

        <h2>Account Create Page</h2>
        <table>
            <tr>
                <td><form:label path="name" cssErrorClass="error-label">name</form:label></td>
                <td><form:input path="name" cssErrorClass="error-input" />
                    <form:errors path="name" cssClass="error-messages" />
                </td>
            </tr>
            <tr>
                <td><form:label path="email" cssErrorClass="error-label">e-mail</form:label></td>
                <td><form:input path="email" cssErrorClass="error-input" />
                    <form:errors path="email" cssClass="error-messages" />
                </td>
            </tr>
            <tr>
                <td><form:label path="password" cssErrorClass="error-label">password</form:label></td>
                <td><form:password path="password" cssErrorClass="error-input" />
                    <form:errors path="password" cssClass="error-messages" />
                </td>
            </tr>
            <tr>
                <td><form:label path="confirmPassword" cssErrorClass="error-label">password (confirm)</form:label></td>
                <td><form:password path="confirmPassword" cssErrorClass="error-input" />
                    <form:errors path="confirmPassword" cssClass="error-messages" />
                </td>
            </tr>
            <tr>
                <td><form:label path="birthday" cssErrorClass="error-label">birthday</form:label></td>
                <td><fmt:formatDate value="${accountCreateForm.birthday}" pattern="yyyy-MM-dd" var="formattedBirthday" />
                    <input type="date" id="birthday" name="birthday" value="${formattedBirthday}">
                    <form:errors path="birthday" cssClass="error-messages" />
                </td>
            </tr>
            <tr>
                <td><form:label path="zip" cssErrorClass="error-label">zip</form:label></td>
                <td><form:input path="zip" cssErrorClass="error-input" />
                    <form:errors path="zip" cssClass="error-messages" />
                </td>
            </tr>
            <tr>
                <td><form:label path="address" cssErrorClass="error-label">address</form:label></td>
                <td><form:input path="address" cssErrorClass="error-input" />
                    <form:errors path="address" cssClass="error-messages" />
                </td>
            </tr>
            <tr>
                <td>&nbsp;</td>
                <td><input type="submit" name="confirm" id="confirm" value="confirm" /></td>
            </tr>
        </table>
    </form:form>

    <form method="get" action="${pageContext.request.contextPath}/account/create">
        <input type="submit" name="home" id="home" value="Login page" />
    </form>
</div>
        <hr>
        <p style="text-align: center; background: #e5eCf9;">Copyright &copy; 20XX CompanyName</p>
    </div>
</body>
</html>
