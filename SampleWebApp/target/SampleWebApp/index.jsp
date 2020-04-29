<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Form Actions</title>
</head>
<body>
<h1>Item Submission Form</h1>
<form action="Servlet" method="post">
    First Item:
    <input id="firstItem" type="text" name="firstItem"/>
    <br>
    Second Item:
    <input id="secondItem" type="text" name="secondItem"/>
    <br>
    Third Item:
    <input id="thirdItem" type="text" name="thirdItem"/>
    <br>
    Fourth Item:
    <input id="fourthItem" type="text" name="fourthItem"/>
    <br>
    <button id="submit" type="submit">Submit</button>
</form>
<script>
    document.querySelector('#submit').addEventListener('click', (e) => {
        //e.preventDefault();
        let inpt = document.querySelector('#firstItem', '#secondItem', '#thirdItem', '#fourthItem');
        let item = inpt.value;
        if(item.length == 0) {
            return;
        } else {

        }
    });
</script>
</body>
</html>