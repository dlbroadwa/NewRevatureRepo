package com.company;

import java.util.ArrayList;

public class Sales {
    public final ArrayList<Integer> TicketInventory= new ArrayList<>();

    public void GenerateTickets(int x) throws InterruptedException{
        synchronized (TicketInventory) {}
        while (TicketInventory.size()<5) {
            TicketInventory.add(x);
            System.out.println("Ticket " + x + " was added!");
        }

    }
    public void BuysTicket(int x) throws InterruptedException{
        while (TicketInventory.size()>= 1 ){
            TicketInventory.remove(TicketInventory.indexOf(x));
            System.out.println("Ticket " + x + " was purchased!");
        }

    }


}
