package models;

import java.util.Date;

/**
 *  Project 2:<br>
 * <br>
 *  Maintenance_Ticket is used to model a Maintenance_Ticket object
 *
 *  <br> <br>
 *  Created: <br>
 *     May 11, 2020 Paityn Maynard<br>
 *     With assistance from: <br>
 *  Modifications: <br>
 *
 *       <hr>
 *    Added toString for JSON RESPONSES
 *      Jean Aldoph II
 *      <hr>
 *
 * <br>
 *  @author
 *  @version 11 May 2020
 */
public class Maintenance_Ticket {//Start of Maintenance_Ticket Class
//Instant Variables
    int mainId, attractionId, employeeId;
    String status, description;
    Date startDate, endDate;
//Constructors
    public Maintenance_Ticket(){}

    /**
     * Used to create a new Maintenance_Ticket object
     * @param mainId
     * @param attractionId
     * @param employeeId
     * @param status
     * @param description
     * @param startDate
     * @param endDate
     */
    public Maintenance_Ticket(int mainId, int attractionId, int employeeId, String status, String description,Date startDate, Date endDate){
        this.mainId = mainId;
        this.attractionId = attractionId;
        this.employeeId = employeeId;
        this.status = status;
        this.description=description;
        this.startDate = startDate;
        this.endDate = endDate;
    }

//Getters
    public int getMainId() {
        return mainId;
    }

    public int getAttractionId() {
        return attractionId;
    }

    public int getEmployeeId() {
        return employeeId;
    }

    public String getStatus() {
        return status;
    }

    public String getDescription() {
        return description;
    }

    public Date getStartDate() {
        return startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

//Setters
    public void setMainId(int mainId) {
        this.mainId = mainId;
    }

    public void setAttractionId(int attractionId) {
        this.attractionId = attractionId;
    }

    public void setEmployeeId(int employeeId) {
        this.employeeId = employeeId;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    @Override
    public String toString() {
        return "{mainId=" + mainId +
                ",attractionId=" + attractionId +
                ",employeeId=" + employeeId +
                ",status='" + status +
                ",description='" + description +
                ",startDate=" + startDate +
                ",endDate=" + endDate +
                '}';
    }
}//End of Maintenance_Ticket Class
