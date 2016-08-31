<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8" />
<title>Reset Password</title>
<link rel="stylesheet"
	href="${f:h(pageContext.request.contextPath)}/resources/app/css/styles.css" />
</head>
<body>
	<div id="wrapper">
		<h1>Reset Password</h1>
		<t:messagesPanel />
		<form:form
			action="${f:h(pageContext.request.contextPath)}/reissue/resetpassword"
			method="POST" modelAttribute="passwordResetForm">
			<table>
				<tr>
					<th><form:label path="username">Username</form:label></th>
					<td>${f:h(passwordResetForm.username)} <form:hidden
							path="username" value="${f:h(passwordResetorm.username)}" />
					</td>
					<td></td>
				</tr>
				<form:hidden path="token" value="${f:h(passwordResetForm.token)}" />
				<tr>
					<th><form:label path="secret" cssErrorClass="error-label">Secret</form:label>
					</th>
					<td><form:password path="secret" cssErrorClass="error-input" /></td>
					<td><form:errors path="secret" cssClass="error-messages" /></td>
				</tr>
				<tr>
					<th><form:label path="newPassword" cssErrorClass="error-label">New password</form:label>
					</th>
					<td><form:password path="newPassword"
							cssErrorClass="error-input" /></td>
					<td><form:errors path="newPassword" cssClass="error-messages" /></td>
				</tr>
				<tr>
					<th><form:label path="confirmNewPassword"
							cssErrorClass="error-label">New password(Confirm)</form:label></th>
					<td><form:password path="confirmNewPassword"
							cssErrorClass="error-input" /></td>
					<td><form:errors path="confirmNewPassword"
							cssClass="error-messages" /></td>
				</tr>
			</table>

			<input id="submit" type="submit" value="Reset password" />
		</form:form>
	</div>
</body>
</html>