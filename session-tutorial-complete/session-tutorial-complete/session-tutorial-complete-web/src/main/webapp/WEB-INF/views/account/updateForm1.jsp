<div>
    <%-- (1) --%>
    <form:form action="${pageContext.request.contextPath}/account/update"
        method="post" modelAttribute="accountUpdateForm">

        <h2>Account Update Page 1/2</h2>
        <table>
            <tr>
                <td><form:label path="name" cssErrorClass="error-label">name</form:label></td>
                <%-- (2) --%>
                <td><form:input path="name" cssErrorClass="error-input" />
                    <form:errors path="name" cssClass="error-messages" />
                </td>
            </tr>
            <tr>
                <td><form:label path="email" cssErrorClass="error-label">e-mail</form:label></td>
                <td><form:input path="email" cssErrorClass="error-input" />
                    <form:errors path="email" cssClass="error-messages" />
                </td>
            </tr>
            <tr>
                <td><form:label path="birthday" cssErrorClass="error-label">birthday</form:label></td>
                <td><fmt:formatDate value="${accountUpdateForm.birthday}" pattern="yyyy-MM-dd" var="formattedBirthday" />
                    <input type="date" id="birthday" name="birthday" value="${formattedBirthday}">
                    <form:errors path="birthday" cssClass="error-messages" />
                </td>
            </tr>
            <tr>
                <td><form:label path="zip" cssErrorClass="error-label">zip</form:label></td>
                <td><form:input path="zip" cssErrorClass="error-input" />
                    <form:errors path="zip" cssClass="error-messages" />
                </td>
            </tr>
            <tr>
                <td><form:label path="address" cssErrorClass="error-label">address</form:label></td>
                <td><form:input path="address" cssErrorClass="error-input" />
                    <form:errors path="address" cssClass="error-messages" />
                </td>
            </tr>
            <tr>
                <td>&nbsp;</td>
                <td><input type="submit" name="form2" id="next" value="next" /></td>
            </tr>
        </table>
    </form:form>

    <form method="get" action="${pageContext.request.contextPath}/account/update">
        <input type="submit" name="home" id="home" value="home" />
    </form>
</div>
