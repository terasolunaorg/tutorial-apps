<!DOCTYPE html>
<html class="no-js">
<head>
<meta charset="utf-8" />
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
<meta name="viewport" content="width=device-width" />
<script type="text/javascript">
    
</script>
<c:set var="titleKey" value="title.welcome.home" />
<title><spring:message code="${titleKey}" text="secure-login" /></title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/app/css/styles.css">
</head>
<body>
    <div class="container">
    <jsp:include page="../layout/header.jsp"  />
        <div id="wrapper">
        <span id="expiredMessage"> <t:messagesPanel />
        </span>
            <h1 id="title">Hello world!</h1>
        
        <p>Welcome ${f:h(account.firstName)} ${f:h(account.lastName)}</p>

        <c:if test="${!empty lastLoginDate}">
            <p id="lastLogin">Last login date is ${f:h(lastLoginDate)}.</p>
        </c:if>

        <div>
            <a id="info" href="${f:h(pageContext.request.contextPath)}/accounts">
                Account Information
            </a>
        </div>

        <sec:authorize url="/unlock">
        <div>
            <a id="unlock" href="${f:h(pageContext.request.contextPath)}/unlock?form">
                Unlock Account
            </a>
        </div>
        </sec:authorize>

        <form:form action="${f:h(pageContext.request.contextPath)}/logout">
            <button id="logout">Logout</button>
        </form:form>
        </div>
        <hr>
        <p style="text-align: center; background: #e5eCf9;">Copyright &copy; 20XX CompanyName</p>
    </div>
</body>
</html>