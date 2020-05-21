let attractionCache;

function getAttractions(){
const httpRequest = new XMLHttpRequest();

    if (!httpRequest) {
        console.log('Failed to create an XMLHttpRequest instance');
        getAttractions(null);
        return null;
    }
 httpRequest.onreadystatechange = function() {
         if (httpRequest.readyState === XMLHttpRequest.DONE) {
             if (httpRequest.status === 200) {
                 attractionCache = this.response;
                 resp = JSON.parse(xmlhttp.responseText);
                 localStorage.setItem('attractionCache', JSON.stringify(attractionCache));
                 getAttractions(attractionCache);
             }
             else {
                 console.log('Error: ' + httpRequest.status + ' ' + httpRequest.statusText);
                 getAttractions(null);
             }
         }
     };
httpRequest.open("GET","/java/servlets/AttractionServlet");
httpRequest.setRequestHeader('Content-type', 'application/json; charset=utf-8');
httpRequest.setRequestHeader('find',attractionCache);
httpRequest.responseType = 'json';
httpRequest.send();
}

function displayAttractions(attractions){
  clearDisplay();
    const errMsg = 'Failed to load attractions';
    var mainCont = document.getElementById('managerview');

    if (attractions === null || attractions === undefined) {
        mainCont.innerText = errMsg;
    }
    else {
        for(i=0; i<resp.length;i++){
            var div = document.createElement('div');
            div.innerHtml('<img scr='+response[i].url+'><br/>'+ response[i].name +', ID#:'+response[i].id+'<br/> Rating:'+ response[i].rate+'<br/>Status: '+ resp[i].status);
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
    getAttractions();
}
init();
