function getProducts() {
    const httpRequest = new XMLHttpRequest;
    if (!httpRequest) {
        console.log('Failed to create an XMLHttpRequest instance');
        displayProducts(null);
        return null;
    }

    httpRequest.onreadystatechange = function() {
        if (httpRequest.readyState === 4) {
            if (httpRequest.status === 200) {
                displayProducts(this.response);
            }
            else {
                console.log('Error: ' + httpRequest.status + ' ' + httpRequest.statusText);
                displayProducts(null);
            }
        }
    };
    httpRequest.open('GET', 'allProducts', true);
    httpRequest.responseType = 'json';
    httpRequest.send();
}

function displayProducts(products) {
    clearDisplay();
    const errMsg = 'Failed to load products';

    if (products === null || products === undefined) {
        document.getElementById('storefront').innerText = errMsg;
    }
    else {
        const lst = document.createElement('ul');
        products.products.forEach(element => {
            const item = document.createElement('li');
            const innerList = document.createElement('ul');
            // Store product ID for add-to-cart purposes
            innerList.setAttribute('data-prodID', element.productID);

            let innerItem = document.createElement('li');
            // Product name
            innerItem.innerText = element.name;
            innerList.appendChild(innerItem);
            // Product type
            innerItem = document.createElement('li');
            innerItem.innerText = 'Type: ' + element.productType;
            innerList.appendChild(innerItem);
            // Product price
            innerItem = document.createElement('li');
            innerItem.innerText = 'Price: ' + convertPrice(element.price / 100);
            innerList.appendChild(innerItem);
            
            item.appendChild(innerList);

            // Add to cart button
            const orderBox = document.createElement('div');
            const orderForm = document.createElement('form');
            orderForm.setAttribute('method', 'POST');
            orderForm.setAttribute('onsubmit', `alert('You bought ${element.name}!'); return false;`);
            orderForm.setAttribute('action', 'addtocart');

            const qtyText = document.createElement('span');
            qtyText.innerText = 'Quantity:';
            orderForm.appendChild(qtyText);
            const qtySelect = document.createElement('select');
            qtySelect.setAttribute('name', 'qtyToBuy');
            for (let i = 1; i <= Math.min(element.qty, 20); ++i) {
                const qtyOption = document.createElement('option');
                qtyOption.setAttribute('value', i);
                qtyOption.innerText = i;
                qtySelect.appendChild(qtyOption);
            }
            orderForm.appendChild(qtySelect);
            const orderButton = document.createElement('button');
            orderButton.innerText = 'Add to Cart';
            orderForm.appendChild(orderButton);

            orderBox.appendChild(orderForm);

            item.appendChild(orderBox);
            lst.appendChild(item);
        });
        document.getElementById('storefront').appendChild(lst);
    }
}

function clearDisplay() {
    document.getElementById('storefront').innerHTML = '';
}

function convertPrice(amount) {
    const formatter = new Intl.NumberFormat('en-US', {style: 'currency', currency: 'USD'});
    return formatter.format(amount);
}

function init() {
    getProducts();
}

init();
