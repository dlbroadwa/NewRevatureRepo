package data;

import dbutility.ConnectionDBUtility;
import models.Employee;

import java.util.List;

public class EmployeeSQLRepo implements Repo<Employee, String> {
    private List<Employee> employeeList;
    private ConnectionDBUtility connectionDBUtility;

    public EmployeeSQLRepo(ConnectionDBUtility connectionDBUtility) {
        if(connectionDBUtility != null) {
            this.connectionDBUtility = connectionDBUtility;
        }
    }

    @Override
    public Employee findById(String s) {
        return null;
    }

    @Override
    public List<Employee> findAll() {
        return null;
    }

    @Override
    public String save(Employee obj) {
        return null;
    }


    @Override
    public void update(Employee newObj, String s) {

    }

    @Override
    public void delete(Employee obj) {

    }
}
