package data;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;

import models.Maintenance_Ticket;
import utils.ConnectionUtil;

/**
 *  Project 2:<br>
 * <br>
 *  SQLDatabaseMaintenance_Ticket
 *
 *  <br> <br>
 *  Created: <br>
 *     May 11, 2020 Paityn Maynard<br>
 *     With assistance from: <br>
 *  Modifications: added date type conversion - Joshua Brewer<br>
 *
 * <br>
 *  @author
 *  @version 11 May 2020
 */
public class SQLDatabaseMaintenance_Ticket implements GenericDAO<Maintenance_Ticket,Integer> {//Start of SQLDatabaseMaintenance_Ticket
//Instance Variables
    private ConnectionUtil connectionUtil;

//Constructors
    public SQLDatabaseMaintenance_Ticket(ConnectionUtil connectionUtil){
        this.connectionUtil = connectionUtil;
    }

//Methods
    public ArrayList<Maintenance_Ticket> findAll() {//Start of findAll method
        ArrayList<Maintenance_Ticket> results = null;

        String sql="Select * from "+ connectionUtil.getDefaultSchema()+".maintenance_tickets";
        try(Connection conn = connectionUtil.getConnection();
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(sql)){
                results = new ArrayList<>();

                while (rs.next()) {
                    int mainId = rs.getInt("maintenance_ticketid");
                    int attractionId = rs.getInt("attractionid");
                    int employeeId = rs.getInt("employeeid");
                    String description = rs.getString("description");
                    String status = rs.getString("status");
                    Date date_made_Date = new Date(rs.getDate("date_made").getTime());
                    LocalDateTime date_made = generateLocalDateTime(date_made_Date);
                    Date date_finished_Date;
                    LocalDateTime date_finished;
                    if(rs.getDate("date_finished") != null) {
                    	date_finished_Date = new Date(rs.getDate("date_finished").getTime());
                    	date_finished = generateLocalDateTime(date_finished_Date);
                    }else {
                    	date_finished_Date = null;
                    	date_finished = null;
                    }
                    
                    
                    results.add(new Maintenance_Ticket(mainId, attractionId, employeeId,status, description, date_made, date_finished));
                }
        }
        catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return results;
    }//End of findAll method


