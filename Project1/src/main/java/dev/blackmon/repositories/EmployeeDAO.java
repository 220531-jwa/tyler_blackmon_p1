package dev.blackmon.repositories;

import dev.blackmon.enums.EmployeeType;
import dev.blackmon.models.Employee;
import dev.blackmon.utils.ConnectionUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class EmployeeDAO {
    private static ConnectionUtil cu = ConnectionUtil.getConnectionUtil();

    public Employee getEmployeeByUsernameAndPassword(String username, String password) {
        String sql = "select * from employee where username = ? and pass = ? ";

        try (Connection conn = cu.getConnection()) {
            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setString(1, username);
            ps.setString(2, password);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                return new Employee(
                        rs.getInt("id"),
                        rs.getBigDecimal("available_reimbursement"),
                        Integer.parseInt(rs.getString("employee_type")) == 1 ? EmployeeType.FINANCIAL_MANAGER : EmployeeType.EMPLOYEE,
                        rs.getString("username"),
                        rs.getString("pass")
                );
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

}
