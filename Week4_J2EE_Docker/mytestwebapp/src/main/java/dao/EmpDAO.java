package dao;

import model.Employee;

import java.util.ArrayList;

public class EmpDAO {
    private static ArrayList<Employee> emps = new ArrayList<Employee>();

    static { initEmps(); }

    private static void initEmps() {
        Employee emp1 = new Employee("Smith", 123, "jsmith", "aStrongPassword");
        Employee emp2 = new Employee("Allen", 456, "eallen", "aReallyAwfulJoke");
        Employee emp3 = new Employee("Jones", 789, "djones", "p@$$w0rd");

        emps.add(emp1);
        emps.add(emp2);
        emps.add(emp3);
    }

    public Employee getEmp(int i) {
        Employee result = null;
        for ( Employee e : emps ) {
            if ( e.getID() == i ) {
                result = e;
            }
        }
        return result;
    }

    public Employee addNewEmp(Employee newEmp) {
        emps.add(newEmp);
        return newEmp;
    }

    public Employee removeEmp(int i) {
        for ( Employee e : emps ) {
            if ( e.getID() == i ) {
                emps.remove(e);
                return e;
            }
        }
        return null;
    }

    public Employee updateEmp(Employee updatedEmp) {
        for ( int i = 0; i < emps.size() ; i++ ) {
            if ( emps.get(i).getID() == updatedEmp.getID() ) {
                emps.set(i, updatedEmp);
                return updatedEmp;
            }
        }
        return null;
    }
}
