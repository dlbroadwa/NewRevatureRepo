package main.java.models;

import java.util.Date;

/**
 *  Project 2:<br>
 * <br>
 *  The Ticket class serves as a representation of a real-world ticket used for interacting with the system.
 *  	Ticket instances hold information of its real-world counterpart as variables.
 *
 *  <br> <br>
 *  Created: <br>
 *     11 May 2020, Barthelemy Martinon<br>
 *     With assistance from: <br>
 *  Modifications: <br>
 *     11 May 2020, Barthelemy Martinon,    Created class.
 * <br>
 *  @author Barthelemy Martinon   With assistance from:
 *  @version 11 May 2020
 */
public class Ticket {
    // Instance Variables
    private int ticketID;
    private int customerID;
    private int accessLevel;
    private Date startDate;
    private Date endDate;

    // Constructor
    public Ticket(int ticketID, int customerID, int accessLevel, Date startDate, Date endDate) {
        this.ticketID = ticketID;
        this.customerID = customerID;
        this.accessLevel = accessLevel;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    // Getter Methods
    public int getTicketID() { return ticketID; }
    public int getCustomerID() { return customerID; }
    public int getAccessLevel() { return accessLevel; }
    public Date getStartDate() { return startDate; }
    public Date getEndDate() { return endDate; }

    // Setter Methods
    public void setTicketID(int ticketID) { this.ticketID = ticketID; }
    public void setCustomerID(int customerID) { this.customerID = customerID; }
    public void setAccessLevel(int accessLevel) { this.accessLevel = accessLevel; }
    public void setStartDate(Date startDate) { this.startDate = startDate; }
    public void setEndDate(Date endDate) { this.endDate = endDate; }

    // Methods
}