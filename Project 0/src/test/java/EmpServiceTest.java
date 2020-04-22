import clients.EmployeeService;
import data.Dao;
import models.Employee;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;

import java.util.ArrayList;
import java.util.List;

public class EmpServiceTest {
    EmployeeService service;
    List<Employee> employees = new ArrayList<>();

    @Mock
    Dao employeeSQL;

    @Rule
    public MockitoRule mockitoRule = MockitoJUnit.rule();

    @Before
    public void useEService() {
        service = new EmployeeService(employeeSQL);
        Employee tmp = new Employee();
        tmp.setFirstName("Testley");
        tmp.setLastName("Testerson");
        tmp.setHourly_Salary("100.00");
        tmp.setUserid("TestSubject");
        employees.add(tmp);
    }
    @Test
    public void shouldSeeTheList(){
        Mockito.when(employeeSQL.findAll()).thenReturn(employees);
        List<Employee> actual = service.getAllEmployees();
        Assert.assertArrayEquals("Employees not returned", employees.toArray(), actual.toArray());
    }
    @Test
    public void shouldAddToList(){
        Mockito.doNothing().when(employeeSQL).save(employees);
        Assert.assertFalse("No employees added", employees.isEmpty());
    }
}
