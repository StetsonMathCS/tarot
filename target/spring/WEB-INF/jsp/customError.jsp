<%--
  Created by IntelliJ IDEA.
  User: kevin
  Date: 05.09.2018
  Time: 17:29
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>${statusCode} - Error</title>
    <jsp:include page="./headers/analytics.jsp"/>
</head>
<body>
<h2>Error - ${statusCode} ${exceptionMessage}</h2>
<p>${fullErrorMessage}</p>
</body>
</html>
