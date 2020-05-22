<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
    <title>Forum | Login & Registration</title>
    <%--    Note, .css FILES have to be included with a style tag, like below.--%>
    <style><%@include file="loginStyle.css"%></style>
    <link href='https://fonts.googleapis.com/css?family=Open+Sans:700,600' rel='stylesheet' type='text/css'>
</head>
<body>
<br>
<br>
<div class="box">
    <form method="POST" onsubmit="login()">
        <h1>Log In!</h1>
        <input class="input" type="text" name="email" placeholder="Email" />
        <input class="input" type="password" name="password" placeholder="password" />
        <input class="btn" type="submit" value="Login" />
    </form>
</div>
<br>
<br>
<br>
<br>
<br>
<br>
<div class="box">
    <form method="POST" action="register">
        <h1>Register!</h1>
        <input class="input" type="text" name="username" placeholder="Username" />
        <input class="input" type="password" name="password" placeholder="password" />
        <input class="btn" type="submit" value="Register" />
    </form>
</div>
</body>

<script>


    function login() {

        $.getJSON('java/servlets/CustomerServlet'), function (data) {

            let cred =
                {
                    pw: data.password,
                    email: data.email
                }
            $.ajax
            ({

                type: "POST",
                url: "/CustomerServlet",
                contentType: "application/json",
                dataType: "json",
                data: JSON.stringify(cred),
                success: function (response) {

                }

            });
        }
    }

</script>
</html>