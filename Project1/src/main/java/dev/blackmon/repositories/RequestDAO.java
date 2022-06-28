package dev.blackmon.repositories;

import dev.blackmon.enums.ReimbursementType;
import dev.blackmon.models.Request;
import dev.blackmon.utils.ConnectionUtil;

import java.sql.*;

public class RequestDAO {
    private static ConnectionUtil cu = ConnectionUtil.getConnectionUtil();

    public Request createReimbursementRequest(Request r) {
        String sql = "insert into request values (?, ?, ?, ?, ?, false, ?, ?, ?) returning *";

        try (Connection conn = cu.getConnection()) {
            PreparedStatement ps = conn.prepareCall(sql);

            ps.setInt(1, r.getId());
            ps.setInt(2, r.getRequesterId());
            ps.setInt(3, r.getFmId());
            ps.setBigDecimal(4, r.getAmount());
            ps.setInt(5, getIntForType(r.getType()));
            ps.setDate(6, Date.valueOf(r.getEventDate()));
            ps.setTime(7, Time.valueOf(r.getEventTime()));
            ps.setString(8, r.getDescription());


            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                return new Request();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    private int getIntForType(ReimbursementType type) {
        switch (type) {
            case UNIVERSITY_COURSES:
                return 1;
            case SEMINARS:
                return 2;
            case CERTIFICATION_PREPERATION:
                return 3;
            case CERTIFICATION:
                return 4;
            case TECHNICAL_TRAINING:
                return 5;
            default:
                return 6;
        }
    }
}
