<!DOCTYPE html>
<html class="no-js">
<head>
<meta charset="utf-8" />
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
<meta name="viewport" content="width=device-width" />
<script type="text/javascript">
    
</script>
<c:set var="titleKey" value="title.account.updateForm2" />
<title><spring:message code="${titleKey}" text="session-tutorial-complete" /></title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/app/css/styles.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/vendor/bootstrap-3.0.0/css/bootstrap.css"
    media="screen, projection">
</head>
<body>
    <div class="container">
    <jsp:include page="../layout/header.jsp" />
<div>

    <form:form action="${pageContext.request.contextPath}/account/update" method="post" modelAttribute="accountUpdateForm">

        <h2>Account Update Page 2/2</h2>
        <table>
            <tr>
                <td><form:label path="cardNumber" cssErrorClass="error-label">your card number</form:label></td>
                <td><form:input path="cardNumber" cssErrorClass="error-input" />
                    <form:errors path="cardNumber" cssClass="error-messages" />
                </td>
            </tr>
            <tr>
                <td><form:label path="cardExpirationDate" cssErrorClass="error-label">expiration date of your card</form:label></td>
                <td><fmt:formatDate value="${accountUpdateForm.cardExpirationDate}" pattern="yyyy-MM" var="formattedCardExpirationDate" />
                    <input type="month" name="cardExpirationDate" id="cardExpirationDate" value="${formattedCardExpirationDate}">
                    <form:errors path="cardExpirationDate" cssClass="error-messages" />
                </td>
            </tr>
            <tr>
                <td><form:label path="cardSecurityCode" cssErrorClass="error-label">security code of your card</form:label></td>
                <td><form:input path="cardSecurityCode" cssErrorClass="error-input" />
                    <form:errors path="cardSecurityCode" cssClass="error-messages" />
                </td>
            </tr>
            <tr>
                <td>&nbsp;</td>
                <td><input type="submit" name="redoForm1" id="back" value="back" />
                    <input type="submit" name="confirm" id="confirm" value="confirm" />
                </td>
            </tr>
        </table>
    </form:form>

    <form method="get" action="${pageContext.request.contextPath}/account/update">
        <input type="submit" name="home" id="home" value="home" />
    </form>
</div>
        <hr>
        <p style="text-align: center; background: #e5eCf9;">Copyright &copy; 20XX CompanyName</p>
    </div>
</body>
</html>
