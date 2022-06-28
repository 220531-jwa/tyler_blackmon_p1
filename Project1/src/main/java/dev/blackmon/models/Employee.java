package dev.blackmon.models;

import dev.blackmon.enums.EmployeeType;

import java.math.BigDecimal;

public class Employee {
    private int id;
    private BigDecimal availableReimbursement;
    private EmployeeType type;
    private String username;
    private String password;

    public Employee() {
        super();
    }

    public Employee(int id, BigDecimal availableReimbursement, EmployeeType type, String username, String password) {
        super();
        this.id = id;
        this.availableReimbursement = availableReimbursement;
        this.type = type;
        this.username = username;
        this.password = password;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public BigDecimal getAvailableReimbursement() {
        return availableReimbursement;
    }

    public void setAvailableReimbursement(BigDecimal availableReimbursement) {
        this.availableReimbursement = availableReimbursement;
    }

    public EmployeeType getType() {
        return type;
    }

    public void setType(EmployeeType type) {
        this.type = type;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
