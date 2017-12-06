<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>Home</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/app/css/styles.css">
</head>
<body>
    <div id="wrapper">
        <h1>Account Information</h1>
        <table>
            <tr>
                <th>Username</th>
                <td>${f:h(account.username)}</td>
            </tr>
            <tr>
                <th>First name</th>
                <td>${f:h(account.firstName)}</td>
            </tr>
            <tr>
                <th>Last name</th>
                <td>${f:h(account.lastName)}</td>
            </tr>
        </table>
    </div>
</body>
</html>

