<!DOCTYPE html>
<html class="no-js">
<head>
<meta charset="utf-8" />
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
<meta name="viewport" content="width=device-width" />
<script type="text/javascript">
    
</script>
<c:set var="titleKey" value="title.goods.showGoodsDetail" />
<title><spring:message code="${titleKey}" text="session-tutorial-complete" /></title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/app/css/styles.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/vendor/bootstrap-3.0.0/css/bootstrap.css"
    media="screen, projection">
</head>
<body>
    <div class="container">
    <jsp:include page="../layout/header.jsp" />
<sec:authentication property="principal" var="userDetails" />
<div style="display: inline-flex">
    welcome&nbsp;&nbsp; <span id="userName">${f:h(userDetails.account.name)}</span>
    <form:form method="post" action="${pageContext.request.contextPath}/logout">
        <input type="submit" id="logout" value="logout" />
    </form:form>
    <form method="get" action="${pageContext.request.contextPath}/account/update">
        <input type="submit" name="form1" id="updateAccount" value="Account Update" />
    </form>
</div>
<br>
<br>

<div>
    <table>
        <tr>
            <th>Name</th>
            <td id="name">${f:h(goods.name)}</td>
        </tr>
        <tr>
            <th>Price</th>
            <td id="price"><fmt:formatNumber value="${f:h(goods.price)}" type="CURRENCY" currencySymbol="&yen;" maxFractionDigits="0" /></td>
        </tr>
        <tr>
            <th>Description</th>
            <td id="description">${f:h(goods.description)}</td>
        </tr>
    </table>

    <form method="get" action="${pageContext.request.contextPath}/goods">
        <input type="submit" id="home" value="home" />
    </form>
</div>
        <hr>
        <p style="text-align: center; background: #e5eCf9;">Copyright &copy; 20XX CompanyName</p>
    </div>
</body>
</html>

