package clients;

import data.Dao;
import models.Employee;

import java.util.List;

public class EmployeeService {
    private Dao<Employee, Integer> empDao;

    public EmployeeService(Dao<Employee, Integer> empDao) {
        this.empDao = empDao;
    }

    public List<Employee> getAllEmployees() {
        return this.empDao.findAll();
    }
}
