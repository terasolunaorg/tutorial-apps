<c:if test="${!empty param.testdescription}">
<h1 class="test-description">
Test : ${f:h(param.testdescription)}
</h1>
</c:if>