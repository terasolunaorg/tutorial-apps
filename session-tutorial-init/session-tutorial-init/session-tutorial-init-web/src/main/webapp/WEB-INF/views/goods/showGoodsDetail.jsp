<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8" />
<title>Item List Page</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/app/css/styles.css">
</head>
<body>
 
	<sec:authentication property="principal" var="userDetails" />
	<div style="display: inline-flex">
		welcome&nbsp;&nbsp; <span id="userName">${f:h(userDetails.account.name)}</span>
		<form:form method="post"
			action="${pageContext.request.contextPath}/logout">
			<input type="submit" id="logout" value="logout" />
		</form:form>
		<form method="get"
			action="${pageContext.request.contextPath}/account/update">
			<input type="submit" name="form1" id="updateAccount"
				value="Account Update" />
		</form>
	</div>
	<br>
	<br>
 
	<div class="container">
 
		<table>
			<tr>
				<th>Name</th>
				<td><span id="name">${f:h(goods.name)}</span></td>
			</tr>
			<tr>
				<th>Price</th>
				<td><span id="price"><fmt:formatNumber
							value="${f:h(goods.price)}" type="CURRENCY"
							currencySymbol="&yen;" maxFractionDigits="0" /></span></td>
			</tr>
			<tr>
				<th>Description</th>
				<td><span id="description">${f:h(goods.description)}</span></td>
			</tr>
		</table>
 
		<form method="get" action="${pageContext.request.contextPath}/goods">
			<input type="submit" id="home" value="home" />
		</form>
	</div>
</body>
</html>