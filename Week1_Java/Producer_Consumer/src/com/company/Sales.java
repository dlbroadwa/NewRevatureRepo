package com.company;

import java.util.ArrayList;

public class Sales {
    public final ArrayList<Integer> TicketInventory= new ArrayList<>();

    public void generateTickets(int x) throws InterruptedException{
        synchronized (TicketInventory) {
            while (true) {
                TicketInventory.add(x);
                System.out.println("Ticket " + x + " was added!");
            }

        }

    }
    public void buysTicket(int x) throws InterruptedException{
        synchronized (TicketInventory) {
            while (true) {
                TicketInventory.remove(TicketInventory.indexOf(x));
                System.out.println("Ticket " + x + " was purchased!");
            }

       }

    }

}
