// create variables to be called
var url =null;
var address ;
var arr ;
const inText =document.querySelector("#input_id");





//// Add an Event Listener for each time there is in input address
//inText.addEventListener(
//    'focusout',
//    function () {
//        console.log("testing")
//        console.log(inText.value)
//        arr =inText.value;
//
//        //remove all the whitesplace and cammas from the address
//        address =arr.replace(/\s/g, '%20').replace(/,/g, "");
//        console.log("testing:" + arr)
//        console.log("Address Testing: " + address)
//
//        //const url ='https://maps.googleapis.com/maps/api/place/textsearch/json?query='+arr+"&key="+key;
//       // const url =''+arr+"&key="+key;
////          pointOfInterest(place.geometry.location.lat(),place.geometry.location.lng());
////          console.log("From otherSide: " + place.geometry.location.lat(),place.geometry.location.lng())
//
////        getTheAPI(url);
//    })

const key ="AIzaSyCTzLnorH3gTmgbe5oSsUJYLUxxWdkxdMQ";

//let urlTest ="https://maps.googleapis.com/maps/api/place/textsearch/xml?query=restaurants+in+Miami&key=AIzaSyCTzLnorH3gTmgbe5oSsUJYLUxxWdkxdMQ";

var latitude,longitude;

// this is the Dom Manipulation for the first on to display the LatLng
var DOMManipulator = (data)=>{

    latitude =data.results[0].geometry.location.lat;
    longitude =data.results[0].geometry.location.lng;



    let dom = document.querySelector("#forLatlng");

   let createDom = `
  <section class="card bg-light mb-3" style="text-align:center;">

   <img  src=${data.results[0].icon}>
      <p class="card-title">${data.results[0].formatted_address} </P>
      <P class="card-text">Lat:${latitude} </p>
      <P class="card-text">Lng :${longitude} </p>

  </section>



   `;
dom.innerHTML =createDom;

}

let service;
function initPlacesService() {
	service = new google.maps.places.PlacesService(document.createElement('div'));
}


// work with the Latitude and the Longitude to pass it at the url call urlNearby
//let lat = place.geometry.location.lat();
//lwt lng = place.geometry.location.lng();
var pointOfInterest = (lat, lng)=>{
	let point = new google.maps.LatLng(lat,lng);
	let request = {
			location: point,
			radius: 10000,
			type: 'restaurant'
	};
	service.nearbySearch(request, DOMManipulatorNearBy);
}

//function myPlaces(pl){
//let some = pl.address_components
//
//for(let i in some)
////console.log("What is it:"+some[i].placeInfo + "and :"+ i)
//
//}

// get the near by location


// Manipulate the Dom elements with data From Ajax API Request
let nearbyFlexbileMessage;
function DOMManipulatorNearBy(results, status) {
	if (status == google.maps.places.PlacesServiceStatus.OK) {
		document.getElementById("forNearByplaces").innerHTML = '';
		let count = 0;
	    for(let i =0 ; i<results.length ; i++){
	    	if (results[i] != null && results[i].business_status != undefined) {
		        dom =`
		              <table class="card text-white bg-info " id="near_by">

		              <tr class="card-text"><td onError="onErrorHandler(this)"><strong>${results[i].name}</td></tr>
		              <tr class="card-text"><td>Type: ${results[i].types[0]}  </td></tr>
		              <tr class="card-text"><td> Price level: ${results[i].price_level}  </td></tr>
		              <tr class="card-text"><td> Rating: ${results[i].rating} / 5</td></tr>
		              <tr class="card-text"><td>Total Ratings: ${results[i].user_ratings_total}  </td></tr>

		              <tr class="card-text"><td> <img style="width: 100%;" src= ${results[i].photos[0].getUrl()} > </td></tr>

		              </table>

		              `;
		        document.getElementById("forNearByplaces").innerHTML += dom;
		        nearbyFlexibleMessage = "<b>" + results[i].name;
                let resultCoords = [results[i].geometry.location.lat(), results[i].geometry.location.lng()];
		        createMarkerForNearby(resultCoords);
		        count++
		        if (count >= 4)
		        	break;
	    	}
	    }
	    if (document.getElementById("forNearByplaces").innerHTML == '')
	    	document.getElementById("forNearByplaces").innerHTML = `<br><h4 style="text-align: center"><i>No places nearby.</i></h4>`;
	}
}

function onErrorHandler(error){
    error.innerHTML = "undefined";
    return true;

}
/////////////////////////// list-group-item
// setting up the option to only auto-complete addresses instead of places

