let ticketCache = 'active';
let mainCont = document.getElementById('maintenance');
const httpRequest = new XMLHttpRequest();

function getTickets(ticketVal){

    if (!httpRequest) {
        console.log('Failed to create an XMLHttpRequest instance');
        displayTickets(null);
        return null;
    }
	httpRequest.onreadystatechange = () => {
	    if (httpRequest.readyState === 4 && httpRequest.status === 200) {
	    	let response = JSON.parse(httpRequest.response);
            console.log(httpRequest.response)
            ticketCache = ticketVal;
            localStorage.setItem('ticketCache', JSON.stringify(ticketCache));
            displayTickets(response);
	    }
	};
	httpRequest.open("GET","maintenanceTicketServlet");
    httpRequest.setRequestHeader('find',ticketCache);
    httpRequest.send();
}

function displayTickets(tickets){
  clearDisplay();
    const errMsg = 'Failed to load maintenance tickets';
    if (tickets === null || tickets === undefined) {
        mainCont.innerText = errMsg;
    }
    else {
    	let ticketArr = tickets;
        for(let ticket of tickets){
        	let div = document.createElement('div');
            div.innerHTML = '</br> Maintenance Ticket ID#: '+ ticket.mainId
                          +'<br/> Attraction ID#: '+ ticket.attractionId
                          +'<br/> Status of Attraction: '+ ticket.status
                          +'<br/> Employee ID#: ' + ticket.employeeId
                          +'<br/> Creation Date: '+ ticket.startDate;
                          +'<br/> Resolution Date: '+ ticket.endDate;
            mainCont.appendChild(div);
        }
    }
}


function clearDisplay() {
    document.getElementById('maintenance').innerHTML = '';
}

function applyFilter(ticketLoc) {
    if (!ticketCache) {
        console.log('How did this happen?');
        return;
    }

    if (ticketLoc.length != 0) {
        clearDisplay();
        getTickets(ticketLoc);
    }
    else {
        getTickets(ticketCache);
    }

}

function init() {
    getTickets(ticketCache);
}
init();