var table = document.querySelector('#table1_ID');

let ourRequest=  new XMLHttpRequest();

ourRequest.open("GET",'http://localhost:8080/RevatureInventoryShop/Welcome');
ourRequest.onload = function() {
    let ourData = JSON.parse(ourRequest.responseText);
    console.log(ourData);

};

ourRequest.send();

var data = {
    index:
        [
            {id:1,in:1},
            {id:2,in:2},
            {id:3,in:3},
            {id:4,in:4},
        ],
    item:
        [
            {id:1,name:'Candy'},
            {id:2,name:'Potato Chips'},
            {id:3,name: 'Mints'},
            {id:4,name: 'Sodas'},
        ],
    quantity:
        [
            {id:1,q:400},
            {id:2,q:350},
            {id:3,q:300},
            {id:4,q:200},
        ]
};

addRows(data);

function addRows(data) {
    if (!data) return;
    var tbody = table.createTBody();
    tbody.id = data.id;
    for (i=0;i<3;i++)
    data.item.forEach(file => {
        var tr = tbody.insertRow();
      for (i=0;i<3;i++)


        var td1 = tr.insertCell();
        var td2 = tr.insertCell();
        var td3 = tr.insertCell();

        var i = document.createTextNode(data.in);
        td1.appendChild(i);

        var n = document.createTextNode(file.name);
        td2.appendChild(n);

        var ph = document.createTextNode(data.q);
        td3.appendChild(ph);
    });
}
