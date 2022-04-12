<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8" />
<title>Password Reissue URL is generated</title>
<link rel="stylesheet" href="${f:h(pageContext.request.contextPath)}/resources/app/css/styles.css" />
</head>
<body>
    <div id="wrapper">
        <h1>Your Password Reissue URL was successfully generated.</h1>
        The URL was sent to your registered E-mail address.<br /> Please
        access the URL and enter the secret shown below.
        <h3>Secret : <span id=secret>${f:h(secret)}</span></h3>
    </div>
</body>
</html>