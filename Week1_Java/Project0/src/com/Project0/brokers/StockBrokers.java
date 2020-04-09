package com.Project0.brokers;
//***************************************************************************//





//**************************************************************************//


public class StockBrokers {
    private class Brokers {
        private String firstName;
        private String lastName;
        private Integer brokerID;

        private Brokers Brokers(String[] arguments) {
            this.firstName = arguments[0];
            this.lastName = arguments[1];
            try {
                this.brokerID = new Integer(arguments[2]);
            } catch (Exception e) {
                e.printStackTrace();
                System.out.println("ID defaulted to next 0");
                this.brokerID = 0;
            }

            return this;
        }
    }
}