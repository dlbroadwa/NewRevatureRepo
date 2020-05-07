$(function() {
    $('#clickme').click(function() {
        $.ajax({
            url: "package.json",
            dataType: 'json',
            success: function(data) {
                var items = [];

                $.each(data, function(key, val) {

                    var selectedGender = document.getElementById("gender").getSelection("gender");

                    var selectedAge = document.getElementById("age").getSelection("age");

                    var selectedType = document.getElementById("type").getSelection("type");

                    if(selectedType === "any" && selectedGender === "any" && selectedAge === 6){

                        items.push('<li id="' + key + '">' +val.id+ val.type + val.name + val.age +val.breed + val.gender + '</li>');

                    }


                    if(selectedType === "any"){

                        if (selectedGender === val.gender || selectedGender === "any") {

                            if (selectedAge === val.age || selectedAge ==="any") {

                                items.push('<li id="' + key + '">' + val.id + val.type + val.name + val.age + val.breed + val.gender + '</li>');

                            }

                        }

                    }

                    if(selectedType === val.type) {

                        if (selectedGender === val.gender || selectedGender === "any") {

                            if (selectedAge === val.age || selectedAge ==="any") {

                                items.push('<li id="' + key + '">' + val.id + val.type + val.name + val.age + val.breed + val.gender + '</li>');

                            }

                        }
                    }

                });

                $('<ul/>', {
                    'class': 'interest-list',
                    html: items.join('')
                }).appendTo('body');

            },
            statusCode: {
                404: function() {
                    alert('There was a problem with the server.  Try again soon!');
                }
            }
        });
    });
});