package dev.blackmon.services;

import dev.blackmon.models.Employee;
import dev.blackmon.repositories.EmployeeDAO;

public class EmployeeService {
    private EmployeeDAO employeeDAO = new EmployeeDAO();

    public Employee getEmployeeByUsernameAndPassword(String username, String password) {
        return employeeDAO.getEmployeeByUsernameAndPassword(username, password);
    }
}
