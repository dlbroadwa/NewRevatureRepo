const functions = require('./functions');

test('Adds 2 + 2 to equal 4', () => {
   expect(functions.add(2, 2)).toBe(4);
});

test('Gets coordinates', () => {
   expect(functions.marker(-150, 222)).toStrictEqual([-150, 222]);
})

test('Should return true', () => {
   const error = 'error';
   expect(functions.errorHandler(error)).toBe(error === 'error');
})

test('Should return true if result exists', () => {
   const results = ['New York', "Florida", 'California'];
   let ResultsCount = 0;
   results.forEach(() => ResultsCount++);
   expect(functions.domManipulatorNearby(ResultsCount, 200)).toBe(true);
   ResultsCount = 0;
   expect(functions.domManipulatorNearby(ResultsCount, 200)).not.toBe(false);
})

test('Should return true of the object exits', () => {
   let emptyObject = {};
   expect(functions.initPlacesService()).toEqual(emptyObject);
})

test('Should return true if the data matches', () => {
   let place = ["New York", "NY"];
   expect(functions.someCityLikeNewyorkReturnNullHandlerFunction(place)).toEqual(place);
})

test('Return should be equal', () => {
   let Data = ["California", "CA"];
   expect(functions.sendInfoOver(Data[0], Data[1])).toEqual(Data);
})

test('Returns true if the ready state and status are correct', () => {
   let readyState = true;
   let status = 200;
   expect(functions.getDataForPlaces(readyState, status)).toBe(true);
})

test('Returns true if the objects are the same', () => {
   let obj = {
      name: 'New York',
      id: 'Josh',
      rating: 5,
      title: 'Best Chicken Ever',
      body: 'Highly Recommend'
   }
   expect(functions.postFromDatabase(obj)).toBe(true);
})

test('Gets coordinates', () => {
   expect(functions.createMarkerForNearby(-150, 222)).toStrictEqual([-150, 222]);
})

test('True if coordinates are matching', () => {
   let place = [111, -222];
   expect(functions.createMarkerForDB(place)).toEqual(place);
})

test('Returns true of object exists', () => {
   let obj = {itemInObject:null};
   expect(functions.createMap(obj)).toEqual(true);
})

test('Returns true of coordinates are similar', () => {
   let obj = [-111, 222];
   expect(functions.userLocation(obj)).toEqual(obj);
})

test('Returns true if the city and state are similar', () => {
   let city = "New York";
   let state = "NY";
   expect(functions.getDataInfo(city, state)).toBe(true);
})

test('Returns the number of elements', () => {
   let places = ['New York', 'California'];
   expect(functions.getAllData(places)).toBe(2);
})

test('Returns the number of elements', () => {
   let places = ['New York', 'California'];
   expect(functions.autoCompleteInfo(places)).toBe(2);
})

test('Should return similar coordinates', () => {
   let a = 222;
   let b = -400;
   let obj = [a, b];
   expect(functions.relocateForAutoComplete(a, b)).toEqual(obj);
})