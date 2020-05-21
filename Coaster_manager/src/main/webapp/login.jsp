<%--
  Created by IntelliJ IDEA.
  User: Reginald Jefferson
  Date: 5/21/2020
  To change this template use File | Settings | File Templates.
--%>
<!DOCTYPE html>
<html lang="en">
<head>
    <title>Coaster Website</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
</head>
<body>

<nav class="navbar navbar-inverse">
    <div class="container-fluid">
        <div class="navbar-header">
            <a class="navbar-brand" href="#">Voldemort Coasters</a>
        </div>
        <ul class="nav navbar-nav">
            <li class="active"><a href="#">Home</a></li>
            <li class="dropdown"><a class="dropdown-toggle" data-toggle="dropdown" href="#">Page 1 <span class="caret"></span></a>
                <ul class="dropdown-menu">
                    <li><a href="#">Page 1-1</a></li>
                    <li><a href="#">Page 1-2</a></li>
                    <li><a href="#">Page 1-3</a></li>
                </ul>
            </li>
            <li><a href="#">Page 2</a></li>
        </ul>
        <ul class="nav navbar-nav navbar-right">
            <li><a href="customerLogin.jsp"><span class="glyphicon glyphicon-log-in"></span> Log Out</a></li>
        </ul>
    </div>
</nav>

<div class="container">
    <form method="post" action="login" class="bg-light">
        <div id="empId_group">
            <label for="empId">Employee ID:</label>
            <input id="empId" type="text" name="empId" class="form-control" placeholder="Enter Employee ID"/>
        </div>
        <div id=pass_group">
            <label for="password">Password:</label>
            <input id="password" type="password" name="password" class="form-control" placeholder="Enter password"/>
        </div>
        <div id="btn_group">
            <button id="login" class="btn-dark" type="submit">Log in</button>
        </div>
    </form>
</div>
<script>
    document.querySelector('#login').addEventListener('click', (e) => {
        let input1 = document.querySelector('#empId');
        let input2 = document.querySelector('#password');
        let name = input1.value;
        let password = input2.value;
        if(name.length === 0 || password.length === 0 ) {
            e.preventDefault();
            alert("Please enter an Employee ID and Password to continue.");
        }
    });
</script>
</body>
</html>
