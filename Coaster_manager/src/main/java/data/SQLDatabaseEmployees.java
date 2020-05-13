package data;

import models.Employee;
import utils.ConnectionUtil;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;


/**
 * Project 2
 * SQLDatabaseEmployees class
 * @author Reginald Jefferson
 * @version 05/11/2020
 */
public class SQLDatabaseEmployees implements GenericDAO<Employee,Integer> {
    private ConnectionUtil connectionUtil;
    static final String TABLE = ".employees";
    private String schemaName = connectionUtil.getDefaultSchema();
    Connection connection = null;

    /**
     * @param connectionUtil
     */
    public SQLDatabaseEmployees(ConnectionUtil connectionUtil) {
        this.connectionUtil = connectionUtil;
    }

    /**
     * Finds and returns all objects
     *
     * @return a list of all objects, or <code>null</code> if an error occurred while retrieving
     * those objects from the database.
     */
    @Override
    public List<Employee> findAll() {
        List<Employee> employees = null;

        try {
            String schemaName = connectionUtil.getDefaultSchema();
            String sql = "SELECT * FROM  /*firstname, lastname, phonenumber, emailaddress, employeeid, bossid, admin*/"
                    + schemaName + TABLE;
            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet rs = statement.executeQuery(sql);

            while(rs.next()) {
                int id = rs.getInt("employeeid");
                String firstName = rs.getString("firstname");
                String lastName = rs.getString("lastname");
                String phoneNum = rs.getString("phonenumber");
                String email = rs.getString("emailaddress");
                int bossid = rs.getInt("bossid");
                Boolean admin = rs.getBoolean("admin");

                employees.add(new Employee(firstName, lastName, phoneNum, email, id, bossid, admin));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return employees;
    }

    /**
     * Adds a new object to the database.
     *
     * @param newObj The object to be added
     * @return <code>true</code> if the object was successfully added, <code>false</code> otherwise.
     */
    @Override
    public boolean add(Employee newObj) {
        return false;
    }

    /**
     * Finds and returns the object with the specified ID.
     *
     * @param integer the ID of the object to find
     * @return the object with that ID, or <code>null</code> if such an object could not be found.
     */
    @Override
    public Employee findByID(Integer integer) {
        Employee employee = null;

        try {
//            String schemaName = connectionUtil.getDefaultSchema();
            String sql = "SELECT employeeid, firstname, lastname, phonenumber, emailaddress, bossid, admin FROM " + schemaName + TABLE;
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, integer);
            ResultSet rs = statement.executeQuery(sql);

            while (rs.next()) {
                employee = new Employee();
                employee.setFname(rs.getString("firstname"));
                employee.setLname(rs.getString("lastname"));
                employee.setPhoneNum(rs.getString("phonenumber"));
                employee.setEmail(rs.getString("emailaddress"));
                employee.setId(rs.getInt("employeeid"));
                employee.setBossid(rs.getInt("bossid"));
                employee.setAdmin(rs.getBoolean("admin"));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return employee;
    }

    /**
     * Updates the object with the specified ID.
     *
     * @param integer the ID of the object to be updated
     * @param newObj  the new object that will replace the existing object in the database
     * @return <code>true</code> if an update occurred successfully, <code>false</code> otherwise.
     */
    @Override
    public boolean update(Integer integer, Employee newObj) {
        int rowsUpdated = 0;
        try {
            String sql = "UPDATE " + schemaName + TABLE +
                    " SET firstname=?, lastname=?, phonenumber=?, emailaddress=?, admin=? WHERE employeeid=?";
            PreparedStatement statement = connection.prepareStatement(sql);

            statement.setString(1, newObj.getFname());
            statement.setString(2, newObj.getLname());
            statement.setString(3, newObj.getPhoneNum());
            statement.setString(4, newObj.getEmail());
            statement.setBoolean(5, newObj.isAdmin());
            statement.setInt(6, newObj.getId());

            rowsUpdated = statement.executeUpdate();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return rowsUpdated > 0;
    }

    /**
     * Removes unused in this class
     * This method returns <code>true</code> even if that object does not exist in the database
     * (and hence there is nothing to remove).
     *
     * @param integer the ID of the object to remove
     * @return <code>true</code> if the deletion was successful, <code>false</code> otherwise.
     */
    @Override
    public boolean remove(Integer integer) {
        return false;
    }
}
