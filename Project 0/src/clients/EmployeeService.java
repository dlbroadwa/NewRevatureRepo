package clients;

import data.Repo;
import models.Employee;

import java.util.List;

public class EmployeeService {
    private Repo<Employee, Integer> empRepo;

    public EmployeeService(Repo<Employee, Integer> empRepo) {
        this.empRepo = empRepo;
    }

    public List<Employee> getAllEmployees() {
        return this.empRepo.findAll();
    }
}
