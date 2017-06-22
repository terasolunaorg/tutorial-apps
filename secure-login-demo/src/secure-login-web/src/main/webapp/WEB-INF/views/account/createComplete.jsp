<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8" />
<title>Create Account</title>
<link rel="stylesheet"
	href="${f:h(pageContext.request.contextPath)}/resources/app/css/styles.css" />
</head>
<body>
	<div id="wrapper">
		<h1>Welcome ${f:h(firstName)} ${f:h(lastName)}!</h1>
		<h3>Your initial password is <span id=password>${f:h(password)}</span>. Please login and change it.</h3>
		<a href="${f:h(pageContext.request.contextPath)}/login">Back to login page</a>
	</div>
</body>
</html>