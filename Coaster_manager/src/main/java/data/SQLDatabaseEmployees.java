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
            String sql = "firstname, lastname, phonenumber, emailaddress, employeeid, bossid, admin" + schemaName + TABLE;
            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet rs = statement.executeQuery(sql);

            while(rs.next()) {
                String firstName = rs.getString("firstname");
                String lastName = rs.getString("lastname");
                String phoneNum = rs.getString("phonenumber");
                String email = rs.getString("emailaddress");
                int id = rs.getInt("employeeid");
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
            String schemaName = connectionUtil.getDefaultSchema();
            String sql = "firstname, lastname, phonenumber, emailaddress, employeeid, bossid, admin" + schemaName + TABLE;
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, integer);
            ResultSet rs = statement.executeQuery(sql);

            while (rs.next()) {
                String firstName = rs.getString("firstname");
                String lastName = rs.getString("lastname");
                String phoneNum = rs.getString("phonenumber");
                String email = rs.getString("emailaddress");
                int id = rs.getInt("employeeid");
                int bossid = rs.getInt("bossid");
                Boolean admin = rs.getBoolean("admin");

                employee = new Employee();
                employee.setFname(firstName);
                employee.setLname(lastName);
                employee.setPhoneNum(phoneNum);
                employee.setEmail(email);
                employee.setId(id);
                employee.setBossid(bossid);
                employee.setAdmin(admin);
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
        return false;
    }

    /**
     * Removes the object with the specified ID.
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
