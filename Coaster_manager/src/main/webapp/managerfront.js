let attractionCache;

function changeHTML(){
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
                localStorage.setItem('attractionCache', JSON.stringify(attractionCache));
                getAttractions(attractionCache);
            }
            else {
                console.log('Error: ' + httpRequest.status + ' ' + httpRequest.statusText);
                getAttractions(null);
            }
        }
    };

httpRequest.open("GET","java/servlets/attractionServlet");
httpRequest.setRequestHeader('Content-type', 'application/json; charset=utf-8');
httpRequest.setRequestHeader('find',attractionCache);
httpRequest.responseType = 'json';
httpRequest.send();
}

function getAttractions(attractions){
  clearDisplay();
    const errMsg = 'Failed to load attractions';
    var mainCont = document.getElementById('managerview');

    if (attractions === null || attractions === undefined) {
        mainCont.innerText = errMsg;
    }
    else {
        for(i=0; i<resp.length;i++){
            var div = document.createElement('div');
            div.innerHtml('<img scr='+resp[i].url+'><br/>'+ resp[i].name +', ID#:'+resp[i].id+'<br/> Rating:'+ resp[i].rate+'<br/>Status: '+ resp[i].status);
            mainCont.appendChild(div);
        }
    }


}
function clearDisplay() {
    document.getElementById('managerview').innerHTML = '';
}