    public boolean add(Maintenance_Ticket ticket) {//Start of add method
        if (findByID(ticket.getMainId()) != null) {
            return false;
        }
        int addedRowCount = 0;
        String sql = "INSERT INTO " + connectionUtil.getDefaultSchema() +
                ".maintenance_tickets  (attractionid, employeeid, description, date_made, status)" +
                "values (?, ?, ?,?,?)";

        try (Connection conn = connectionUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {//Start of try
            ps.setInt(1, ticket.getAttractionId());
            ps.setInt(2, ticket.getEmployeeId());
            ps.setString(3, ticket.getDescription());
            Date date = generateDate(ticket.getStartDate());
            ps.setDate(4,generateSQLDate(date));
            ps.setString(5, ticket.getStatus());
            System.out.println(ps);

            addedRowCount = ps.executeUpdate();
        }//End of try
        catch (SQLException throwables) {//Start of catch
            throwables.printStackTrace();
        }//End of catch

        return addedRowCount == 1;

    }//End of add method


    public Maintenance_Ticket findByID(Integer integer) {//Start of findByID method
        Maintenance_Ticket result = null;

        String sql ="Select * from " + connectionUtil.getDefaultSchema()+".maintenance_tickets where maintenance_ticketid = ?";

        try (Connection conn = connectionUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {//Start of first try
            ps.setInt(1, integer);

            try (ResultSet rs = ps.executeQuery()) {//Start of second try
                if (rs.next()) {//Start of first if
                    result = new Maintenance_Ticket();
                    result.setMainId(rs.getInt("maintenance_ticketid"));
                    result.setAttractionId(rs.getInt("attractionid"));
                    result.setEmployeeId(rs.getInt("employeeid"));
                    result.setDescription(rs.getString("description"));
                    result.setStatus(rs.getString("status"));
                    Date date_made = new Date(rs.getDate("date_made").getTime());
                    result.setStartDate(generateLocalDateTime(date_made));
                    if(rs.getDate("date_finished") != null) {
                    	Date date_finished = new Date(rs.getDate("date_finished").getTime());
                    	result.setEndDate(generateLocalDateTime(date_finished));
                    }
                }//End of first if
            }//End of second try
        }//End of first try
        catch (SQLException throwables) {//Start of catch
            throwables.printStackTrace();
        }//End of catch

        return result;
    }//End of findById method

    public Maintenance_Ticket findByAttraction(Integer integer){//Start findByAttraction method

        Maintenance_Ticket result = null;

        String sql ="Select * from " + connectionUtil.getDefaultSchema()+".maintenance_tickets where attractionid = ?";

        try (Connection conn = connectionUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {//Start of first try
            ps.setInt(1, integer);

            try (ResultSet rs = ps.executeQuery()) {//Start of second try
                if (rs.next()) {//Start of first if
                    result=new Maintenance_Ticket();
                    result.setMainId(rs.getInt("maintenance_ticketid"));
                    result.setAttractionId(rs.getInt("attractionid"));
                    result.setEmployeeId(rs.getInt("employeeid"));
                    result.setDescription(rs.getString("description"));
                    result.setStatus(rs.getString("status"));
                    Date date_made = new Date(rs.getDate("date_made").getTime());
                    result.setStartDate(generateLocalDateTime(date_made));
                    if(rs.getDate("date_finished") != null) {
                    	Date date_finished = new Date(rs.getDate("date_finished").getTime());
                    	result.setEndDate(generateLocalDateTime(date_finished));
                    }
                }//End of first if
            }//End of second try
        }//End of first try
        catch (SQLException throwables) {//Start of catch
            throwables.printStackTrace();
        }//End of catch

        return result;
    }//End findByAttraction method

    
    public boolean update(Integer integer, Maintenance_Ticket newObj) {//Start of update method

        Maintenance_Ticket ticket  = new Maintenance_Ticket();
        int updatedRowCount = 0;
        String status = newObj.getStatus();
        String enddate = "now";

        String sql = "UPDATE " + connectionUtil.getDefaultSchema() +
                ".maintenance_tickets SET date_finished = ?, status = ? WHERE maintenance_ticketid=?";

        try (Connection conn = connectionUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
        	Date date_finished = generateDate(newObj.getEndDate());
            ps.setDate(1, generateSQLDate(date_finished));
            ps.setString(2, status);
            ps.setInt(3, newObj.getMainId());

            updatedRowCount = ps.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return updatedRowCount > 0;

    }//End of update method

    /**
     * Remove Unused in this class
     * @param integer
     * @return
     */
    public boolean remove(Integer integer) {//Start of remove method
        return false;
    }//End of remove method
    
    
    /**
     * Helper class to generate LocalDateTime objects from java.util.Date objects
     * @author Joshua Brewer
     * @param date
     * @return
     */
    private LocalDateTime generateLocalDateTime(Date date) {
        return date.toInstant()
        	      .atZone(ZoneId.systemDefault())
        	      .toLocalDateTime();
    }
    
    /**
     * Helper class to generate Date objects from LocalDateTime objects
     * @author Joshua Brewer
     * @param ldt
     * @return Date
     */
    private Date generateDate(LocalDateTime ldt) {
    	return java.sql.Timestamp.valueOf(ldt);
    }
    
    
    /**
     * Helper class to generate SQLDate objects from java.util.Date objects
     * @author Joshua Brewer
     * @param date
     * @return java.sql.Date
     */
    private java.sql.Date generateSQLDate(Date date){
    	return new java.sql.Date(date.getTime());
    }

}//End of SQLDatabaseMaintenance_Ticket
