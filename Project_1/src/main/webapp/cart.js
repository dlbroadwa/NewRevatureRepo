function getProducts() {
    const httpRequest = new XMLHttpRequest();
    if (!httpRequest) {
        console.log('Failed to create an XMLHttpRequest instance');
        displayCart(null);
        return null;
    }

    httpRequest.onreadystatechange = function () {
        if (httpRequest.readyState === XMLHttpRequest.DONE) {
            if (httpRequest.status === 200) {
                productCache = this.response;
                displayCart(productCache);
            }
            else {
                console.log('Error: ' + httpRequest.status + ' ' + httpRequest.statusText);
                displayCart(null);
            }
        }
    };
    httpRequest.open('GET', 'shoppingcart');
    httpRequest.setRequestHeader('Content-type', 'application/json; charset=utf-8');
    httpRequest.responseType = 'json';
    httpRequest.send();
}

function displayCart(products) {
    const outerElem = document.getElementById('shop-cart');
    clearCartDisplay();
    if (products === null || products === undefined) {
        outerElem.innerText = 'Failed to load shopping cart';
    }
    else if (products.contents.length === 0) {
        outerElem.innerText = 'Your shopping cart is empty.';
    }
    else {
        // Display subtotal
        const subtotalDisp = document.createElement('div');
        const subtotalPrice = convertPrice(products.subtotal / 100);
        subtotalDisp.innerHTML = `<b>Subtotal: ${subtotalPrice}`;
        outerElem.appendChild(subtotalDisp);

        // Display list of items in cart
        const lst = document.createElement('ul');
        products.contents.forEach(prod => {
            const item = document.createElement('li');
            item.setAttribute('class', 'product-listing');
            const innerList = document.createElement('ul');

            let innerItem = document.createElement('li');
            innerItem.innerText = `Item: ${prod.name}`;
            innerList.appendChild(innerItem);

            innerItem = document.createElement('li');
            innerItem.innerText = `Quantity: ${prod.qty}`;
            innerList.appendChild(innerItem);

            const price = convertPrice(prod.price * prod.qty / 100);
            innerItem = document.createElement('li');
            innerItem.innerText = `Price: ${price}`;
            innerList.appendChild(innerItem);

            item.appendChild(innerList);
            lst.appendChild(item);
        });
        outerElem.appendChild(lst);
    }
}

function convertPrice(amount) {
    const formatter = new Intl.NumberFormat('en-US', { style: 'currency', currency: 'USD' });
    return formatter.format(amount);
}

function clearCartDisplay() {
    document.getElementById('shop-cart').innerHTML = '';
}

function clearCart() {
    const request = new XMLHttpRequest();
    if (!request) {
        console.log('Failed to create XMLHttpRequest');
        return;
    }
    request.onreadystatechange = function() {
        if (request.readyState === XMLHttpRequest.DONE) {
            if (request.status >= 200 && request.status < 400) {
                location.reload(true);
            }
            else {
                console.log('Something went wrong when clearing cart');
            }
        }
    }
    request.open('DELETE', 'shoppingcart');
    request.send();
}

function init() {
    getProducts();
}

init();
