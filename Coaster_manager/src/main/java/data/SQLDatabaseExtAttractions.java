package data;

import models.Attraction;
import utils.ConnectionUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SQLDatabaseExtAttractions implements GenericDAO<Attraction, Integer>{//Start of SQLDatabaseExtAttractions class
//Instance Variables
    private static ConnectionUtil connectionUtil;

//Constructors
    public SQLDatabaseExtAttractions(ConnectionUtil connectionUtil){
        this.connectionUtil=connectionUtil;
    }

    public List<Attraction> findAll() {//Start of findAll method
        List<Attraction> results = null;

        String sql = "Select name,attractionid,classificationid,imageurl,ratings,status  from "+
                connectionUtil.getDefaultSchema()+".external_attractions left join " + connectionUtil.getDefaultSchema()+
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

    public boolean add(Attraction attraction) {//Start of add method
        if (findByID(attraction.getId()) != null) {//Start of if statement
            return false;
        }//End of if statement
        int addedRowCount = 0;
        String sql = "INSERT INTO " + connectionUtil.getDefaultSchema() +
                ".external_attractions (attractionid, imageurl, name, ratings) values (?, ?, ?, ?)";

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

        return addedRowCount == 1;
    }//End of add method

    public Attraction findByID(Integer integer) {//Start of findByID method
        Attraction result = null;

        String sql ="Select name,attractions.attractionid,classificationid,imageurl,ratings,status from "+connectionUtil.getDefaultSchema()+
                ".external_attractions left join "+connectionUtil.getDefaultSchema()+
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

        String sql = "DELETE FROM " + connectionUtil.getDefaultSchema() + ".external_attractions WHERE attractionid = ?";

        try (Connection conn = connectionUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);

            deletedRowCount = ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return deletedRowCount != -1;

    }//End of remove method

    /**
     * Update unused in the class
     * @param integer
     * @param newObj the new object that will replace the existing object in the database
     * @return false
     */
    public boolean update(Integer integer, Attraction newObj) {//Start of update method
        return false;
    }//end of update method

}//End of SQLDatabaseExtAttractions class
