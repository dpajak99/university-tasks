<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<h1>Roster - strona glowna</h1>
<ul>
    <c:out value="${test}"></c:out>
    <c:forEach var="member" items="${memberList}" varStatus="status">
        <li>
            <a href="member.do?id=${status.index}">
                <c:out value="${member}"></c:out>
            </a>
        </li>
    </c:forEach>
</ul>