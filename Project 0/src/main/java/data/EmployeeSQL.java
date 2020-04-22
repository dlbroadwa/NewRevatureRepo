package data;

import dbutility.ConnectionDBUtility;
import models.Employee;
import models.GrossPay;

import java.sql.*;
import java.util.*;

/**
 * Employee DAO utilizing scanner object for user input and java table for temporary storage of data. Information from the database is pulled in through the connection utility.
 * This class contains the necessary SQL functions to perform searches, updates, adds, and deletion of data from the Employee Table. Find by ID is not currently being used at this time.
 */

public class EmployeeSQL implements Dao<Employee, String> {
    //private List<Employee> employeeList;
    private ConnectionDBUtility connectionDBUtility;
    private Scanner scanner = new Scanner(System.in);

    public EmployeeSQL(ConnectionDBUtility connectionDBUtility) {
        if(connectionDBUtility != null) {
            this.connectionDBUtility = connectionDBUtility;
        }
    }

    @Override
    public Employee findById(GrossPay e) {
        Connection connection = null;
        List<Employee> employees = new ArrayList<>();

        try {
            connection = connectionDBUtility.getConnection();
            String schemaName = connectionDBUtility.getDefaultSchema();
            String sql = "first_name, last_name from " + schemaName + ".employee";
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(sql);

            while(rs.next()) {
                String first_name =rs.getString("first_name");
                String last_name = rs.getString("last_name");

                Employee temp = new Employee();
                temp.setFirstName(first_name);
                temp.setLastName(last_name);
                employees.add(temp);
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
        return (Employee) employees;
    }

    @Override
    public List<Employee> findAll() {
        Connection connection = null;
        List<Employee> employees = new ArrayList<>();

        try {
            connection = connectionDBUtility.getConnection();
            String schemaName = connectionDBUtility.getDefaultSchema();
            String sql = "select * from " + schemaName + ".employee";
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(sql);

            while(rs.next()) {
                String first_name =rs.getString("first_name");
                String last_name = rs.getString("last_name");
                String hourly_Salary = rs.getString("hourly_Salary");
                String userid = rs.getString("userid");

                Employee temp = new Employee();
                temp.setFirstName(first_name);
                temp.setLastName(last_name);
                temp.setHourly_Salary(hourly_Salary);
                temp.setUserid(userid);
                employees.add(temp);
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
    public void save(Employee obj) {
        Connection connection;
        try {
            connection = connectionDBUtility.getConnection();
            String schemaName = connectionDBUtility.getDefaultSchema();
            String sql = "insert into " + schemaName + ".employee (first_name, last_name, hourly_salary, userid) values (?,?,?,?)";
            PreparedStatement statement = connection.prepareStatement(sql);
            System.out.println("Enter the first name: ");
            statement.setString(1, scanner.nextLine());
            System.out.println("Enter the last name: ");
            statement.setString(2, scanner.nextLine());
            System.out.println("Enter the hourly salary: ");
            statement.setDouble(3, Double.parseDouble(scanner.nextLine()));
            System.out.println("Enter a User ID: ");
            statement.setString(4, scanner.nextLine());
            //Statement statement = connection.createStatement();
            //ResultSet rs = statement.executeQuery(sql);
            int rowsUpdated = statement.executeUpdate();
            if (rowsUpdated > 0) {
                System.out.println("Employee was added successfully!\n");
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    @Override
    public void update(Employee obj) {
        Connection connection;
        try {
            connection = connectionDBUtility.getConnection();
            String schemaName = connectionDBUtility.getDefaultSchema();
            String sql = "update " + schemaName + ".employee set hourly_salary=? where userid=?";
            PreparedStatement statement = connection.prepareStatement(sql);
            System.out.println("Please enter the new hourly salary: ");
            statement.setDouble(1, Double.parseDouble(scanner.nextLine())/*scanner.nextLine()*/);
            System.out.println("Please enter the employee's user ID: ");
            statement.setString(2, scanner.nextLine());
//            statement.setString(3, scanner.nextLine());
//            statement.setString(4, scanner.nextLine());
            //Statement statement = connection.createStatement();
            int rowsUpdated = statement.executeUpdate();
            if (rowsUpdated > 0) {
                System.out.println("User was updated successfully!");
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

        @Override
    public void delete(Employee obj) {
            Connection connection;
            this.scanner = new Scanner(System.in);
            try {
                connection = connectionDBUtility.getConnection();
                String schemaName = connectionDBUtility.getDefaultSchema();
                String sql = "delete from " + schemaName + ".employee where first_name=? and last_name=?";
                PreparedStatement statement = connection.prepareStatement(sql);
                System.out.println("Their first name? ");
                statement.setString(1, scanner.nextLine());
                System.out.println("Their last name?");
                statement.setString(2, scanner.nextLine());
                //Statement statement = connection.createStatement();
                //ResultSet rs = statement.executeQuery(sql);
                int rowsDeleted = statement.executeUpdate();
                if (rowsDeleted > 0) {
                    System.out.println("Employee was deleted successfully!");
                }
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
    }

}
