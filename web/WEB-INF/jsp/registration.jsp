<%--
  Created by IntelliJ IDEA.
  User: jane
  Date: 18.12.2022
  Time: 13:25
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
    <title>Registration</title>
</head>
<body>
<form action="${pageContext.request.contextPath}/registration" method="post">
    <label for="fioId"> Name:
        <input type="text" name="fio" id="fioId">
    </label><br>
    <label for="loginId"> Email:
        <input type="text" name="login" id="loginId">
    </label><br>
    <label for="passwordId"> Password:
        <input type="password" name="password" id="passwordId">
    </label><br>
    <label for="roleId"> Role:
        <select name="role" id="roleId">
            <c:forEach var="role" items="${requestScope.roles}">
                <option value="${role}">${role}</option>
            </c:forEach>
        </select>
    </label><br>

    <button type="submit">Send</button>
    <c:if test="${not empty requestScope.rentErrors}">
        <div style="color: red">
            <c:forEach var="error" items="${requestScope.rentErrors}">
                <span>${error.message}</span>
            </c:forEach>
        </div>
    </c:if>

</form>
</body>
</html>
