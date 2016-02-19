<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8" />
<title>Account Create Page</title>
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/app/css/styles.css">
</head>
<body>
	<div class="container">

		<form:form action="${pageContext.request.contextPath}/account/create"
			method="post">

			<h3>Your account will be created with below information. Please
				push "create" button if it's OK.</h3>
			<table>
				<tr>
					<td><label for="name">name</label></td>
					<td><span id="name">${f:h(accountCreateForm.name)}</span></td>
				</tr>
				<tr>
					<td><label for="email">e-mail</label></td>
					<td><span id="email">${f:h(accountCreateForm.email)}</span></td>
				</tr>
				<tr>
					<td><label for="password">password</label></td>
					<td><span id="password">****</span></td>
				</tr>
				<tr>
					<td><label for="birthday">birthday</label></td>
					<td><span id="birthday"><fmt:formatDate
								value="${accountCreateForm.birthday}" pattern="yyyy-MM-dd" /></span></td>
				</tr>
				<tr>
					<td><label for="zip">zip</label></td>
					<td><span id="zip">${f:h(accountCreateForm.zip)}</span></td>
				</tr>
				<tr>
					<td><label for="address">address</label></td>
					<td><span id="address">${f:h(accountCreateForm.address)}</span></td>
				</tr>
				<tr>
					<td>&nbsp;</td>
					<td><input type="submit" name="redoForm" id="back"
						value="back" /><input type="submit" id="create" value="create" /></td>
				</tr>
			</table>
		</form:form>


		<form method="get"
			action="${pageContext.request.contextPath}/account/create">
			<input type="submit" id="home" name="home" value="Login page" />
		</form>
	</div>
</body>
</html>