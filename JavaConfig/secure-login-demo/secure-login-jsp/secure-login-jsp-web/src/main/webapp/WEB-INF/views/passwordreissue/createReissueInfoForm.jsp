<!DOCTYPE html>
<html class="no-js">
<head>
<meta charset="utf-8" />
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
<meta name="viewport" content="width=device-width" />
<script type="text/javascript">
    
</script>
<c:set var="titleKey" value="title.passwordreissue.createReissueInfoForm" />
<title><spring:message code="${titleKey}" text="secure-login" /></title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/app/css/styles.css">
</head>
<body>
    <div class="container">
    <jsp:include page="../layout/header.jsp" />
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
        <hr>
        <p style="text-align: center; background: #e5eCf9;">Copyright &copy; 20XX CompanyName</p>
    </div>
</body>
</html>
