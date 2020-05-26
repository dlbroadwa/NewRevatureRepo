var map;
var marker;
var markerSet = {};
var nearbyMarkerSet = [];
//var popup = L.popup();
var currentCoordinates = [51.5074, -0.1278];
var flexibleMessage = "Location Found!";
var databaseCoordinates = [51, -0.1278];
var databaseFlexibleMessage = "Location Found!";
var yellowIcon = new L.Icon({
    iconUrl: 'https://cdn.rawgit.com/pointhi/leaflet-color-markers/master/img/marker-icon-2x-yellow.png',
    shadowUrl: 'https://cdnjs.cloudflare.com/ajax/libs/leaflet/0.7.7/images/marker-shadow.png',
    iconSize: [25, 41],
    iconAnchor: [12, 41],
    popupAnchor: [1, -34],
    shadowSize: [41, 41]
});
var redIcon = new L.Icon({
    iconUrl: 'https://cdn.rawgit.com/pointhi/leaflet-color-markers/master/img/marker-icon-2x-red.png',
    shadowUrl: 'https://cdnjs.cloudflare.com/ajax/libs/leaflet/0.7.7/images/marker-shadow.png',
    iconSize: [25, 41],
    iconAnchor: [12, 41],
    popupAnchor: [1, -34],
    shadowSize: [41, 41]
});
var blueIcon = new L.Icon({
    iconUrl: 'https://cdn.rawgit.com/pointhi/leaflet-color-markers/master/img/marker-icon-2x-blue.png',
    shadowUrl: 'https://cdnjs.cloudflare.com/ajax/libs/leaflet/0.7.7/images/marker-shadow.png',
    iconSize: [25, 41],
    iconAnchor: [12, 41],
    popupAnchor: [1, -34],
    shadowSize: [41, 41]
});

//Creates a marker and popup at the designated coordinates
function createMarker(coordinates)
{
    marker = L.marker(coordinates, {icon: redIcon}).addTo(map);
    marker.bindPopup(flexibleMessage).openPopup();
    markerSet = marker;
}

//Creates a marker and popup for recommended places nearby
function createMarkerForNearby(coordinates) {
	marker = L.marker(coordinates, {icon: blueIcon}).addTo(map);
    marker.bindPopup(nearbyFlexibleMessage).openPopup();
    nearbyMarkerSet.push(marker);
}

//Creates green marker for locations from the database
function createMarkerForDB(coordinates)
{
    marker = L.marker(coordinates, {icon: yellowIcon}).addTo(map);
    marker.bindPopup(databaseFlexibleMessage).openPopup();
    nearbyMarkerSet.push(marker);
}

//Creates the map
function createMap()
{
    map = L.map('map').setView(currentCoordinates, 13);
    L.tileLayer('https://api.mapbox.com/styles/v1/{id}/tiles/{z}/{x}/{y}?access_token=pk.eyJ1IjoianByYWdhc2EiLCJhIjoiY2thNWJnaXczMGN3dTNucXlkMGVhcGFocyJ9.wLsye5QSK_x3ho7rAXMnTg', {
    attribution: 'Map data &copy; <a href="https://www.openstreetmap.org/">OpenStreetMap</a> contributors, <a href="https://creativecommons.org/licenses/by-sa/2.0/">CC-BY-SA</a>, Imagery © <a href="https://www.mapbox.com/">Mapbox</a>',
    id: 'mapbox/streets-v9',
    maxZoom: 16,
    tileSize: 512,
    zoomOffset: -1,
    accessToken: 'pk.eyJ1IjoianByYWdhc2EiLCJhIjoiY2thNWJnaXczMGN3dTNucXlkMGVhcGFocyJ9.wLsye5QSK_x3ho7rAXMnTg'
    }).addTo(map);
}
//map.locate({setView: })
//Creates map and prompts user if they would like to go to their location
createMap();

function userLocation()
{
    // if(navigator.geolocation)
    // {
    //     navigator.geolocation.getCurrentPosition(function (position) {
    //         currentCoordinates = [position.coords.latitude, position.coords.longitude]
    //         map.flyTo(currentCoordinates, 10);
    //         marker = L.marker(currentCoordinates).addTo(map);
    //         marker.bindPopup("     You     ").openPopup();
    //     },errorCallBack)
    // }
    // else
    // {
    //     errorCallBack();
    // }
    currentCoordinates = [38.2700, -100.8603];
    map.flyTo(currentCoordinates, 4);
    marker = L.marker(currentCoordinates).addTo(map);
    markerSet = marker;
    marker.bindPopup("<h4 style='text-align: center; border-radius: 1em;'>See if there is a post in your area!</h4>").openPopup();
}

userLocation();

function getDataInfo(city, state)
{
    var data = {city, state};
    var xhr = new XMLHttpRequest();

    xhr.addEventListener("readystatechange", function ()
    {
        if (this.readyState === this.DONE && this.status === 200)
        {
            let responses = JSON.parse(this.responseText);
            responses.forEach((element) => {
                databaseCoordinates = [element.location.latitude, element.location.longitude];
                databaseFlexibleMessage = "<b>" + element.location.name + "</b><br>" + "visited by " + element.userID +
                	"<br><br> Rating: " + element.rating + "/5" +
                    "<br><br><b>" + element.title + "</b>" +
                    "<br>" + element.body;

                createMarkerForDB(databaseCoordinates);
            })
        }
    });

    xhr.open("POST", "get_posts");
    xhr.send(JSON.stringify(data));
}

