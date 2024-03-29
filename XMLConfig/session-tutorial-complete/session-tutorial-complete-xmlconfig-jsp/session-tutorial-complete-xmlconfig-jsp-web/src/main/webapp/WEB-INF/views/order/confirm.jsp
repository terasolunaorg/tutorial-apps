<!DOCTYPE html>
<html class="no-js">
<head>
<meta charset="utf-8" />
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
<meta name="viewport" content="width=device-width" />
<script type="text/javascript">
    
</script>
<c:set var="titleKey" value="title.order.confirm" />
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
    <form:form method="post"
        action="${pageContext.request.contextPath}/logout">
        <input type="submit" id="logout" value="logout" />
    </form:form>
    <form method="get"
        action="${pageContext.request.contextPath}/account/update">
        <input type="submit" name="form1" id="updateAccount"
            value="Account Update" />
    </form>
</div>
<br>
<br>

<div>
    <spring:eval var="cart" expression="@cart" />

    <h3>Below items will be ordered. Please push "order" button if it's OK.</h3>
    <table>
        <tr>
            <th>Name</th>
            <th>Price</th>
            <th>Quantity</th>
        </tr>
        <c:forEach items="${cart.cartItems}" var="cartItem" varStatus="status">
            <tr>
                <td id="itemName${status.index}">${f:h(cartItem.goods.name)}</td>
                <td id="itemPrice${status.index}"><fmt:formatNumber value="${cartItem.goods.price}" type="CURRENCY" currencySymbol="&yen;" maxFractionDigits="0" /></td>
                <td id="itemQuantity${status.index}">${f:h(cartItem.quantity)}</td>
            </tr>
        </c:forEach>
        <tr>
            <td>Total</td>
            <td id="totalPrice"><fmt:formatNumber value="${f:h(cart.totalAmount)}" type="CURRENCY" currencySymbol="&yen;" maxFractionDigits="0" /></td>
            <td></td>
        </tr>
    </table>

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
            <td><label for="zip">zip</label></td>
            <td id="zip">${f:h(account.zip)}</td>
        </tr>
        <tr>
            <td><label for="address">address</label></td>
            <td id="address">${f:h(account.address)}</td>
        </tr>
        <tr>
            <%-- (1) --%>
            <td>payment</td>
            <td id="payment">
                <c:choose>
                    <c:when test="${empty account.cardNumber}">cash</c:when>
                    <c:otherwise>card (card number : ****-****-****-${f:h(account.lastFourOfCardNumber)})</c:otherwise>
                </c:choose>
            </td>
        </tr>
    </table>
</div>
<div style="display: inline-flex">
    <form:form method="post" action="${pageContext.request.contextPath}/order">
        <input type="hidden" name="signature" value="${f:h(signature)}" />
        <input type="submit" id="order" value="order" />
    </form:form>
    <form method="get" action="${pageContext.request.contextPath}/cart">
        <input type="submit" id="back" value="back" />
    </form>
</div>
<div>
    <form method="get" action="${pageContext.request.contextPath}/goods">
        <input type="submit" id="home" value="home" />
    </form>
</div>
        <hr>
        <p style="text-align: center; background: #e5eCf9;">Copyright &copy; 20XX CompanyName</p>
    </div>
</body>
</html>
