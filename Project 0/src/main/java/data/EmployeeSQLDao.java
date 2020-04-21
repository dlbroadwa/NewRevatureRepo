package data;

import dbutility.ConnectionDBUtility;
import dbutility.PostgresConnectionUtility;
import models.Employee;

import java.sql.*;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class EmployeeSQLDao implements Dao<Employee, Integer> {
    private List<Employee> employeeList;
    private ConnectionDBUtility connectionDBUtility;

    public EmployeeSQLDao(ConnectionDBUtility connectionDBUtility) {
        if(connectionDBUtility != null) {
            this.connectionDBUtility = connectionDBUtility;
        }
    }

    @Override
    public Employee findById(Integer integer) {
        return null;
    }

    @Override
    public List<Employee> findAll() {
        Connection connection = null;
        List<Employee> employees = new ArrayList<>();

        try {
            connection = connectionDBUtility.getConnection();
            String schemaName = connectionDBUtility.getDefaultSchema();
            String sql = "Select employeeID, first_name, last_name, hourly_salary, userid from " + schemaName + ".employee";
            Statement statement = connection.createStatement();

            ResultSet rs = statement.executeQuery(sql);

            while(rs.next()) {
                int employeeID = rs.getInt("employeeID");
                String first_name =rs.getString("first_name");
                String last_name = rs.getString("last_name");
                String hourly_Salary = rs.getString("hourly_Salary");
                String userid = rs.getString("userid");

                Employee temp = new Employee();
                temp.setEmployeeID(employeeID);
                temp.setFirstName(first_name);
                temp.setLastName(last_name);
                temp.setHourly_Salary(hourly_Salary);
                temp.setUserid(userid);

            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            if(connection != null) {
                try {
                    connection.close();
                }catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            }
        }
        return employees;
    }

    @Override
    public Integer save(Employee obj) {
        return null;
    }

    @Override
    public void update(Employee newObj, Integer integer) {

    }

    @Override
    public void delete(Employee obj) {

    }
}
