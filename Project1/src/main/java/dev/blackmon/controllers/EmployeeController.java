package dev.blackmon.controllers;

import dev.blackmon.models.Employee;
import dev.blackmon.services.EmployeeService;
import io.javalin.http.Context;

public class EmployeeController {
    private static final EmployeeService employeeService = new EmployeeService();

    public static void getEmployeeByUsernameAndPassword(Context ctx) {
        Employee e = ctx.bodyAsClass(Employee.class);

        Employee result = employeeService.getEmployeeByUsernameAndPassword(e.getUsername(), e.getPassword());

        if (result != null) {
            ctx.status(200);
            ctx.json(result);
        } else {
            ctx.status(404);
        }
    }
}
