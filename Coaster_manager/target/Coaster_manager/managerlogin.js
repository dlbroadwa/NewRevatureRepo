function login() {
    let data = {
        empID: document.getElementById("empId").value,
        pword: document.getElementById("password").value,
        add: 'login'
    }
    $.ajax({
        url: '/employeeServlet',
        type: 'POST',
        dataType: 'json',
        data: JSON.stringify(data),
        contentType: 'application/json',
        mimeType: 'application/json',
        success: function (response) {

        }
    })
}
// $(document).ready(function() {
//     $("#login").click(function () {
//         let data = {
//             empID: document.getElementById("empId").value,
//             pword: document.getElementById("password").value,
//             add: 'login'
//         }
//         $.ajax({
//             url: '/employeeServlet',
//             type: 'POST',
//             dataType: 'json',
//             data: JSON.stringify(data),
//             contentType: 'application/json',
//             mimeType: 'application/json',
//             success: function (response) {
//
//             }
//         })
//     })
// })