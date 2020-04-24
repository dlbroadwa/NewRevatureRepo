//https://swapi.py4e.com/api/

function getPeople()
{
    var person = document.getElementById("num").value;
    var xmlhttp = new XMLHttpRequest();
    xmlhttp.onreadystatechange = function()
    {
        var response;
        if(xmlhttp.readyState == XMLHttpRequest.DONE)
        {
            response = JSON.parse(xmlhttp.responseText);
            const html = 
            `
            <div class="char">Name: ${response.name}</div>
            <div class="char">Height: ${response.height}</div>
            <div class="char">Mass: ${response.mass}</div>
            <div class="char">Hair Color: ${response.hair_color}</div>
            <div class="char">Skin Color: ${response.skin_color}</div>
            <div class="char">Eye Color: ${response.eye_color}</div>
            <div class="char">Birth Year: ${response.birth_year}</div>
            <div class="char">Gender: ${response.gender}</div>
            `;
            document.getElementById('name-attributes').innerHTML = html;
        }
        else if(xmlhttp.status == 400)
        {
            console.log("Error");
        }
        else
        {
            console.log("Error");
        }
    }

    xmlhttp.open("GET", "https://swapi.dev/api/people/" + person +"/", true);
    xmlhttp.send();
}


