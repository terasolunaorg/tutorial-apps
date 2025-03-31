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
