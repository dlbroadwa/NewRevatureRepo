package data;

import models.Maintenance_Ticket;
import utils.ConnectionUtil;
import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class SQLDatabaseMaintenance_Ticket implements GenericDAO<Maintenance_Ticket,Integer> {//Start of SQLDatabaseMaintenance_Ticket
//Instance Variables
    private static ConnectionUtil connectionUtil;

//Constructors
    public SQLDatabaseMaintenance_Ticket(ConnectionUtil connectionUtil){
        this.connectionUtil = connectionUtil;
    }

//Methods
    public List<Maintenance_Ticket> findAll() {//Start of findAll method
        List<Maintenance_Ticket> results = null;

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
                    Date start = rs.getDate("date_made");
                    Date end = rs.getDate("date_finished");
                    results.add(new Maintenance_Ticket(mainId, attractionId, employeeId,status, description,start, end));
                }
        }
        catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return results;
    }//End of findAll method


    public boolean add(Maintenance_Ticket newObj) {//Start of add method

        return false;
    }//End of add method


    public Maintenance_Ticket findByID(Integer integer) {//Start of findByID method
        Maintenance_Ticket result = null;

        String sql ="Select * from " + connectionUtil.getDefaultSchema()+".maintenance_ticket where maintenance_ticketid = ?";

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
                    result.setStartDate(rs.getDate("date_made"));
                    result.setEndDate(rs.getDate("date_finished"));
                }//End of first if
            }//End of second try
        }//End of first try
        catch (SQLException throwables) {//Start of catch
            throwables.printStackTrace();
        }//End of catch

        return result;
    }//End of findById method

    public Maintenance_Ticket findByAttraction(Integer integer){
        Maintenance_Ticket result = null;

        String sql ="Select * from " + connectionUtil.getDefaultSchema()+".maintenance_ticket where attractionid = ?";

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
                    result.setStartDate(rs.getDate("date_made"));
                    result.setEndDate(rs.getDate("date_finished"));
                }//End of first if
            }//End of second try
        }//End of first try
        catch (SQLException throwables) {//Start of catch
            throwables.printStackTrace();
        }//End of catch

        return result;
    }

    public boolean update(Integer integer, Maintenance_Ticket newObj) {//Start of update method
        return false;
    }//End of update method


    public boolean remove(Integer integer) {//Start of remove method
        return false;
    }//End of remove method

}//End of SQLDatabaseMaintenance_Ticket
