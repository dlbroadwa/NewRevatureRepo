const functions = require('./functions');


test('Adds 2 + 2 to equal 4', () => {
   expect(functions.add(2, 2)).toBe(4);
});

// test('tests if null', () => {
//    expect(shoppingApi.jQuery($.ajax(null))).toBe(!null);
// })