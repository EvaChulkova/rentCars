<%--
  Created by IntelliJ IDEA.
  User: jane
  Date: 17.12.2022
  Time: 08:41
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<h1>Add new car</h1>

<form action="${pageContext.request.contextPath}/add_new_car" method="post" enctype="multipart/form-data">
    <label for="brandId"> Brand:
        <input type="text" name="brand" id="brandId">
    </label><br>
    <label for="colorId"> Color:
        <select name="color" id="colorId">
            <c:forEach var="color" items="${requestScope.color}">
                <option value="${color}">${color}</option>
            </c:forEach>
        </select>
    </label><br>
    <label for="seatAmountId"> Seat amount:
        <input type="text" name="seatAmount" id="seatAmountId">
    </label><br>
    <label for="priceId"> Price:
        <input type="text" name="price" id="priceId">
    </label><br>
    <label for="statusId"> Status:
        <select name="status" id="statusId">
            <c:forEach var="status" items="${requestScope.status}">
                <option value="${status}">${status}</option>
            </c:forEach>
        </select>
    </label><br>
    <label for="imageId">Image:
        <input type="file" name="image" id="imageId" required>
    </label><br>

    <button type="submit">Send</button>
</form>
</body>
</html>
