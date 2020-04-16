package clients;

import data.Repo;
import models.Employee;

import java.util.List;

public class EmployeeService {
    private Repo<Employee, String> empRepo;

    public EmployeeService(Repo<Employee, String> empRepo) {
        this.empRepo = empRepo;
    }

    public List<Employee> getAllEmployees() {
        return this.empRepo.findAll();
    }
}
