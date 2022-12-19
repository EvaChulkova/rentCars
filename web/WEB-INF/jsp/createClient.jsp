<%--
  Created by IntelliJ IDEA.
  User: jane
  Date: 19.12.2022
  Time: 17:30
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<h1>Add info about you (client):</h1>
<form action="${pageContext.request.contextPath}/add_new_client" method="post">
    <label for="ageId">Age:
        <input type="text" name="age" id="ageId">
    </label><br>
    <label for="licenceNoId">Licence No:
        <input type="text" name="licenceNo" id="licenceNoId">
    </label><br>
    <label for="validityId">Age:
        <input type="text" name="validity" id="validityId">
    </label><br>
    <button type="submit">Send</button>
</form>
</body>
</html>
