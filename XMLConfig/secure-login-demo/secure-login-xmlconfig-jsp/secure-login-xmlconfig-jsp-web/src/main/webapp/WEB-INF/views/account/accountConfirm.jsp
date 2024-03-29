<!DOCTYPE html>
<html class="no-js">
<head>
<meta charset="utf-8" />
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
<meta name="viewport" content="width=device-width" />
<script type="text/javascript">
    
</script>
<c:set var="titleKey" value="title.account.accountConfirm" />
<title><spring:message code="${titleKey}" text="secure-login" /></title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/app/css/styles.css">
</head>
<body>
    <div class="container">
    <jsp:include page="../layout/header.jsp" />
    <div id="wrapper">
        <h1>Create Account</h1>
        <form:form
            action="${f:h(pageContext.request.contextPath)}/accounts/create" method="POST"
            modelAttribute="accountCreateForm">
            <table>
                <tr>
                    <th>Username</th>
                    <td>${f:h(accountCreateForm.username)}</td>
                    <form:hidden path="username"/>
                </tr>
                <tr>
                    <th>First name</th>
                    <td>${f:h(accountCreateForm.firstName)}</td>
                    <form:hidden path="firstName"/>
                </tr>
                <tr>
                    <th>Last name</th>
                    <td>${f:h(accountCreateForm.lastName)}</td>
                    <form:hidden path="lastName"/>
                </tr>
                <tr>
                    <th>E-mail</th>
                    <td>${f:h(accountCreateForm.email)}</td>
                    <form:hidden path="email"/>
                </tr>
                    <form:hidden path="confirmEmail"/>
                <tr>
                    <th>URL</th>
                    <td>${f:h(accountCreateForm.url)}</td>
                    <form:hidden path="url"/>
                </tr>
                <tr>
                    <th>Image</th>
                    <td>${f:h(accountCreateForm.image.originalFilename)}</td>
                </tr>
                <tr>
                    <th>Profile</th>
                    <td>${f:br(f:h(accountCreateForm.profile))}</td>
                    <form:hidden path="profile"/>
                </tr>
                <form:hidden path="imageId"/>
            </table>
            <input type="submit" id="redo" name="redo" value="Back" />
            <input type="submit" id="create" value="Create new account"/>
        </form:form>
    </div>
        <hr>
        <p style="text-align: center; background: #e5eCf9;">Copyright &copy; 20XX CompanyName</p>
    </div>
</body>
</html>