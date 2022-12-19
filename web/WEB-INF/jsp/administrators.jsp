<%--
  Created by IntelliJ IDEA.
  User: jane
  Date: 18.12.2022
  Time: 14:15
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<h1>Admins</h1>
<ul>
    <c:forEach var="admin" items="${requestScope.admins}">
        <li>
            ${admin.id} - ${admin.description}
        </li>
    </c:forEach>
</ul>

</body>
</html>
