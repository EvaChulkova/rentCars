<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: jane
  Date: 18.12.2022
  Time: 14:57
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<h1>Bookings</h1>
<ul>
    <c:forEach var="booking" items="${requestScope.bookings}">
        <li>
            ${booking.id} - ${booking.description}
        </li>
    </c:forEach>
</ul>
</body>
</html>
