let attractionCache = 'all ';

function getAttractions(attractionVal){
const httpRequest = new XMLHttpRequest();

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
    let mainCont = document.getElementById('managerview');

    if (attractions === null || attractions === undefined) {
        mainCont.innerText = errMsg;
    }
    else {
    	let attractionArr = attractions.attractions;
        for(let attraction of attractions.attractions){
        	console.log(attraction);
        	let div = document.createElement('div');
            dic.innerHTML = '</br>'+ attraction.name +', ID#: '+ attraction.id
                            +'<br/>Rating: ' + attraction.rating
                            +'<br/>Status: '+ attraction.status;
            mainCont.appendChild(div);
        }
    }
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
        getAttractions(attractLoc);
    }
    else {
        getAttractions(attractionCache);
    }
}

function init() {
    getAttractions(attractionCache);
}
init();
