<!DOCTYPE html>
<html class="no-js">
<head>
<meta charset="utf-8" />
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
<meta name="viewport" content="width=device-width" />
<script type="text/javascript">
    
</script>
<c:set var="titleKey" value="title.account.updateFinish" />
<title><spring:message code="${titleKey}" text="session-tutorial-complete" /></title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/app/css/styles.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/vendor/bootstrap-3.0.0/css/bootstrap.css"
    media="screen, projection">
</head>
<body>
    <div class="container">
    <jsp:include page="../layout/header.jsp" />
<div>

    <h3>Your account has updated.</h3>
    <table>
        <tr>
            <td><label for="name">name</label></td>
            <td id="name">${f:h(account.name)}</td>
        </tr>
        <tr>
            <td><label for="email">e-mail</label></td>
            <td id="email">${f:h(account.email)}</td>
        </tr>
        <tr>
            <td><label for="birthday">birthday</label></td>
            <td id="birthday"><fmt:formatDate value="${account.birthday}" pattern="yyyy-MM-dd" /></td>
        </tr>
        <tr>
            <td><label for="zip">zip</label></td>
            <td id="zip">${f:h(account.zip)}</td>
        </tr>
        <tr>
            <td><label for="address">address</label></td>
            <td id="address">${f:h(account.address)}</td>
        </tr>
        <tr>
            <td><label for="cardNumber">your card number</label></td>
            <td id="cardNumber">****-****-****-${f:h(account.lastFourOfCardNumber)}</td>
        </tr>
        <tr>
            <td><label for="cardExpirationDate">expiration date of your card</label></td>
            <td id="cardExpirationDate"><fmt:formatDate value="${account.cardExpirationDate}" pattern="yyyy-MM" /></td>
        </tr>
        <tr>
            <td><label for="cardSecurityCode">security code of your card</label></td>
            <td id="cardSecurityCode">${f:h(account.cardSecurityCode)}</td>
        </tr>
    </table>

    <form method="get" action="${pageContext.request.contextPath}/account/update">
        <input type="submit" name="home" id="home" value="home" />
    </form>

</div>
        <hr>
        <p style="text-align: center; background: #e5eCf9;">Copyright &copy; 20XX CompanyName</p>
    </div>
</body>
</html>