// getting the field input auto complete
//function getPlacesAutoCompleteInfo(){
//
//    let input = document.querySelector("#input_id");
//    auto_text = new google.maps.places.Autocomplete(input,options);
//
//    auto_text.setFields(['address_component' , 'geometry']);
////auto_text.setFields(['city']);
//
//// pointOfInterest(auto_text.geometry.location.lat(),auto_text.geometry.location.lng());
//
//console.log("anything")
//
////    auto_text.addListener('place_changed',function(){
////    console.log("From otherSide NEW: " + auto_text.getPlace().geometry.location.lat() )
////    })
//
//
//}
//////////////////////

//window.document.onload = function(){
//var h = document.getElementsByTagName('head')[0];
//
//var scr = document.createElement('script')
//scr.src = 'https://maps.googleapis.com/maps/api/js?key=AIzaSyCTzLnorH3gTmgbe5oSsUJYLUxxWdkxdMQ&libraries=places&callback=getPlacesAutoCompleteAuto';
//h.appendChild(scr);
//
//}


////////////////////


//var address_precision;




///////////  Calling the Second API To filter the address ///////////////
//var filterAddress =()=>{
//
//    let stateData , city;

   // var place = autos.getPlace(); // getting the place address information
//console.log("From otherSide NEW: " + place )

//// looping to the place with the Address_component that we precise earlier
//
//    for( let i = 0 ; i < place.address_components.length ; i++){
//        var addressType = place.address_components[i].types[0];
//
//        if(componentForm[addressType]){
//
//            address_precision = place.address_components[i][componentForm[addressType]];
//            //document.getElementById(addressType).value = address_precision;
//
//// push the details to the array
//            arrAddress.push(address_precision);
//        }



//city = arrAddress[1];
// stateData = arrAddress[2];

  //  }
//////////// Passing data to the object and also call the AJAX Function /////////////
//    objectData(arrAddress[1], arrAddress[2])
//    getDataForPlaces();

//}

var arrAddress = [];

function someCityLikeNewyorkReturnNullHandlerFunction(autoTextFromPlaces_js){

	arrAddress.push(autoTextFromPlaces_js);

	 city = arrAddress[1];
	 state = arrAddress[2];



	 console.log("checking Address:" + city + " St :" + state)


	}


///////////////////////////// Setup Object to be passed to query from Database /////////////////////////////////
let dataFromDAO = null;

function sendInfoOver (x,y){

    city = x;
    state = y;
    //picker = arr.split(',')[1]; // extract the city name after the first commas at the line
    //
//        if(city == null || city == 'undefined')
//        {
//        city = picker;
//        }

    //city = "Palmdale";
    //state = "CA";

    //cities = "New York";
    //st = "NY";
        console.log("(City & State): " + city + ", "+ state);

        dataFromDAO = {city, state};

    ////// Set Timer to let the object finish its assignment before calling the request Function
             setTimeout(function(){  getDataForPlaces();}, 100);
    }

////////////////////// Control of the  Data with the AJAX Call /////////////////////////////



function getDataForPlaces()
{
    var xhr = new XMLHttpRequest();

    xhr.addEventListener("readystatechange", function ()
    {

        if (this.readyState === this.DONE && this.status == 200)
        {
            let responses = JSON.parse(this.responseText);
//            let responses = this.responseText;
            //console.log("testing DB from Console" +responses.body)
            document.getElementById("id_forPost").innerHTML = '';
            responses.forEach((element) => {
                postFromDatabase(element);
            });
            if (responses.length == 0)
            	document.getElementById("id_forPost").innerHTML = `<br><h4 style="text-align: center"><i>No posts nearby.</i></h4>`;
        }
    });

    if (!dataFromDAO.city || !dataFromDAO.state)
    	document.getElementById("id_forPost").innerHTML = `<br><h4 style="text-align: center"><i>No posts nearby.</i></h4>`;
    xhr.open("POST", "get_posts");
    xhr.send(JSON.stringify(dataFromDAO));
}

///////////////////////////// Having control of the elements from the Database ///////////////////////////

let postFromDatabase = (post) =>{

	let url = window.location.protocol + "//" + window.location.hostname + ":30001/profile?username=" + post.userID;

    let domForPost =`

        <table class=" card text-white bg-warning mb-3" >

        <tr class="card-text"><td style= "padding-left:2%;"><b>${post.location.name}</b></td></tr>
        <tr class="card-text"><td style= "padding-left:2%;">Visited by <a href=${url} style="color: #967513">${post.userID}</a></td></tr>
        <tr class="card-text"><td style= "padding-left:2%;">Rating: ${post.rating} / 5</td></tr>
        <tr class="card-text"><td style= "padding-left:2%;"><b><u>${post.title}</u></b></td></tr>
        <tr class="card-text"><td style= "padding-left:2%;">${post.body}</td></tr>




        <tr class="card-text"><td> <img style="width: 100%;" src= ${post.uri} > </td></tr>



        </table>

        `;

    document.getElementById("id_forPost").innerHTML += domForPost;
}





///////////////////////////////////// END of the Creation of the Dom Element ////////////////////




////////////////////////////////

