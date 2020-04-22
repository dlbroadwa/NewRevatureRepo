package clients;

import data.Dao;
import models.Employee;

import java.util.List;

/**
 * Employee service that utilizes the employee dao to help perform search, update, add, and delete functions in the Employee SQL DAO
 */
public class EmployeeService {
    private Dao<Employee, String> empDao;

    public EmployeeService(Dao<Employee, String> empDao) {
        this.empDao = empDao;
    }

    public List<Employee> getAllEmployees() {
        return this.empDao.findAll();
    }
//    public Employee getEmployee(String emp) {
//        return this.empDao.findById(emp);
//    }
    public void update(Employee emp) {
        empDao.update(emp);
    }
    public void saveEmp(Employee emp) {
        empDao.save(emp);
    }
    public void deleteEmp(Employee emp) {
        empDao.delete(emp);
    }
}
