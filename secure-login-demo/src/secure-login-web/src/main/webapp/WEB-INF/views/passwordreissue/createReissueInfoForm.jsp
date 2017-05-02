<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Reissue Password</title>
<link rel="stylesheet"
	href="${f:h(pageContext.request.contextPath)}/resources/app/css/styles.css">
</head>
<body>
	<div id="wrapper">
		<h1>Reissue password</h1>
		<t:messagesPanel />
		<form:form
			action="${f:h(pageContext.request.contextPath)}/reissue/create"
			method="POST" modelAttribute="createReissueInfoForm">
			<table>
				<tr>
					<th><form:label path="username" cssErrorClass="error-label">Username</form:label>
					</th>
					<td><form:input path="username" cssErrorClass="error-input" /></td>
					<td><form:errors path="username" cssClass="error-messages" /></td>
				</tr>
			</table>

			<input id="submit" type="submit" value="Reissue password" />
		</form:form>
	</div>
</body>
</html>
