<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>Home</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/app/css/styles.css">
</head>

<!-- (1) -->
<sec:authentication property="principal.account" var="account" />
<body>
    <div id="wrapper">
        <h1>Hello world!</h1>
        
    <p>The time on the server is ${serverTime}.</p>

    <p>Welcome ${f:h(account.firstName)} ${f:h(account.lastName)} !!</p>

    <p>
        <form:form action="${pageContext.request.contextPath}/logout">
            <button type="submit">Logout</button>
        </form:form>
    </p>

    <ul>
        <li><a href="${pageContext.request.contextPath}/account">view account</a></li>
    </ul>
    </div>
</body>
</html>
