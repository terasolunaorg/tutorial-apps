<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8" />
<title>Account was successfully unlocked</title>
<link rel="stylesheet" href="${f:h(pageContext.request.contextPath)}/resources/app/css/styles.css" />
</head>
<body>
    <div id="wrapper">
        <h1>${f:h(username)}'s account was successfully unlocked.</h1>
        <a href="${f:h(pageContext.request.contextPath)}/">go to Top</a>
    </div>
</body>
</html>
