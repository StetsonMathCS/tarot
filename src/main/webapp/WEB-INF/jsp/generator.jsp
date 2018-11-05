<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Tarot | Generator</title>
    <jsp:include page="./headers/analytics.jsp"/>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css" integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO" crossorigin="anonymous"></head>
    <link href="../../resources/css/styles.css" rel="stylesheet" type="text/css">
    <script src="../../resources/js/tarot.js"></script>
<body>
    <h3>Generator Page</h3>
    <nav class="navbar p-3 pr-5 pl-5">
        <a class="navbar-brand" href="#">
            Stetson University | Tarot
        </a>
    </nav>
    <div class="container mt-5">
        <form id="myForm">
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
                                <tbody>
                                    <tr>
                                        <td>CSCI 111. Introduction to Computing</td>
                                        <td>
                                            <div class="form-group col-sm-8">
                                                <select class="form-control" name="courseCapacity[0]" id="exampleFormControlSelect1">
                                                    <option>5</option>
                                                    <option>10</option>
                                                    <option>15</option>
                                                    <option>20</option>
                                                    <option>25</option>
                                                </select>
                                            </div>
                                        </td>
                                        <td>
                                            <div class="form-group col-sm-8">
                                                <select class="form-control" name="courseSections[0]" id="exampleFormControlSelect2">
                                                    <option>1</option>
                                                    <option>2</option>
                                                    <option>3</option>
                                                    <option>4</option>
                                                    <option>5</option>
                                                </select>
                                            </div>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td>CSCI 141. Introduction to Computer Science I</td>
                                        <td>
                                            <div class="form-group col-sm-8">
                                                <select class="form-control" name="courseCapacity[1]" id="exampleFormControlSelect3">
                                                    <option>5</option>
                                                    <option>10</option>
                                                    <option>15</option>
                                                    <option>20</option>
                                                    <option>25</option>
                                                </select>
                                            </div>
                                        </td>
                                        <td>
                                            <div class="form-group col-sm-8">
                                                <select class="form-control" name="courseSections[1]" id="exampleFormControlSelect4">
                                                    <option>1</option>
                                                    <option>2</option>
                                                    <option>3</option>
                                                    <option>4</option>
                                                    <option>5</option>
                                                </select>
                                            </div>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td>CSCI 190. Special Topics in Computer Science</td>
                                        <td>
                                            <div class="form-group col-sm-8">
                                                <select class="form-control" name="courseCapacity[2]" id="exampleFormControlSelect5">
                                                    <option>5</option>
                                                    <option>10</option>
                                                    <option>15</option>
                                                    <option>20</option>
                                                    <option>25</option>
                                                </select>
                                            </div>
                                        </td>
                                        <td>
                                            <div class="form-group col-sm-8">
                                                <select class="form-control" name="courseSections[2]" id="exampleFormControlSelect6">
                                                    <option>1</option>
                                                    <option>2</option>
                                                    <option>3</option>
                                                    <option>4</option>
                                                    <option>5</option>
                                                </select>
                                            </div>
                                        </td>
                                    </tr>
                                </tbody>
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
                                        <th scope="col">Available</th>
                                        <th scope="col">Block Out</th>
                                    </tr>
                                </thead>
                                <tbody>
                                    <tr>
                                        <td>Elizabeth 205</td>
                                        <td>
                                            <div class="form-group col-sm-8">
                                                <select class="form-control" name="roomCapacity[0]" id="exampleFormControlSelect7">
                                                    <option>1</option>
                                                    <option>2</option>
                                                    <option>3</option>
                                                    <option>4</option>
                                                    <option>5</option>
                                                </select>
                                            </div>
                                        </td>
                                        <td>
                                            8:00 AM - 5:00 PM
                                        </td>
                                        <td>
                                            <input type="text">
                                            <div class="btn btn-dark">+</div>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td>Elizabeth 210</td>
                                        <td>
                                            <div class="form-group col-sm-8">
                                                <select class="form-control" name="roomCapacity[1]" id="exampleFormControlSelect8">
                                                    <option>1</option>
                                                    <option>2</option>
                                                    <option>3</option>
                                                    <option>4</option>
                                                    <option>5</option>
                                                </select>
                                            </div>
                                        </td>
                                        <td>
                                            8:00 AM - 5:00 PM
                                        </td>
                                        <td>
                                            <input type="text">
                                            <div class="btn btn-dark">+</div>
                                        </td>
                                    </tr>
                                </tbody>
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
                                        <th scope="col">Available</th>
                                        <th scope="col">Block Out</th>
                                    </tr>
                                </thead>
                                <tbody>
                                    <tr>
                                        <td>Hala ElAarag</td>
                                        <td>
                                            <div class="form-group col-sm-8">
                                                <select class="form-control" name="facultyMaxClass[0]" id="exampleFormControlSelect9">
                                                    <option>1</option>
                                                    <option>2</option>
                                                    <option>3</option>
                                                    <option>4</option>
                                                    <option>5</option>
                                                </select>
                                            </div>
                                        </td>
                                        <td>
                                            <ul>
                                                <li>
                                                    10:00 AM - 12:00 PM
                                                    <div class="btn btn-danger">-</div>
                                                </li>
                                                <li>
                                                    01:00 PM - 3:00 PM
                                                    <div class="btn btn-danger">-</div>
                                                </li>
                                            </ul>
                                        </td>
                                        <td>
                                            <input type="text">
                                            <div class="btn btn-dark">+</div>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td>Daniel Plante</td>
                                        <td>
                                            <div class="form-group col-sm-8">
                                                <select class="form-control" name="facultyMaxClass[1]" id="exampleFormControlSelect10">
                                                    <option>1</option>
                                                    <option>2</option>
                                                    <option>3</option>
                                                    <option>4</option>
                                                    <option>5</option>
                                                </select>
                                            </div>
                                        </td>
                                        <td>
                                            <ul>
                                                <li>09:00 AM - 12:00 PM<div class="btn btn-danger">-</div></li>
                                                <li>01:00 PM - 5:00 PM<div class="btn btn-danger">-</div></li>
                                            </ul>
                                        </td>
                                        <td>
                                            <input type="text">
                                            <div class="btn btn-dark">+</div>
                                        </td>
                                    </tr>
                                </tbody>
                            </table>
                        </div>
                    </div>
                </div>
                <div class="col-md-10 mb-5">
                    <div class="btn btn-dark w-100" type="submit" onclick="submitForm();">Generate</div>
                </div>
            </div>
        </form>
    </div>
</body>
</html>
