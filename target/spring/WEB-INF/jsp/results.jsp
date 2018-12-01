<%@ page contentType="text/html; charset=UTF-8" %>
<html>
<head>
    <title>Test page</title>
    <jsp:include page="./headers/analytics.jsp"/>
</head>
<body>
    <h3>Results Generated!</h3>
    ...loading

    <p id="results"></p>
    <script>
        const data = localStorage.getItem('data');
        document.getElementById("results").innerHTML=data;
    </script>
</body>
</html>