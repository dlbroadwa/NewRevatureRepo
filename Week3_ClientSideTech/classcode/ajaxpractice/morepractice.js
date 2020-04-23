document.addEventListener('DOMContentLoaded', initialize, false);

function initialize()
{
    document.getElementById('searchPokemon').addEventListener('click', searchPokemon);
}

function searchPokemon()
{
    var pokemonId = document.getElementById('pokemonName').value;
    var xmlhttp = new XMLHttpRequest();

    xmlhttp.onreadystatechange = function()
    {
        var response;
        var types;
        var sprite;
        if(xmlhttp.readyState == XMLHttpRequest.DONE)
        {
            if(xmlhttp.status == 200)
            {
                //Use response.[something you want to find]
                response = JSON.parse(xmlhttp.responseText);
                document.getElementById("name").innerHTML = response.name;
                response.types.forEach(function (typeSlot){
                    types = typeSlot.type.name + ' ';
                });
                
                document.getElementById("type").innerHTML = types;           
                const shinyPositions = 
                `
                <img src="${response.sprites.front_default}"/>
                <img src="${response.sprites.front_shiny}"/>
                <img src="${response.sprites.front_female}"/>
                <img src="${response.sprites.front_shiny_female}"/>
                <img src="${response.sprites.back_default}"/>
                <img src="${response.sprites.back_shiny}"/>
                <img src="${response.sprites.back_female}"/>
                <img src="${response.sprites.back_shiny_female}"/>
                `
                document.getElementById('sprite').innerHTML = shinyPositions;
                 toggleLoading(false);
            }
            else if(xmlhttp.status == 400)
            {
                alert("Error");
            }
            else
            {
                alert("Something that is not 200 was returned");
            }
        }
    };
    xmlhttp.open('GET', 'http://pokeapi.co/api/v2/pokemon/' + pokemonId+'/', true);
    xmlhttp.send();
    toggleLoading(true);
}

function toggleLoading(show)
{
    if(show)
    {
        document.getElementById('loading').classList.remove('hide');
    }
    else
    {
        document.getElementById('loading').classList.add('hide');
    }
}