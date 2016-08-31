<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8" />
<title>Account Information</title>
<link rel="stylesheet"
	href="${f:h(pageContext.request.contextPath)}/resources/app/css/styles.css" />
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

		<form:form
			action="${f:h(pageContext.request.contextPath)}/password?form">
			<button id="changePassword">Change Password</button>
		</form:form>
		<a href="${f:h(pageContext.request.contextPath)}/">Back to Top</a>
	</div>
</body>
</html>