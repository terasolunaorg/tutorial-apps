<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>Home</title>
<link rel="stylesheet"
	href="${f:h(pageContext.request.contextPath)}/resources/app/css/styles.css">
</head>
<body>
	<div id="wrapper">
		<span id="expiredMessage"> <t:messagesPanel />
		</span>
		<h1>Hello world!</h1>

		<p>Welcome ${f:h(account.firstName)} ${f:h(account.lastName)}</p>

		<c:if test="${!empty lastLoginDate}">
			<p id="lastLogin">Last login date is ${f:h(lastLoginDate)}.</p>
		</c:if>

		<div>
			<a id="info" href="${f:h(pageContext.request.contextPath)}/accounts" />
				Account Information
			</a>
		</div>

		<sec:authorize url="/unlock">
		<div>
			<a id="unlock" href="${f:h(pageContext.request.contextPath)}/unlock?form">
				Unlock Account
			</a>
		</div>
		</sec:authorize>
		
		<form:form action="${f:h(pageContext.request.contextPath)}/logout">
			<button id="logout">Logout</button>
		</form:form>
	</div>
</body>
</html>
