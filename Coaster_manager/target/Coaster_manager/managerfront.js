let attractionCache = 'all ';
let mainCont = document.getElementById('managerview');
const httpRequest = new XMLHttpRequest();

function getAttractions(attractionVal){

    if (!httpRequest) {
        console.log('Failed to create an XMLHttpRequest instance');
        displayAttractions(null);
        return null;
    }
	httpRequest.onreadystatechange = () => {
	    if (httpRequest.readyState === 4 && httpRequest.status === 200) {
	    	let response = JSON.parse(httpRequest.response);
            console.log(httpRequest.response)
            attractionCache = attractionVal;
            localStorage.setItem('attractionCache', JSON.stringify(attractionCache));
            displayAttractions(response);
	    }
	};
	httpRequest.open("GET","attractionServlet");
    httpRequest.setRequestHeader('find',attractionVal);
    httpRequest.send();
}

function displayAttractions(attractions){
  clearDisplay();
    const errMsg = 'Failed to load attractions';
    if (attractions === null || attractions === undefined) {
        mainCont.innerText = errMsg;
    }
    else {
    	let attractionArr = attractions.attractions;
        for(let attraction of attractions.attractions){
        	let div = document.createElement('div');
            div.innerHTML = '</br>'+ attraction.name +', ID#: '+ attraction.id
                            +'<br/>Rating: ' + attraction.rating
                            +'<br/>Status: '+ attraction.status;
            mainCont.appendChild(div);
        }
    }
}

function findById(id){

   if (!httpRequest) {
        console.log('Failed to create an XMLHttpRequest instance');
        displayAttractions(null);
        return null;
    }
	httpRequest.onreadystatechange = () => {
	    if (httpRequest.readyState === 4 && httpRequest.status === 200) {
	    	let response = JSON.parse(httpRequest.response);
            console.log(httpRequest.response)
            attractionCache = attractionVal;
            localStorage.setItem('attractionCache', JSON.stringify(attractionCache));
            displayAttractions(response);
	    }
	};
	httpRequest.open("GET","attractionServlet");
    httpRequest.setRequestHeader('find','id');
    httpRequest.setRequestHeader('id',id);
    httpRequest.send();
}


function clearDisplay() {
    document.getElementById('managerview').innerHTML = '';
}

function applyFilter(attractLoc) {
    if (!attractionCache) {
        console.log('How did this happen?');
        return;
    }

    if (attractLoc.length != 0) {
        clearDisplay();
        if(attractLoc=='id'){
        let form = document.createElement('form');
            form.setAttribute('name','idForm');
            form.setAttribute('action',findById(this.form.id.value));
        let input = document.createElement('input');
            input.setAttribute('type','number');
            input.setAttribute('id','id');
                form.appendChild(input);
        let submit = document.createElement('input');
            submit.setAttribute('type','submit');
                form.appendChild(submit);
                mainCont.appendChild(form);
        }
        else{
        getAttractions(attractLoc);
        }
    }
    else {
        getAttractions(attractionCache);
    }

}

function init() {
    getAttractions(attractionCache);
}
init();
