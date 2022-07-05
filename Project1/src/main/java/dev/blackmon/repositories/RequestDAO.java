package dev.blackmon.repositories;

import dev.blackmon.enums.ReimbursementType;
import dev.blackmon.models.Request;
import dev.blackmon.utils.ConnectionUtil;

import java.sql.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class RequestDAO {
    private static ConnectionUtil cu = ConnectionUtil.getConnectionUtil();

    public Request createReimbursementRequest(Request r) {
        String sql = "insert into request values (default, ?, ?, ?, ?, false, ?, ?, ?, false, ?) returning *";

        try (Connection conn = cu.getConnection()) {
            PreparedStatement ps = conn.prepareCall(sql);

            ps.setInt(1, r.getRequesterId());
            ps.setInt(2, r.getFmId());
            ps.setBigDecimal(3, r.getAmount());
            ps.setInt(4, getIntForType(r.getType()));
            ps.setDate(5, Date.valueOf(r.getEventDate()));
            ps.setTime(6, Time.valueOf(r.getEventTime()));
            ps.setString(7, r.getDescription());
            ps.setString(8, r.getGrade());


            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                return new Request();
            }

            return null;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public List<Request> getRequestsWithId(String employeeType, int id) {
        String option1 = "select * from request where requester_id = ?";
        String option2 = "select * from request where fm_id = ?";
        String typeSql = "select type from reimbursement_type where id = ?";
        List<Request> requests = new ArrayList<>();

        try (Connection conn = cu.getConnection()) {
            PreparedStatement getReimbursementType = conn.prepareStatement(typeSql);
            PreparedStatement ps = conn.prepareStatement(employeeType.equals("EMPLOYEE") ? option1 : option2);

            ps.setInt(1, id);

            ResultSet rs = ps.executeQuery();


            while (rs.next()) {
                getReimbursementType.setInt(1, rs.getInt(5));

                ResultSet typeResult = getReimbursementType.executeQuery();
                ReimbursementType reimbursementType = null;
                if (typeResult.next()) {
                    reimbursementType = ReimbursementType.valueOf(typeResult.getString(1).toUpperCase().replace(" ", "_"));
                }
                requests.add(new Request(
                        rs.getInt(1),
                        rs.getInt(2),
                        rs.getInt(3),
                        rs.getBigDecimal(4),
                        reimbursementType,
                        rs.getBoolean(6),
                        LocalDate.parse(rs.getDate(7).toString()),
                        LocalTime.parse(rs.getTime(8).toString()),
                        rs.getString(9),
                        rs.getBoolean(10),
                        rs.getString(11)
                ));
            }
            return requests;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public Request approveRequest(int id) {
        String sql = "update request set approved = true where id = ? returning *";

        try (Connection conn = cu.getConnection()) {
            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setInt(1, id);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                return new Request(
                        rs.getInt(1),
                        rs.getInt(2),
                        rs.getInt(3),
                        rs.getBigDecimal(4),
                        getReimbursementType(rs.getInt(1)),
                        rs.getBoolean(6),
                        LocalDate.parse(rs.getDate(7).toString()),
                        LocalTime.parse(rs.getTime(8).toString()),
                        rs.getString(9),
                        rs.getBoolean(10),
                        rs.getString(11)
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    private ReimbursementType getReimbursementType(int id) {
        String typeSql = "select type from reimbursement_type where id = ?";

        try (Connection conn = cu.getConnection()) {
            PreparedStatement getType = conn.prepareStatement(typeSql);

            getType.setInt(1, id);
            ResultSet typeResult = getType.executeQuery();
            ReimbursementType reimbursementType = null;
            if (typeResult.next()) {
                reimbursementType = ReimbursementType.valueOf(typeResult.getString(1).toUpperCase().replace(" ", "_"));
            }

            return reimbursementType;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Request denyRequest(int id) {
        String sql = "update request set denied = true where id = ? returning *";

        try (Connection conn = cu.getConnection()) {
            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setInt(1, id);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                return new Request(
                        rs.getInt(1),
                        rs.getInt(2),
                        rs.getInt(3),
                        rs.getBigDecimal(4),
                        getReimbursementType(rs.getInt(1)),
                        rs.getBoolean(6),
                        LocalDate.parse(rs.getDate(7).toString()),
                        LocalTime.parse(rs.getTime(8).toString()),
                        rs.getString(9),
                        rs.getBoolean(10),
                        rs.getString(11)
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
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
