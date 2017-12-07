<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8" />
<title>Account Update Page</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/app/css/styles.css">
</head>
<body>
    <div class="container">

        <h3>Your account has updated.</h3>
        <table>
            <tr>
                <td><label for="name">name</label></td>
                <td><span id="name">${f:h(account.name)}</span></td>
            </tr>
            <tr>
                <td><label for="email">e-mail</label></td>
                <td><span id="email">${f:h(account.email)}</span></td>
            </tr>
            <tr>
                <td><label for="birthday">birthday</label></td>
                <td><span id="birthday"><fmt:formatDate
                            value="${account.birthday}" pattern="yyyy-MM-dd" /></span></td>
            </tr>
            <tr>
                <td><label for="zip">zip</label></td>
                <td><span id="zip">${f:h(account.zip)}</span></td>
            </tr>
            <tr>
                <td><label for="address">address</label></td>
                <td><span id="address">${f:h(account.address)}</span></td>
            </tr>
            <tr>
                <td><label for="cardNumber">your card number</label></td>
                <td><span id="cardNumber">****-****-****-${f:h(account.lastFourOfCardNumber)}</span></td>
            </tr>
            <tr>
                <td><label for="cardExpirationDate">expiration date of
                        your card</label></td>
                <td><span id="cardExpirationDate"><fmt:formatDate
                            value="${account.cardExpirationDate}" pattern="yyyy-MM" /></span></td>
            </tr>
            <tr>
                <td><label for="cardSecurityCode">security code of your
                        card</label></td>
                <td><span id="cardSecurityCode">${f:h(account.cardSecurityCode)}</span></td>
            </tr>
        </table>

        <form method="get"
            action="${pageContext.request.contextPath}/account/update">
            <input type="submit" name="home" id="home" value="home" />
        </form>

    </div>
</body>
</html>