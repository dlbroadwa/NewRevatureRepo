package com.company;

public class Main {

    public static void main(String[] args) throws InterruptedException {
        Sales PolandFlight = new Sales();

        Thread producer = new Thread(new Runnable() {
            @Override
            public void run() {
                try{
                    PolandFlight.generateTickets(1);
                } catch (InterruptedException e){
                    e.printStackTrace();
                }
            }
        });

        Thread consumer = new Thread(new Runnable() {
            @Override
            public void run() {
                try{
                    PolandFlight.buysTicket(1);
                } catch (InterruptedException e){
                    e.printStackTrace();
                }
            }
        });

        producer.start();//Starts threads
        consumer.start();

        producer.join(); //make everyone wait for producer thread to fin
        consumer.join();

	// write your code here
    }
}
