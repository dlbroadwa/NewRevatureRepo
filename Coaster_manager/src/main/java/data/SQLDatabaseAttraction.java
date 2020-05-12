package data;

import models.Attraction;
import utils.ConnectionUtil;
import utils.PostgresConnectionUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 *  Project 2:<br>
 * <br>
 *  SQLDatabaseAttraction
 *
 *  <br> <br>
 *  Created: <br>
 *     May 11, 2020 Paityn Maynard<br>
 *     With assistance from: <br>
 *  Modifications: <br>
 *
 * <br>
 *  @author
 *  @version 11 May 2020
 */
public class SQLDatabaseAttraction implements GenericDAO<Attraction,Integer> {//Start of SQLDatabaseAttraction
//Instance Variables
    private static ConnectionUtil connectionUtil;

//Constructors
    public SQLDatabaseAttraction(ConnectionUtil connectionUtil){
        this.connectionUtil=connectionUtil;
    }

//Methods
    public List<Attraction> findAll() {//Start of findAll method
        List<Attraction> results = null;

        String sql = "Select name,attractionid,classificationid,imageurl,ratings,status  from "+
                      connectionUtil.getDefaultSchema()+".attractions left join " + connectionUtil.getDefaultSchema()+
                     ".maintenance_tickets on attractions.attractionid = maintenance_tickets.attractionid";

        try (Connection conn = connectionUtil.getConnection();
             Statement st = conn.createStatement();
             ResultSet rs = st.executeQuery(sql)) {//Start of try
            results = new ArrayList<>();

            while (rs.next()) {
                String name = rs.getString("name");
                int id = rs.getInt("attractionid");
                String status = rs.getString("status");
                String imageurl = rs.getString("imageurl");
                int rating = rs.getInt("ratings");

                results.add(new Attraction(name,status,imageurl,id,rating));
            }
        }//End of try
        catch (SQLException throwables) {//Start of catch
            throwables.printStackTrace();
        }//End of catch

        return results;

    }//End of findAll method


    public boolean add(Attraction newObj) {//Start of add method
        return false;
    }//End of add method


    public Attraction findByID(Integer integer) {//Start of findByID method
        Attraction result = null;

        String sql ="Select name,attractions.attractionid,classificationid,imageurl,ratings,status from "+connectionUtil.getDefaultSchema()+
                    ".attractions left join "+connectionUtil.getDefaultSchema()+
                    ".maintenance_tickets on attractions.attractionid = maintenance_tickets.attractionid where attractions.attractionid= ? ";

        try (Connection conn = connectionUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {//Start of first try
            ps.setInt(1, integer);

            try (ResultSet rs = ps.executeQuery()) {//Start of second try
                if (rs.next()) {//Start of first if
                    result = new Attraction();
                    result.setName(rs.getString("name"));
                    result.setId(rs.getInt("attractionid"));
                    result.setImageurl(rs.getString("imageurl"));
                    result.setRating(rs.getInt("ratings"));
                    result.setStatus(rs.getString("status"));
                }//End of first if
            }//End of second try
        }//End of first try
        catch (SQLException throwables) {//Start of catch
            throwables.printStackTrace();
        }//End of catch
        if(result.getStatus()==null){
            result.setStatus("Operational");
        }

        return result;

    }//End of findByIDMethod


    public boolean remove(Integer id) {//Start of remove method
        int deletedRowCount = -1;

        String sql = "DELETE FROM " + connectionUtil.getDefaultSchema() + ".attractions WHERE attractionid = ?";

        try (Connection conn = connectionUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);

            deletedRowCount = ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return deletedRowCount != -1;

}//End of remove method


    public boolean update(Integer integer, Attraction newObj) {//Start of update method
        return false;
    }//End of update method

}//End of SQLDatabaseAttraction
