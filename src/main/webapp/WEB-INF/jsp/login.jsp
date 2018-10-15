<%--
  Created by IntelliJ IDEA.
  User: kevin
  Date: 14.09.2018
  Time: 17:17
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <jsp:include page="./headers/analytics.jsp"/>
</head>
<body>
    <h3>Login Page</h3>
${error }
<form method="post" action="${pageContext.request.contextPath }/login">
    <label for="accessToken">Access Token</label>
    <input type="password" name="accessToken" id="accessToken" />
    <input type="submit" value="Login"/>
</form>
</body>
</html>
