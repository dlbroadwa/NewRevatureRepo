package dto;

import models.Maintenance_Ticket;

import java.util.ArrayList;

public class MaintenanceTicketWrapper {

    private ArrayList<Maintenance_Ticket> tickets;

    public MaintenanceTicketWrapper() {

        tickets = new ArrayList<>();
    }

    public void addTicket(Maintenance_Ticket maintenance_ticket) {
        tickets.add(maintenance_ticket);
    }

    public void setTickets(ArrayList<Maintenance_Ticket> tickets) {
        this.tickets = tickets;
    }

    public ArrayList<Maintenance_Ticket> getTickets(){
        return tickets;
    }

}
