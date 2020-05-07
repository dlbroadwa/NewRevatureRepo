function getAddedProductInfo(pid) {
    const httpRequest = new XMLHttpRequest();
    if (!httpRequest) {
        console.log("Couldn't create XMLHttpRequest instance");
        return;
    }

    httpRequest.onreadystatechange = function() {
        if (httpRequest.readyState === XMLHttpRequest.DONE) {
            if (httpRequest.status === 200) {
                displayProduct(this.response);
            }
            else {
                console.log('Error: ' + httpRequest.status + ' ' + httpRequest.statusText);
            }
        }
    }
    httpRequest.open('GET', 'addtocart?pid=' + encodeURIComponent(pid));
    httpRequest.setRequestHeader('Content-type', 'application/json; charset=utf-8');
    httpRequest.responseType = 'json';
    httpRequest.send();
}

function displayProduct(prodInfo) {
    if (prodInfo !== null) {
        const elem = document.getElementById('item-info');
        elem.innerHTML = ''; // Just in case
        let info = document.createElement('li');
        info.innerText = prodInfo.name;
        elem.appendChild(info);
        info = document.createElement('li');
        const price = prodInfo.subtotal / 100;
        info.innerText = 'Cart subtotal: ' + convertPrice(price);
        elem.appendChild(info);
    }
}

function convertPrice(amount) {
    const formatter = new Intl.NumberFormat('en-US', { style: 'currency', currency: 'USD' });
    return formatter.format(amount);
}

function init() {
    const params = new URLSearchParams(window.location.search);
    const pid = params.get('pid');
    getAddedProductInfo(pid);
}

init();
