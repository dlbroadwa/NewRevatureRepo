const functions = {
    add: (num1, num2) => num1 + num2,
    marker: (a, b) => [a, b],
    errorHandler: (error) => {return true},
    domManipulatorNearby: (results, status) => {
        if(results >= 0 && status == 200)
        {
            return true;
        }
        else
        {
            return false;
        }
    },
    initPlacesService: () => {return {}},
    someCityLikeNewyorkReturnNullHandlerFunction: (place) => {
        let placeData = [place[0], place[1]];
        let city = placeData[0];
        let state = placeData[1];
        let address = [city, state]
        return address;
    },
    sendInfoOver: (x, y) => {return [x, y]},
    getDataForPlaces: (readyState, status) => {
        if(readyState === true && status === 200)
        {
            return true;
        }
        else
        {
            return false;
        }
    },
    postFromDatabase: (placeInfo) => {
        let newObj = {
            name: placeInfo.name,
            id: placeInfo.id,
            rating: placeInfo.rating,
            title: placeInfo.title,
            body: placeInfo.body
        }
        let flag = false;
        if(placeInfo.name === newObj.name && placeInfo.id === newObj.id && placeInfo.rating === newObj.rating && placeInfo.title === newObj.title && placeInfo.body === newObj.body)
        {
            flag = true;
        }
        return flag;
    },
    createMarkerForNearby: (a, b) => {
        return [a, b];
    },
    createMarkerForDB: (place) => {
        return [place[0], place[1]];
    },
    createMap: (obj) => {
        if(obj != null)
        {
            return true;
        }
        else
        {
            return false;
        }
    },
    userLocation: (location) => {
        let lat = location[0];
        let lng = location[1];
        return [lat, lng];
    },
    getDataInfo: (city, state) => {
        return city === 'New York' && state === 'NY';
    },
    getAllData: (places) => {
        let count = 0;
        places.forEach(() => {
            count++;
        })
        return count;
    },
    autoCompleteInfo: (places) => {
        let count = 0;
        places.forEach(() => {
            count++;
        })
        return count;
    },
    relocateForAutoComplete: (a, b) => {
        let lat = a;
        let lng = b;
        return [a, b]
    }
};

module.exports = functions;
