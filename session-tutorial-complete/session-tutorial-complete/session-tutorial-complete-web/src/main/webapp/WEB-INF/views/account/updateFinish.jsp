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
