
function getPokemon()
{
    var choice = prompt("Enter and Id Number and see which Pokemon comes up :)");
    const apiData =
    {
        url: 'https://pokeapi.co/api/v2/',
        type: 'pokemon',
        id: choice,
    }

    const {url, type, id} = apiData;
    const apiUrl = `${url}${type}/${id}`

    fetch(apiUrl)
    .then((data) => data.json())
    .then((pokemon) => generateHtml(pokemon));

    const generateHtml = (data) =>
    {
        const html = `
            <div class="name">${data.name}</div>
            <img src=${data.sprites.front_shiny}>
            <div class="details">
                <span>Height: ${data.height}</span>
                <span>Weight: ${data.weight}</span>
            </div>
        `
        const pokemonDiv = document.querySelector('.pokemon');
        pokemonDiv.innerHTML = html;
    }
}

const getbutton = document.getElementById("poke");
getbutton.addEventListener('click', () => {
    getPokemon();
});




