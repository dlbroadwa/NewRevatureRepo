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

            // Item name
            let innerItem = document.createElement('li');
            innerItem.innerText = `Item: ${prod.name}`;
            innerList.appendChild(innerItem);

            // Item quantity (editable)
            innerItem = document.createElement('li');
            innerItem.innerText = 'Quantity: ';
            const qtySelector = document.createElement('select');
            qtySelector.setAttribute('autocomplete', 'off');
            qtySelector.setAttribute('onchange', `changeQty(${prod.productID}, this.value)`);
            const pCache = localStorage.getItem('productCache');
            if (pCache !== null) {
                const prodDatabase = JSON.parse(pCache);
                const maxQty = getQty(prod.productID);
                for (let i = 1; i <= Math.min(maxQty, 20); ++i) {
                    const opt = document.createElement('option');
                    opt.setAttribute('value', i);
                    if (i === prod.qty) {
                        opt.setAttribute('selected', '');
                    }
                    opt.innerText = i;
                    qtySelector.appendChild(opt);
                }
                innerItem.appendChild(qtySelector);
            }
            innerList.appendChild(innerItem);

            // Price
            const price = convertPrice(prod.price * prod.qty / 100);
            innerItem = document.createElement('li');
            innerItem.innerText = `Price: ${price}`;
            innerList.appendChild(innerItem);

            // Remove button
            innerItem = document.createElement('li');
            const removeButton = document.createElement('button');
            removeButton.setAttribute('type', 'button');
            removeButton.setAttribute('onclick', `removeItem(${prod.productID})`);
            removeButton.innerText = 'Remove';
            innerItem.appendChild(removeButton);
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

function getQty(pID) {
    const pCache = localStorage.getItem('productCache');
    if (pCache === null) {
        return 0;
    }
    const prodList = JSON.parse(pCache);
    for (let i = 0; i < prodList.products.length; ++i) {
        if (prodList.products[i].productID === pID) {
            return prodList.products[i].qty;
        }
    }

    return 0;
}

function clearCartDisplay() {
    document.getElementById('shop-cart').innerHTML = '';
}

function changeQty(pID, qty) {
    const request = new XMLHttpRequest();
    if (!request) {
        console.log('Failed to create XMLHttpRequest');
        return;
    }
    request.onreadystatechange = function () {
        if (request.readyState === XMLHttpRequest.DONE) {
            if (request.status >= 200 && request.status < 400) {
                location.reload(true);
            }
            else {
                console.log('Something went wrong when updating cart');
            }
        }
    };
    const data = `prodID=${encodeURIComponent(pID)}&newQty=${encodeURIComponent(qty)}&action=update`;

    request.open('POST', 'shoppingcart');
    request.setRequestHeader('Content-type', 'application/x-www-form-urlencoded');
    request.send(data);
}

function removeItem(pID) {
    const request = new XMLHttpRequest();
    if (!request) {
        console.log('Failed to create XMLHttpRequest');
        return;
    }
    request.onreadystatechange = function () {
        if (request.readyState === XMLHttpRequest.DONE) {
            if (request.status >= 200 && request.status < 400) {
                location.reload(true);
            }
            else {
                console.log('Something went wrong when removing cart item');
            }
        }
    };
    const data = `prodID=${pID}&action=remove`;

    request.open('POST', 'shoppingcart');
    request.setRequestHeader('Content-type', 'application/x-www-form-urlencoded');
    request.send(data);
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
