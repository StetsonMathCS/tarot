<%@ page contentType="text/html; charset=UTF-8" %>
<html>
<head>
    <title>Test page</title>
</head>
<body>
    <h2>Welcome!</h2>
    <p>You are currently logged in as <strong>${userName}</strong></p>
    <p>Your session: ${sessionScope.SESS_TOKEN }</p>

    <a href="${pageContext.request.contextPath }/logout">Logout</a>
</body>
</html>