<!DOCTYPE html>
<html class="no-js">
<head>
<meta charset="utf-8" />
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
<meta name="viewport" content="width=device-width" />
<script type="text/javascript">
    
</script>
<c:set var="titleKey" value="title.account.view" />
<title><spring:message code="${titleKey}" text="secure-login" /></title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/app/css/styles.css">
</head>
<body>
    <div class="container">
    <jsp:include page="../layout/header.jsp" />
    <div id="wrapper">
        <h1>Account Information</h1>
        <img src="${f:h(pageContext.request.contextPath)}/accounts/image" width="100" height="100"/>
        <table>
            <tr>
                <th>Username</th>
                <td>${f:h(account.username)}</td>
            </tr>
            <tr>
                <th>First name</th>
                <td>${f:h(account.firstName)}</td>
            </tr>
            <tr>
                <th>Last name</th>
                <td>${f:h(account.lastName)}</td>
            </tr>
            <tr>
                <th>E-Mail</th>
                <td>${f:h(account.email)}</td>
            </tr>
            <tr>
                <th>URL</th>
                <td>${f:link(account.url)}</td>
            </tr>
            <tr>
                <th>Profile</th>
                <td>${f:br(f:h(account.profile))}</td>
            </tr>
        </table>

        <a id="changePassword" href="${f:h(pageContext.request.contextPath)}/password?form">Change Password</a>
        <br>
        <a href="${f:h(pageContext.request.contextPath)}/">Back to Top</a>
    </div>
        <hr>
        <p style="text-align: center; background: #e5eCf9;">Copyright &copy; 20XX CompanyName</p>
    </div>
</body>
</html>