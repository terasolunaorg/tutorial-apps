<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8" />
<title>Account Update Page</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/app/css/styles.css">
</head>
<body>
    <div class="container">

        <form:form action="${pageContext.request.contextPath}/account/update"
            method="post">

            <h3>Your account will be updated with below information. Please
                push "update" button if it's OK.</h3>
            <table>
                <tr>
                    <td><label for="name">name</label></td>
                    <td><span id="name">${f:h(accountUpdateForm.name)}</span></td>
                </tr>
                <tr>
                    <td><label for="email">e-mail</label></td>
                    <td><span id="email">${f:h(accountUpdateForm.email)}</span></td>
                </tr>
                <tr>
                    <td><label for="birthday">birthday</label></td>
                    <td><span id="birthday"><fmt:formatDate
                                value="${accountUpdateForm.birthday}" pattern="yyyy-MM-dd" /></span></td>
                </tr>
                <tr>
                    <td><label for="zip">zip</label></td>
                    <td><span id="zip">${f:h(accountUpdateForm.zip)}</span></td>
                </tr>
                <tr>
                    <td><label for="address">address</label></td>
                    <td><span id="address">${f:h(accountUpdateForm.address)}</span></td>
                </tr>
                <tr>
                    <td><label for="cardNumber">your card number</label></td>
                    <td><span id="cardNumber">****-****-****-${f:h(accountUpdateForm.lastFourOfCardNumber)}</span></td>
                </tr>
                <tr>
                    <td><label for="cardExpirationDate">expiration date of
                            your card</label></td>
                    <td><span id="cardExpirationDate"><fmt:formatDate
                                value="${accountUpdateForm.cardExpirationDate}"
                                pattern="yyyy-MM" /></span></td>
                </tr>
                <tr>
                    <td><label for="cardSecurityCode">security code of
                            your card</label></td>
                    <td><span id="cardSecurityCode">${f:h(accountUpdateForm.cardSecurityCode)}</span></td>
                </tr>
                <tr>
                    <td>&nbsp;</td>
                    <td><input type="submit" name="redoForm2" id="back"
                        value="back" /><input type="submit" id="update" value="update" /></td>
                </tr>
            </table>
        </form:form>


        <form method="get"
            action="${pageContext.request.contextPath}/account/update">
            <input type="submit" name="home" id="home" value="home" />
        </form>
    </div>
</body>
</html>