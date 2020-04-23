function getPokemonData() {
    clearDisplay();

    const httpRequest = new XMLHttpRequest();
    if (!httpRequest) {
        alert("Couldn't create an XMLHttpRequest instance??");
        return false;
    }

    let receivedData;

    httpRequest.onreadystatechange = function() {
        if (httpRequest.readyState == XMLHttpRequest.DONE) {
            //alert('Got a response, I think');
            document.getElementById('loading').innerText = '';
            if (httpRequest.status == 200) {
                console.log('Response type is ' + httpRequest.responseType);
                receivedData = httpRequest.response;
                //document.getElementById('stuff-appears-here').innerText = httpRequest.responseText;
                displayPokemon(receivedData);
            }
            else {
                alert('Something went wrong! ' + httpRequest.status + ' ' + httpRequest.statusText);
            }
        }
        else {
            document.getElementById('loading').innerText = 'Retrieving data...';
        }
    }

    const query = document.getElementById('search-box').value;
    const url = 'https://pokeapi.co/api/v2/pokemon/' + query;
    httpRequest.open('GET', url, true);
    httpRequest.responseType = 'json';
    httpRequest.send();
}

function displayPokemon(pokemonData) {
    // Show name
    document.getElementById('poke-name').innerText = 'Name: ' + pokemonData.name;
    
    // Show type information
    let types = [];
    pokemonData.types.forEach((t) => {
        types.push(t.type.name);
    });
    document.getElementById('poke-type').innerText = 'Type: ' + types.join('/');

    // Show image
    const pokeImg = document.createElement('img');
    pokeImg.setAttribute('src', pokemonData.sprites.front_default);
    document.getElementById('poke-img').appendChild(pokeImg);
}

function clearDisplay() {
    document.getElementById('poke-name').innerText = '';
    document.getElementById('poke-type').innerText = '';
    document.getElementById('poke-img').innerHTML = '';
}