function getAllData()
{
    var xhr = new XMLHttpRequest();

    xhr.addEventListener("readystatechange", function ()
    {
        if (this.readyState === this.DONE && this.status === 200)
        {
            let responses = JSON.parse(this.responseText);
            responses.forEach((element) => {
                    databaseCoordinates = [element.location.latitude, element.location.longitude];
                    databaseFlexibleMessage = "<b>" + element.location.name + "</b><br>" + "visited by " + element.userID +
                	"<br><br> Rating: " + element.rating + "/5" +
                    "<br><br><b>" + element.title + "</b>" +
                    "<br>" + element.body;
                    createMarkerForDB(databaseCoordinates);
                    document.getElementById('generateAllPosts').value = "Done!";
            })
        }
    });
    xhr.open("POST", "get_all_posts");
    xhr.send();
}

document.getElementById('generateAllPosts').addEventListener('click', () => {
        document.getElementById('generateAllPosts').value = "Getting all posts.....";
        getAllData()
})

function RelocateForAutoComplete() {
        map.removeLayer(markerSet);
        map.flyTo(currentCoordinates, 10);
        createMarker(currentCoordinates);
}

var placeInfo = {
    locality: 'long_name',
    sublocality_level_1: 'long_name',
    administrative_area_level_1: 'short_name'
};
var cityState = {
    locality: null,
    administrative_area_level_1: null,
    sublocality_level_1: null
}

let options = {
	    types: ['(regions)'],
	    componentRestrictions: {country: 'us'}
	}

function callbackFunction() {
	autoCompleteInfo();
	initPlacesService();
}

function autoCompleteInfo()
{
    var input = document.querySelector('#input_id')
    var coords;
    var autoComplete = new google.maps.places.Autocomplete(input, options);
    autoComplete.setFields(['address_component', 'geometry', 'adr_address']);
    google.maps.event.addListener(autoComplete, 'place_changed', function () {
        var place = autoComplete.getPlace();
        cityState.locality = undefined;
        cityState.administrative_area_level_1 = undefined;
        cityState.sublocality_level_1 = undefined;
        for(let i = 0; i < place.address_components.length; i++)
        {
            let addressType = place.address_components[i].types[0];
            if(placeInfo[addressType])
            {
                cityState[addressType] = place.address_components[i][placeInfo[addressType]];
            }
        }
        let city;
        if (!cityState.locality)
        	city = cityState.sublocality_level_1;
        else
        	city = cityState.locality;
        let state = cityState.administrative_area_level_1;
        coords = [place.geometry.location.lat(), place.geometry.location.lng()];
        currentCoordinates = coords;
        flexibleMessage = place.adr_address;
        RelocateForAutoComplete();
        getDataInfo(city, state);
        document.getElementById("id_forPost").innerHTML = '';
        sendInfoOver(city, state);
        if (!city || !state)
        	document.getElementById("forNearByplaces").innerHTML = `<br><h4 style="text-align: center"><i>No places nearby.</i></h4>`;
        else
        	pointOfInterest(coords[0], coords[1]);
        nearbyMarkerSet.forEach(function(entry) {
        	map.removeLayer(entry);
        });
        nearbyMarkerSet = [];
        document.getElementById('generateAllPosts').value = 'Get All Posts';
    })

    google.maps.event.addDomListener(window, 'load', getPlacesAutoComplete);
}

//console.log(states[0].name);
//let count = 0;
// states.forEach((element) => {
//     getData(states[count].name, states[count].abbreviation);
//     count++;
// })

// function doADelayAndRelocate() {
//     setTimeout(function () {
//         console.log(databaseCoordinates)
//         console.log(databaseFlexibleMessage);
//         map.panTo(databaseCoordinates);
//         map.flyTo(databaseCoordinates, 10);
//         createMarkerForDB(databaseCoordinates);
//     }, 500)
// }

//doADelayAndRelocate();

//     //console.log(element);
//     // const html =
//     //     `
//     //     <img src="${element.src}"/>
//     //     `
//     // document.querySelector('.mapPlaces').innerHTML = html;

//Shows coordinates when clicked
// function onMapClick(e)
// {
//     currentCoordinates = e.latlng;
//     createMarker(currentCoordinates)
//     //popUp(e);
// }

//map.on('click', onMapClick);

//Creates a popup, it is binded to the marker
// function popUp(e)
// {
//     //popup.setLatLng(e.latlng).setContent(e.latlng.toString()).openOn(map);
//
//     // let choice = prompt("Would you like to post a message to this location?").toString();
//
//     // if(choice == 'yes' || choice == 'Yes')
//     // {
//     //let message = prompt('What is your message?');
//       marker.bindPopup("You").openPopup();
//     // }
//     // else
//     // {
//     //     marker.bindPopup("<br>" + e.latlng.toString() + "</br>").openPopup();
//     // }
// }

// let city = "New York";
// let state = "NY";
// let data = {city, state}
// let latitude;
// let longitude;
