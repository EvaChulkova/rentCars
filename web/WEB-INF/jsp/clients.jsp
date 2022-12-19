<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: jane
  Date: 17.12.2022
  Time: 08:59
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Clients</title>
</head>
<body>
<h1>Clients</h1>
<ul>
    <c:forEach var="client" items="${requestScope.clients}">
        <li>
            ${client.id} - ${client.description}
        </li>
    </c:forEach>
</ul>
</body>
</html>
