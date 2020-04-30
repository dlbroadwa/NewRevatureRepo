const OPERATION_NAME = "findItemsByKeywords";

var filterarray = [
    {"name":"MaxPrice",
     "value":"25",
     "paramName":"Currency",
     "paramValue":"USD"},
    {"name":"FreeShippingOnly",
     "value":"true",
     "paramName":"",
     "paramValue":""},
    {"name":"ListingType",
     "value":["AuctionWithBIN", "FixedPrice"],
     "paramName":"",
     "paramValue":""},
    ];


// Replace MyAppID with your Production AppID

document.body.appendChild(s);
var urlfilter = "";

    // Parse the response and build an HTML table to display search results
function _cb_findItemsByKeywords(root) {  
    var items = root.findItemsByKeywordsResponse[0].searchResult[0].item || [];
    var html = [];
    html.push('<table width="100%" border="0" cellspacing="0" cellpadding="3"><tbody>');
    for (var i = 0; i < items.length; ++i) {
      var item     = items[i];
      var title    = item.title;
      var pic      = item.galleryURL;
      var viewitem = item.viewItemURL;
      if (null != title && null != viewitem) {
        html.push('<tr><td>' + '<img src="' + pic + '" border="0">' + '</td>' +
        '<td><a href="' + viewitem + '" target="_blank">' + title + '</a></td></tr>');
      }
    }
    html.push('</tbody></table>');
    document.getElementById("results").innerHTML = html.join("");
}  // End _cb_findItemsByKeywords() function

function  buildURLArray() {
    // Iterate through each filter in the array
    for(var i=0; i<filterarray.length; i++) {
      //Index each item filter in filterarray
      var itemfilter = filterarray[i];
      // Iterate through each parameter in each item filter
      for(var index in itemfilter) {
        // Check to see if the paramter has a value (some don't)
        if (itemfilter[index] !== "") {
          if (itemfilter[index] instanceof Array) {
            for(var r=0; r<itemfilter[index].length; r++) {
            var value = itemfilter[index][r];
            urlfilter += "&itemFilter\(" + i + "\)." + index + "\(" + r + "\)=" + value ;
            }
          }
          else {
            urlfilter += "&itemFilter\(" + i + "\)." + index + "=" + itemfilter[index];
          }
        }
      }
    }
  }  // End buildURLArray() function
  
  // Execute the function to build the URL filter
  buildURLArray(filterarray);