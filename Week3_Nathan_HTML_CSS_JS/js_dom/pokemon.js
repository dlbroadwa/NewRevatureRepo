var destination = "bulbasaur";
document.getElementById('pokemonName').value = bulbasaur;
document.getElementById('pokemonDexNum').value = 1;

function toggleInputSelect() {
    if (document.getElementById('name').checked) {
            document.getElementById('pokemonName').style.display = "inline-block";
            document.getElementById('pokemonNameLabel').style.display = "inline-block";
            document.getElementById('pokemonNameBtn').style.display = "inline-block";
            document.getElementById('pokemonDexNum').style.display = "none";
            document.getElementById('pokemonDexNumLabel').style.display = "none";
            document.getElementById('pokemonDexNumBtn').style.display = "none";
    } else if (document.getElementById('number').checked) {
            document.getElementById('pokemonName').style.display = "none";
            document.getElementById('pokemonNameLabel').style.display = "none";
            document.getElementById('pokemonNameBtn').style.display = "none";
            document.getElementById('pokemonDexNum').style.display = "inline-block";
            document.getElementById('pokemonDexNumLabel').style.display = "inline-block";
            document.getElementById('pokemonDexNumBtn').style.display = "inline-block";
    }
}

function setNameDestination() {
    var inputBox = document.getElementById('pokemonName');
    destination = (inputBox.value).toLowerCase();
}

function setDexNumDestination() {
    var inputBox = document.getElementById('pokemonDexNum');
    destination = inputBox.value;
}

function loadDoc() {

    var xhttp;
    if (window.XMLHttpRequest) {
        xhttp = new XMLHttpRequest();
    } else {
        xhttp = new ActiveXObject();
    }

    xhttp.onreadystatechange = function() {
        if (this.readyState == 4 && this.status == 200) {
            var pokemonJsonObj = JSON.parse(this.responseText);
            var pokemonName = pokemonJsonObj.species.name;
            var contentTxt = "<h3>"+pokemonName+"</h3>";

            contentTxt += "<p>Abilities:<br></p>"; 
            contentTxt += "<table border='1'>";
            contentTxt += "<tr><th>Name</th><th>URL</th><th>Is it hidden?</th></tr>"; 
            for (var x in pokemonJsonObj.abilities) {
                var abilityJson = pokemonJsonObj.abilities[x];
                contentTxt += "<tr><td>" + abilityJson.ability.name + "</td><td>" + abilityJson.ability.url + "</td><td>" + abilityJson.is_hidden + "</td></tr>";
            }
            contentTxt += "</table>" 

            document.getElementById("demo").innerHTML = contentTxt;
            //document.getElementById("demo").innerHTML = this.responseText;
        } else {
            document.getElementById("demo").innerHTML = "Cannot obtain information!";
        }
    };

    xhttp.open("GET", "https://pokeapi.co/api/v2/pokemon/"+destination+"/", true);
    xhttp.send();
}

toggleInputSelect();
setNameDestination();
