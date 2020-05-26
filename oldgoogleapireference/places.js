// Api key: AIzaSyCE99IjK1-yA_T4PhBCdZ_whMVi72nKykk
var map;
var service;

function initMap()
{
    let infoWindow = new google.maps.InfoWindow;

    let options =
    {
        zoom: 14,
        center: {lat: 40.5383, lng:-89.3792 },
        mapTypeId: google.maps.MapTypeId.HYBRID
    }

    if(navigator.geolocation)
    {
        navigator.geolocation.getCurrentPosition(function(p){
            var position = {
                lat: p.coords.latitude,
                lng: p.coords.longitude
            };
            infoWindow.setPosition(position);
            infoWindow.setContent('You');
            infoWindow.open(map);
        }), function() {
            handleLocationError('Geolocation service failed', map.center())
        }
    }
    else
    {
        handleLocationError('No Geolocation Available', map.center());
    }

    function handleLocationError(content, position)
    {
        infoWindow.setPosition(position);
        infoWindow.setContent(content);
        infoWindow.open(map);
    }
    var map = new google.maps.Map(document.querySelector('.mapPlaces'), options)
    
    //Listen for click on map
    // google.maps.event.addListener(map, 'click', function(event){
    //     console.log({coords:event.latLng})
    //     addMarker({coords:event.latLng})
    // })

    //Array of markers
    var markers = 
    [
        {coords:options.center,
            // iconImage:'https://developers.google.com/maps/documentation/javascript/examples/full/images/beachflag.png',
            //content: '<h1>Landmass</h1>'
        },
        {coords:{lat: 28.5383, lng: -81.3792}}
    ];

    for(var i = 0; i < markers.length; i++)
    {
        addMarker(markers[i]);
    }


    // Add Marker Function
    function addMarker(props)
    {
        let marker = new google.maps.Marker({
            position:props.coords,
            map:map,
            //custom icon
            //icon:props.iconImage
        })

        //check for custom icon
        if(props.iconImage)
        {
            marker.setIcon(props.iconImage);
        }

        //check content
        if(props.content)
        {
            let infoWindow = new google.maps.InfoWindow({
                content: props.content
            });

            marker.addListener('click', function(){
                infoWindow.open(map, marker);
            })
        }
    }

    let input = document.getElementById('search');
    let searchBox = new google.maps.places.SearchBox(input);



    map.addListener('bounds_changed', function() {
        searchBox.setBounds(map.getBounds());
    })

    var markers = [];

    searchBox.addListener('places_changed', function() {
        var places = searchBox.getPlaces();
        if(places.length === 0)
        {
            return;
        }
        markers.forEach(function(m) {
            m.setMap(null);
        });
        markers = [];

        var bounds = new google.maps.LatLngBounds();
        places.forEach(function(p) {
            if(!p.geometry)
            {
                return;
            }
            markers.push(new google.maps.Marker({
                map: map,
                title: p.name,
                position: p.geometry.location,
            }))
            if(p.geometry.viewport)
            {
                bounds.union(p.geometry.viewport);
            }
            else
            {
                bounds.extend(p.geometry.location)
            }
        })
        map.fitBounds(bounds);
    })

    var ac = new google.maps.places.Autocomplete(document.getElementById('search'));
    function generateData(googleObject)
    {
        var html;
        google.maps.event.addListener(googleObject, 'place_changed', function(){
            
            var place = ac.getPlace();
            // console.log(place.formatted_address);
            // console.log(place.url);
            // console.log(place.geometry.location);
            if(place.formatted_address === undefined)
            {
                place.formatted_address = 'none';
            }

            
            html =
            `
            <h4>${place.name}</h4>
            <h4>${place.formatted_address === undefined ? null : place.formatted_address}"</h4>
            <h4>${place.formatted_phone_number === undefined ? 'Phone number unavailable' : place.formatted_phone_number}</h4>
            <h4>${place.rating === undefined ? 'Rating unavailable' : place.rating}</h4>
            <a href="${place.website === undefined ? 'Website unavailable' : place.website}">Check out website</a>
            `
            document.querySelector('.placesInfo').innerHTML = html;
        })   
    }
    
    generateData(ac);
}