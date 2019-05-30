<div id="wrapper">
    <h1 id="title">Hello world!</h1>

<!-- (1) -->
<sec:authentication property="principal.account" var="account" />
    
    <p>The time on the server is ${serverTime}.</p>

    <p>Welcome ${f:h(account.firstName)} ${f:h(account.lastName)} !!</p>

    <p>
        <form:form action="${pageContext.request.contextPath}/logout">
            <button type="submit">Logout</button>
        </form:form>
    </p>

    <ul>
        <li><a href="${pageContext.request.contextPath}/account">view account</a></li>
    </ul>
</div>
