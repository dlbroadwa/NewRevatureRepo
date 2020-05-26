<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
    <title>Forum | Login & Registration</title>
    <%--    Note, .css FILES have to be included with a style tag, like below.--%>
    <style><%@include file="static/loginStyle.css"%></style>
    <link href='https://fonts.googleapis.com/css?family=Open+Sans:700,600' rel='stylesheet' type='text/css'>
</head>
<body>
<br>
<br>
<div class="box">
<<<<<<< HEAD
=======
<<<<<<< HEAD
=======
<<<<<<< HEAD
        <h1>Log In!</h1>
        <input class="input" type="text" id="email" placeholder="Email" />
        <input class="input" type="password" id="password1" placeholder="Password" />
        <input class="btn" type="submit" onclick="login()" value="Login" />
=======
>>>>>>> a2110d0d94c3224a8a7497a7a2cb235abf7808bc
>>>>>>> 03406b361959d2f3a6eed57db9e3ff0ee8027ac9
    <h1>Log In!</h1>
    <input class="input" type="text" id="email" placeholder="Email" />
    <input class="input" type="password" id="password1" placeholder="Password" />
    <input class="btn" type="submit" onclick="login()" value="Login" />
<<<<<<< HEAD
=======
<<<<<<< HEAD
=======
>>>>>>> 9927e5155986d8e2d2ca60276d5433fef1ae83e4
>>>>>>> a2110d0d94c3224a8a7497a7a2cb235abf7808bc
>>>>>>> 03406b361959d2f3a6eed57db9e3ff0ee8027ac9
</div>
<br>
<br>
<br>
<br>
<br>
<br>
<div class="box">
<<<<<<< HEAD
=======
<<<<<<< HEAD
    <h1>Register!</h1>
    <input class="input" type="text" id="username" placeholder="Username" required />
    <input class="input" type="text" id="firstName" placeholder="John" required />
    <input class="input" type="text" id="lastName" placeholder="Doe" required />
    <input class="input" type="password" id="password2" placeholder="password" required />
    <input class="btn" type="submit" onclick="register()" value="Register" />
=======
<<<<<<< HEAD
        <h1>Register!</h1>
        <input class="input" type="text" id="username" placeholder="Username" required />
        <input class="input" type="text" id="firstName" placeholder="John" required />
        <input class="input" type="text" id="lastName" placeholder="Doe" required />
        <input class="input" type="password" id="password2" placeholder="password" required />
        <input class="btn" type="submit" onclick="register()" value="Register" />
=======
>>>>>>> 03406b361959d2f3a6eed57db9e3ff0ee8027ac9
    <h1>Register!</h1>
    <input class="input" type="text" id="username" placeholder="Username" required />
    <input class="input" type="text" id="firstName" placeholder="John" required />
    <input class="input" type="text" id="lastName" placeholder="Doe" required />
    <input class="input" type="password" id="password2" placeholder="password" required />
    <input class="btn" type="submit" onclick="register()" value="Register" />
<<<<<<< HEAD
=======
>>>>>>> 9927e5155986d8e2d2ca60276d5433fef1ae83e4
>>>>>>> a2110d0d94c3224a8a7497a7a2cb235abf7808bc
>>>>>>> 03406b361959d2f3a6eed57db9e3ff0ee8027ac9
</div>
</body>

<script>
    function login() {
<<<<<<< HEAD
=======
<<<<<<< HEAD
=======

<<<<<<< HEAD
            let cred =
                {
                    action : "login",
                    em : document.getElementById("email").value,
                    pw : document.getElementById("password1").value
                }
            $.ajax
            ({
                type: "POST",
                url: "CustomerServlet",
                contentType: "application/json",
                dataType: "json",
                data: JSON.stringify(cred),
                success: function (response) {
                    sessionStorage.setItem("em", cred.em);
                    sessionStorage.setItem("id", cred.id);
                    window.open("TicketPurchase.html","_self");
                },
                error: function (response) {
                    console.log(response);
                },

            });
    }
    function register() {
            let cred =
                {
                    action : "create",
                    id: "42",
                    em :document.getElementById("username").value,
                    pw: document.getElementById("password1").value,
                    fn: document.getElementById("firstName").value,
                    ln: document.getElementById("lastName").value
                }
            $.ajax
            ({
                type: "POST",
                url: "CustomerServlet",
                contentType: "application/json",
                dataType: "json",
                data: JSON.stringify(cred),
                success: function (response) {
                    sessionStorage.setItem("em", cred.em);
                    sessionStorage.setItem("id", cred.id);
                    window.open("TicketPurchase.html","_self");
                },
                error: function (response) {
                    window.alert(response);
                }
            });
=======
>>>>>>> a2110d0d94c3224a8a7497a7a2cb235abf7808bc
>>>>>>> 03406b361959d2f3a6eed57db9e3ff0ee8027ac9
        let cred =
            {
                action : "login",
                em : document.getElementById("email").value,
                pw : document.getElementById("password1").value
            }
        $.ajax
        ({
            type: "POST",
            url: "CustomerServlet",
            contentType: "application/json",
            dataType: "json",
            data: JSON.stringify(cred),
            success: function (response) {
<<<<<<< HEAD
                sessionStorage.setItem("em", cred.em);
                sessionStorage.setItem("id", cred.id);
                window.open("TicketPurchase.html","_self");
=======
<<<<<<< HEAD
                sessionStorage.setItem("em", cred.em);
                sessionStorage.setItem("id", cred.id);
                window.open("TicketPurchase.html","_self");
=======
                console.log(response);
                //window.open("TicketPurchase.html","_self");
>>>>>>> a2110d0d94c3224a8a7497a7a2cb235abf7808bc
>>>>>>> 03406b361959d2f3a6eed57db9e3ff0ee8027ac9
            },
            error: function (response) {
                console.log(response);
            },

        });
    }
    function register() {
        let cred =
            {
                action : "create",
                id: "42",
                em :document.getElementById("username").value,
                pw: document.getElementById("password1").value,
                fn: document.getElementById("firstName").value,
                ln: document.getElementById("lastName").value
            }
        $.ajax
        ({
            type: "POST",
            url: "CustomerServlet",
            contentType: "application/json",
            dataType: "json",
            data: JSON.stringify(cred),
            success: function (response) {
<<<<<<< HEAD
                sessionStorage.setItem("em", cred.em);
                sessionStorage.setItem("id", String(cred.id));
                window.open("TicketPurchase.html","_self");
=======
<<<<<<< HEAD
                sessionStorage.setItem("em", cred.em);
                sessionStorage.setItem("id", cred.id);
                window.open("TicketPurchase.html","_self");
=======
                console.log(response);
                //window.open("TicketPurchase.html","_self");
>>>>>>> a2110d0d94c3224a8a7497a7a2cb235abf7808bc
>>>>>>> 03406b361959d2f3a6eed57db9e3ff0ee8027ac9
            },
            error: function (response) {
                window.alert(response);
            }
        });
<<<<<<< HEAD

=======
<<<<<<< HEAD

=======
>>>>>>> 9927e5155986d8e2d2ca60276d5433fef1ae83e4
>>>>>>> a2110d0d94c3224a8a7497a7a2cb235abf7808bc
>>>>>>> 03406b361959d2f3a6eed57db9e3ff0ee8027ac9
    }

</script>
</html>