<sec:authentication property="principal" var="userDetails" />
<div style="display: inline-flex">
    welcome&nbsp;&nbsp; <span id="userName">${f:h(userDetails.account.name)}</span>
    <form method="post" action="${pageContext.request.contextPath}/logout">
        <input type="submit" id="logout" value="logout" />
        <sec:csrfInput />
    </form>
    <form method="get" action="${pageContext.request.contextPath}/account/update">
        <input type="submit" name="form1" id="updateAccount" value="Account Update" />
    </form>
</div>
<br>
<br>

<div>
    <p>select a category</p>

    <form:form method="get" action="${pageContext.request.contextPath}/goods/" modelAttribute="goodViewForm">
        <form:select path="categoryId" items="${CL_CATEGORIES}" />
        <input type="submit" id="update" value="update" />
    </form:form>
    <br />
    <t:messagesPanel />
    <table>
        <tr>
            <th>Name</th>
            <th>Price</th>
        </tr>
        <c:forEach items="${page.content}" var="goods" varStatus="status">
            <tr>
                <td><a id="${f:h(goods.name)}" href="${pageContext.request.contextPath}/goods/${f:h(goods.id)}">${f:h(goods.name)}</a></td>
                <td><fmt:formatNumber value="${f:h(goods.price)}" type="CURRENCY" currencySymbol="&yen;" maxFractionDigits="0" /></td>
            </tr>
        </c:forEach>
    </table>
    <t:pagination page="${page}" outerElementClass="pagination" />
</div>
<div>
    <p>
        <fmt:formatNumber value="${page.totalElements}" /> results <br>
        ${f:h(page.number + 1) } / ${f:h(page.totalPages)} Pages
    </p>
</div>
