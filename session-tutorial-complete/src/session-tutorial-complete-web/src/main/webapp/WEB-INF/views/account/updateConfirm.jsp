<div>

    <form:form action="${pageContext.request.contextPath}/account/update" method="post">

        <h3>Your account will be updated with below information. Please push "update" button if it's OK.</h3>
        <table>
            <tr>
                <td><label for="name">name</label></td>
                <td id="name">${f:h(accountUpdateForm.name)}</td>
            </tr>
            <tr>
                <td><label for="email">e-mail</label></td>
                <td id="email">${f:h(accountUpdateForm.email)}</td>
            </tr>
            <tr>
                <td><label for="birthday">birthday</label></td>
                <td id="birthday"><fmt:formatDate value="${accountUpdateForm.birthday}" pattern="yyyy-MM-dd" /></td>
            </tr>
            <tr>
                <td><label for="zip">zip</label></td>
                <td id="zip">${f:h(accountUpdateForm.zip)}</td>
            </tr>
            <tr>
                <td><label for="address">address</label></td>
                <td id="address">${f:h(accountUpdateForm.address)}</td>
            </tr>
            <tr>
                <td><label for="cardNumber">your card number</label></td>
                <td id="cardNumber">****-****-****-${f:h(accountUpdateForm.lastFourOfCardNumber)}</td>
            </tr>
            <tr>
                <td><label for="cardExpirationDate">expiration date of your card</label></td>
                <td id="cardExpirationDate"><fmt:formatDate value="${accountUpdateForm.cardExpirationDate}" pattern="yyyy-MM" /></td>
            </tr>
            <tr>
                <td><label for="cardSecurityCode">security code of your card</label></td>
                <td id="cardSecurityCode">${f:h(accountUpdateForm.cardSecurityCode)}</td>
            </tr>
            <tr>
                <td>&nbsp;</td>
                <td><input type="submit" name="redoForm2" id="back" value="back" />
                    <input type="submit" id="update" value="update" />
                </td>
            </tr>
        </table>
    </form:form>

    <form method="get" action="${pageContext.request.contextPath}/account/update">
        <input type="submit" name="home" id="home" value="home" />
    </form>
</div>
