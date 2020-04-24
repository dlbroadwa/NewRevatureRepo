class Pokemon{
    constructor(id, name, moveset){
        this._id=id;
        this._name=name;
        this._moveset=moveset;
        this._img;
    }
    get id(){
        return this.id;
    }
    set id (id){
        this._id=id;
    }
    get name(){
        return this.name;
    }
    set name(name){
        this._name=name;
    }
    get moveset(){
        return this.moveset;
    }
    set moveset(moveset){
        this._moveset=moveset;
    }
    get img(){
        return this.img;
    }
    set img(img){
        this._img=img;
    }
};
let pokeData;
const numberOfMon = 151;

function randomizeID(numberOfMon){
    var randID = Math.round(Math.random()*numberOfMon);
    return randID;
};

function getPokemon(who){
    var randID = randomizeID(numberOfMon);
    var gotPokemon = new Pokemon();

    var xhttp = new XMLHttpRequest();
    xhttp.responseType = 'json';
    xhttp.onreadystatechange = function (){
        if (this.readyState == 4 && this.status == 200) {
            pokeData = xhttp.response;
            gotPokemon = fillPokemon(pokeData, who);
        }
    };
    
    xhttp.open("GET", "https://pokeapi.co/api/v2/pokemon/"+randID+"/" , true);
    xhttp.send();
    
};

function fillPokemon(pokeData, who){
    var aPokemon = new Pokemon();
    var moveArray = [];
    document.getElementById(who+'PokemonMoves').innerHTML = '';
    //each Pokemon will get 5 random moves from their entire move list
    if(pokeData.moves.length >= 5){
        for(i=0;i<5;i++){
            const li = document.createElement('li'); // create a disconnected element
            var num = randomizeID(pokeData.moves.length);
            var moveName = pokeData.moves[num].move.name;
            moveArray.push(moveName);
            li.innerHTML=moveName;
            document.getElementById(who+"PokemonMoves").appendChild(li);
            };
    } else{
        for(i=0;i<pokeData.moves.length;i++){
            var moveName = pokeData.moves[i].move.name;
            moveArray.push(moveName);
        }
    }
    aPokemon.name = pokeData.name;
    aPokemon.id=pokeData.id;
    aPokemon.moveset=moveArray;
    aPokemon.img=pokeData.sprites.front_default;
    console.log(aPokemon);

    document.getElementById(who+"PokemonImg").setAttribute('src', pokeData.sprites.front_default);
    document.getElementById(who+"PokemonName").innerText = pokeData.name;


};

function startBattle(){
    document.getElementById("battle-sounds").play(); //play the music
    document.getElementById("vs").style.display="block";
    getPokemon('user');
    getPokemon('foe');
};
