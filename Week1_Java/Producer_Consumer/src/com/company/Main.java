package com.company;

public class Main {

    public static void main(String[] args) {
        Sales PolandFlight = new Sales();

        Thread flight = new Thread(new Runnable() {
            @Override
            public void run() {
                try{
                    PolandFlight.GenerateTickets(1);
                } catch (InterruptedException e){
                    e.printStackTrace();
                }
            }
        });

        Thread travel = new Thread(new Runnable() {
            @Override
            public void run() {
                try{
                    PolandFlight.BuysTicket(1);
                } catch (InterruptedException e){
                    e.printStackTrace();
                }
            }
        });

        Thread purchase = new Thread(new Sales);

	// write your code here
    }
}
