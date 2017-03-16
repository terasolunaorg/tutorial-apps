<!DOCTYPE html>
<html>
<head>
<title>Login Page</title>
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/app/css/styles.css">
</head>
<body>
	<div id="wrapper">
		<h3>Login with Username and Password</h3>

		<c:if test="${f:h(param.error)}">
			<t:messagesPanel messagesType="error"
				messagesAttributeName="SPRING_SECURITY_LAST_EXCEPTION" />
		</c:if>

		<form:form action="${pageContext.request.contextPath}/authenticate">
			<table>
				<tr>
					<td><label for="email">E-mail</label></td>
					<td><label for="password">Password</label></td>

				</tr>
				<tr>
					<td><input type="text" id="email" name="email"></td>
					<td><input type="password" id="password" name="password" /></td>
				</tr>
				<tr>
					<td>&nbsp;</td>
					<td><input name="submit" type="submit" id="login" value="Login" /></td>
				</tr>
			</table>
		</form:form>
		Account create page is <a id="createAccount" href="${pageContext.request.contextPath}/account/create?form">here</a>
	</div>
</body>
</html>