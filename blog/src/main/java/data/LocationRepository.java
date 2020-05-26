package data;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Location;
import utils.ConnectionUtils;

/**
 * The Data Access Object class for Location model, using Integer as a primary key index.
 */
public class LocationRepository implements Repository<Location, Integer> {

	private final ConnectionUtils connectionUtil;

    /**
     * Constructor will be called in the Servlet initiation where a configured ConnectionUtil object will be provided.
     * @param connectionUtil - configured instance of ConnectionUtils
     */
	public LocationRepository(ConnectionUtils connectionUtil) {
		this.connectionUtil = connectionUtil;
	}

    /**
     * Queries the database to find a Location by primary key id
     * @param location_id Integer for Location id
     * @return returns a Location object found in database
     */
	@Override
	public Location findById(Integer location_id) {
        Connection connection = null;
        Location location = null;
        try {
            connection = connectionUtil.getConnection();
            String sqlQuery = "SELECT * FROM blog.locations WHERE location_id = ?;";
            PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery);
            preparedStatement.setInt(1, location_id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                location = new Location(
            		resultSet.getInt("location_id"),
                    resultSet.getString("name"),
                    resultSet.getString("street_number"),
                    resultSet.getString("route"),
                    resultSet.getString("city"),
                    resultSet.getString("state"),
                    resultSet.getString("zipcode"),
                    resultSet.getString("country"),
                    resultSet.getFloat("latitude"),
                    resultSet.getFloat("longitude"));
            }
        } catch (Exception exception) {
            exception.printStackTrace();
        } finally {
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException exception) {
                    exception.printStackTrace();
                }
            }
        }
        return location;
	}

    /**
     * Queries the database for all the Location entries
     * @return returns a List of Location objects
     */
	@Override
	public List<Location> findAll() {
        Connection connection = null;
        List<Location> locations = new ArrayList<>();
        try {
            connection = connectionUtil.getConnection();
            String sqlQuery = "SELECT * FROM blog.locations;";
            PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Location location = new Location(
            		resultSet.getInt("location_id"),
                    resultSet.getString("name"),
                    resultSet.getString("street_number"),
                    resultSet.getString("route"),
                    resultSet.getString("city"),
                    resultSet.getString("state"),
                    resultSet.getString("zipcode"),
                    resultSet.getString("country"),
                    resultSet.getFloat("latitude"),
                    resultSet.getFloat("longitude"));
                locations.add(location);
            }
        } catch (Exception exception) {
            exception.printStackTrace();
        } finally {
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException exception) {
                    exception.printStackTrace();
                }
            }
        }
        return locations;
	}

    /**
     * Queries the database for a Location by name, street number, route, city, and state abbreviation.
     * @param name String for name
     * @param street_number String for street number
     * @param route String for route
     * @param city String for city
     * @param state String for state abbreviation
     * @return returns a Location object found in the database
     */
	public Location findLocation(String name, String street_number, String route, String city, String state) {
        Connection connection = null;
        Location location = null;
        try {
            connection = connectionUtil.getConnection();
            String sqlQuery = "SELECT * FROM blog.locations WHERE name=? AND street_number=? AND route=? AND city=? AND state=?;";
            PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery);
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, street_number);
            preparedStatement.setString(3, route);
            preparedStatement.setString(4, city);
            preparedStatement.setString(5, state);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                location = new Location(
            		resultSet.getInt("location_id"),
                    resultSet.getString("name"),
                    resultSet.getString("street_number"),
                    resultSet.getString("route"),
                    resultSet.getString("city"),
                    resultSet.getString("state"),
                    resultSet.getString("zipcode"),
                    resultSet.getString("country"),
                    resultSet.getFloat("latitude"),
                    resultSet.getFloat("longitude"));
            }
        } catch (Exception exception) {
            exception.printStackTrace();
        } finally {
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException exception) {
                    exception.printStackTrace();
                }
            }
        }
        return location;
	}

    /**
     * Writes to the database to persist a Location object
     * @param obj Location object to be saved in database
     * @return returns the object persisted in the database
     */
	@Override
	public Location create(Location obj) {
    	Connection connection = null;
    	Location location = null;
    	try {
            connection = connectionUtil.getConnection();
            String sqlQuery = "INSERT INTO blog.locations VALUES (DEFAULT,?,?,?,?,?,?,?,?,?);";
            PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery);
            preparedStatement.setString(1, obj.getName());
            preparedStatement.setString(2, obj.getStreetNumber());
            preparedStatement.setString(3, obj.getRoute());
            preparedStatement.setString(4, obj.getCity());
            preparedStatement.setString(5, obj.getState());
            preparedStatement.setString(6, obj.getZipcode());
            preparedStatement.setString(7, obj.getCountry());
            preparedStatement.setFloat(8, obj.getLatitude());
            preparedStatement.setFloat(9, obj.getLongitude());
            if (preparedStatement.executeUpdate() == 1) {
                location = obj;
            }
        } catch (Exception exception) {
            exception.printStackTrace();
        } finally {
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException exception) {
                    exception.printStackTrace();
                }
            }
        }
        return location;
	}

    /**
     * Writes to the database to update a Location object
     * @param obj Location object to be updated
     * @return returns a Location object after it has been updated
     */
	@Override
	public Location update(Location obj) {
    	Connection connection = null;
    	Location location = null;
    	try {
            connection = connectionUtil.getConnection();
            String sqlQuery = "UPDATE blog.locations SET name=?, street_number=?, route=?, city=?, state=?, zipcode=?, country=?, latitude=?, longitude=? "
            						+ "where location_id=?;";
            PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery);
            preparedStatement.setString(1, obj.getName());
            preparedStatement.setString(2, obj.getStreetNumber());
            preparedStatement.setString(3, obj.getRoute());
            preparedStatement.setString(4, obj.getCity());
            preparedStatement.setString(5, obj.getState());
            preparedStatement.setString(6, obj.getZipcode());
            preparedStatement.setString(7, obj.getCountry());
            preparedStatement.setFloat(8, obj.getLatitude());
            preparedStatement.setFloat(9, obj.getLongitude());
            preparedStatement.setInt(10, obj.getLocationId());
            if (preparedStatement.executeUpdate() == 1) {
                location = obj;
            }
        } catch (Exception exception) {
            exception.printStackTrace();
        } finally {
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException exception) {
                    exception.printStackTrace();
                }
            }
        }
        return location;
	}

    /**
     * Deletes from the database a Location object if found
     * @param obj Location object to be deleted from the database
     */
	@Override
	public void delete(Location obj) {
    	Connection connection = null;
    	try {
            connection = connectionUtil.getConnection();
            String sqlQuery = "DELETE FROM blog.locations WHERE location_id=?;";
            PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery);
            preparedStatement.setInt(1, obj.getLocationId());
            preparedStatement.executeUpdate();
        } catch (Exception exception) {
            exception.printStackTrace();
        } finally {
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException exception) {
                    exception.printStackTrace();
                }
            }
        }
	}


    /**
     *  Code for Testing
     *  Author Damier Raymond
     * @param s
     * @return
     * @throws SQLException
     */
    public PreparedStatement executeQuery(String s) throws SQLException {
        return connectionUtil.getConnection().prepareStatement("");
    }

    public boolean Query(String s) {
        return true;
    }
/** End of the new method for testing */

}
