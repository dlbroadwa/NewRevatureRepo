function updateLoginForm(response, showLoginCont) {
  let vis = showLoginCont ? "visible" : "hidden";
  // alert(showLoginCont);
  document.getElementById("contLogin").style.visibility = vis;

  if (!showLoginCont) {
    // alert("CREATING BUTTON DYNAMICALLY");
    let span = document.createElement("P");
    var myArr = JSON.parse(response);
    span.innerText = "Welcome back " + myArr.username;
    document.getElementById("contLoggedIn").appendChild(span);

    let logoutLink = document.createElement("BUTTON");
    logoutLink.innerHTML = "LOGOUT";
    logoutLink.onclick = function () {
      loginUser("logout=true", true);
    };
    document.getElementById("contLoggedIn").appendChild(logoutLink);
  }
  if (showLoginCont) {
    // alert("SHOULD BE DESTROYING CONT_LOGGEDIN CONTENTS");
    document.getElementById("contLoggedIn").innerHTML = "";
  }
}

function loginUser(sendString, showLoginCont) {
  //  alert(sendString);
  let xhttp = new XMLHttpRequest();
  //xhttp.onreadystatechange = updateLoginForm(xhttp.response);
  xhttp.onreadystatechange = function () {
    if (this.readyState == 4 && this.status == 200) {
      updateLoginForm(this.response, showLoginCont);
    }
  };
  xhttp.open("POST", "userlogin", true);
  xhttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
  xhttp.send(sendString);
}
