<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Tarot | Generator</title>
    <jsp:include page="./headers/analytics.jsp"/>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css" integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO" crossorigin="anonymous"></head>


<link href="${pageContext.request.contextPath}/resources/css/styles.css" rel="stylesheet" />
<script src="${pageContext.request.contextPath}/resources/js/tarot.js" type="text/javascript"></script>


<body>
    <h3>Generator Page</h3>
    <nav class="navbar p-3 pr-5 pl-5">
        <a class="navbar-brand" href="#">
            Stetson University | Tarot
        </a>
    </nav>
    <div class="container mt-5">
        <form id="myForm" action="" method="POST">
            <div class="row d-flex justify-content-center">
                <div class="col-md-10">
                    <div class="card">
                        <div class="card-header">
                            <h5>Step 1: Course Parameters</h5>
                        </div>
                        <div class="card-body">
                            <table class="table">
                                <thead>
                                    <tr>
                                        <th scope="col">Course</th>
                                        <th scope="col">Capacity</th>
                                        <th scope="col">Sections</th>
                                    </tr>
                                </thead>
                                <tbody id="courseParameters"></tbody>
                            </table>
                        </div>
                    </div>
                </div>
                <div class="col-md-10 mt-5 mb-5">
                    <div class="card">
                        <div class="card-header">
                            <h5>Step 2: Room Parameters</h5>
                        </div>
                        <div class="card-body">
                            <table class="table">
                                <thead>
                                    <tr>
                                        <th scope="col">Room</th>
                                        <th scope="col">Capacity</th>
                                        <th scope="col">Active</th>
                                    </tr>
                                </thead>
                                <tbody id="roomParameters"></tbody>
                            </table>
                        </div>
                    </div>
                </div>
                <div class="col-md-10 mb-5">
                    <div class="card">
                        <div class="card-header">
                            <h5>Step 3: Faculty Parameters</h5>
                        </div>
                        <div class="card-body">
                            <table class="table">
                                <thead>
                                    <tr>
                                        <th scope="col">Faculty</th>
                                        <th scope="col">Max Classes</th>
                                        <th scope="col">Active</th>
                                        <th scope="col">Availability (From/To)</th>
                                    </tr>
                                </thead>
                                <tbody id="facultyParameters"></tbody>
                            </table>
                        </div>
                    </div>
                </div>
                <div class="col-md-10 mb-5">
                    <input type="submit" onclick="submitForm();" class="btn btn-primary">Generate</div>
                </div>
            </div>
        </form>
    </div>
    <script type="text/javascript"> loadGenerator(); </script>
</body>
</html>
