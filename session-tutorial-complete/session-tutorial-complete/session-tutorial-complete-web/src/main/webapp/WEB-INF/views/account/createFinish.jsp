<!DOCTYPE html>
<html class="no-js">
<head>
<meta charset="utf-8" />
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
<meta name="viewport" content="width=device-width" />
<script type="text/javascript">
    
</script>
<c:set var="titleKey" value="title.account.createFinish" />
<title><spring:message code="${titleKey}" text="session-tutorial-complete" /></title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/app/css/styles.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/vendor/bootstrap-3.0.0/css/bootstrap.css"
    media="screen, projection">
</head>
<body>
    <div class="container">
    <jsp:include page="../layout/header.jsp" />
<div>

    <h3>Your account has created.</h3>
    <table>
        <tr>
            <td><label for="name">name</label></td>
            <td id="name">${f:h(accountCreateForm.name)}</td>
        </tr>
        <tr>
            <td><label for="email">e-mail</label></td>
            <td id="email">${f:h(accountCreateForm.email)}</td>
        </tr>
        <tr>
            <td><label for="password">password</label></td>
            <td id="password">****</td>
        </tr>
        <tr>
            <td><label for="birthday">birthday</label></td>
            <td id="birthday"><fmt:formatDate value="${accountCreateForm.birthday}" pattern="yyyy-MM-dd" /></td>
        </tr>
        <tr>
            <td><label for="zip">zip</label></td>
            <td id="zip">${f:h(accountCreateForm.zip)}</td>
        </tr>
        <tr>
            <td><label for="address">address</label></td>
            <td id="address">${f:h(accountCreateForm.address)}</td>
        </tr>
    </table>

    <form method="get" action="${pageContext.request.contextPath}/account/create">
        <input type="submit" id="home" name="home" value="Login page" />
    </form>

</div>
        <hr>
        <p style="text-align: center; background: #e5eCf9;">Copyright &copy; 20XX CompanyName</p>
    </div>
</body>
</html>
