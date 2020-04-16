package data;

import dbutility.ConnectionDBUtility;
import models.Employee;

import java.util.List;

public class EmployeeSQLRepo implements Repo<Employee, Integer> {
    private List<Employee> employeeList;
    private ConnectionDBUtility connectionDBUtility;

    public EmployeeSQLRepo(ConnectionDBUtility connectionDBUtility) {
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
        return null;
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
