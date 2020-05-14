package dao;

import models.Attraction;
import utils.ConnectionUtils;
import utils.PostgresConnectionUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 *  Project 2:<br>
 * <br>
 *  AttractionDAO
 *
 *  <br> <br>
 *  Created: <br>
 *     May 11, 2020 Paityn Maynard<br>
 *     With assistance from: Barthelemy Martinon<br>
 *  Modifications: <br>
 * <br>
 *     12 May 2020, Barthelemy Martinon,    Ported Coaster_manager's SQLDatabaseAttraction class to Coaster_customer
 *                                              as AttractionDAO. Modifications made according to new destination
 *                                              structure and DAO interface.
 * <br>
 *  @author Paityn Maynard With assistance from: Barthelemy Martinon
 *  @version 12 May 2020
 */
public class AttractionDAO implements DAO<Attraction,Integer> {//Start of AttractionDAO
    //Instance Variables
    private static ConnectionUtils connectionUtil;

    //Constructors
    public AttractionDAO(ConnectionUtils connectionUtil){
        this.connectionUtil = connectionUtil;
    }

    //Methods
    public ArrayList<Attraction> findAll() {//Start of findAll method
        ArrayList<Attraction> results = null;

        String sql = "Select project2.attractions.name,project2.attractions.attractionid,project2.attractions.imageurl,project2.attractions.ratings,status  from project2.attractions " +
                "left join project2.maintenance_tickets on attractions.attractionid = maintenance_tickets.attractionid";

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


    public Integer save(Attraction attraction) {//Start of add method
        if (findById(attraction.getId()) != null) {//Start of if statement
            return -1;
        }//End of if statement
        int addedRowCount = 0;
        String sql = "INSERT INTO project2.attractions (attractionid, imageurl, name, ratings) values (?, ?, ?, ?)";

        try (Connection conn = connectionUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {//Start of try
            ps.setInt(1, attraction.getId());
            ps.setString(2, attraction.getImageurl());
            ps.setString(3, attraction.getName());
            ps.setInt(4, attraction.getRating());

            addedRowCount = ps.executeUpdate();
        } //End of try
        catch (SQLException throwables) {//Start of catch
            throwables.printStackTrace();
        }//End of catch

        return addedRowCount;
    }//End of add method


    public Attraction findById(Integer integer) {//Start of findById method
        Attraction result = null;

        String sql ="Select project2.attractions.name,project2.attractions.attractionid,imageurl,ratings,status from " +
                "project2.attractions left join project2.maintenance_tickets on attractions.attractionid = " +
                "maintenance_tickets.attractionid where attractions.attractionid= ? ";

        try (Connection conn = connectionUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {//Start of first try
            ps.setInt(1, integer);

            try (ResultSet rs = ps.executeQuery()) {//Start of second try
                if (rs.next()) {//Start of first if
                    String name = rs.getString("name");
                    int id = rs.getInt("attractionid");
                    String status = rs.getString("status");
                    String imageurl = rs.getString("imageurl");
                    int rating = rs.getInt("ratings");
                    result = new Attraction(name,status,imageurl,id,rating);
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

    }//End of findByIdMethod


    public void delete(Attraction obj) {//Start of remove method
        int idNum = obj.getId();

        String sql = "DELETE FROM project2.attractions WHERE attractionid=" + idNum;

        try (Connection conn = connectionUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }//End of remove method

    public void update(Attraction newObj, Integer integer) {//Start of update method
        // Do nothing for now.
    }//End of update method

}//End of SQLDatabaseAttraction
