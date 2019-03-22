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

    <h3>Your order has been accepted</h3>
    <table>
        <tr>
            <td><label for="orderNumber">order number</label></td>
            <td id="orderNumber">${f:h(order.id)}</td>
        </tr>
        <tr>
            <td><label for="orderDate">order date</label></td>
            <td id="orderDate"><fmt:formatDate value="${order.orderDate}" pattern="yyyy-MM-dd　hh:mm:ss" /></td>
        </tr>
    </table>
    <table>
        <tr>
            <th>Name</th>
            <th>Price</th>
            <th>Quantity</th>
        </tr>
        <c:forEach items="${order.orderLines}" var="orderLine" varStatus="status">
            <tr>
                <td id="itemName${status.index}">${f:h(orderLine.goods.name)}</td>
                <td id="itemPrice${status.index}"><fmt:formatNumber value="${orderLine.goods.price}" type="CURRENCY" currencySymbol="&yen;" maxFractionDigits="0" /></td>
                <td id="itemQuantity${status.index}">${f:h(orderLine.quantity)}</td>
            </tr>
        </c:forEach>
        <tr>
            <td>Total</td>
            <td id="totalPrice"><fmt:formatNumber value="${f:h(order.totalAmount)}" type="CURRENCY" currencySymbol="&yen;" maxFractionDigits="0" /></td>
            <td></td>
        </tr>
    </table>
</div>
<div>
    <form method="get" action="${pageContext.request.contextPath}/goods">
        <input type="submit" id="home" value="home" />
    </form>
</div>
