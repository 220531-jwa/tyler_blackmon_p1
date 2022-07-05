package dev.blackmon.services;

import dev.blackmon.models.Request;
import dev.blackmon.repositories.RequestDAO;

import java.util.List;

public class RequestService {
    private RequestDAO requestDAO = new RequestDAO();

    public Request createReimbursementRequest(Request r) {
        return requestDAO.createReimbursementRequest(r);
    }

    public List<Request> getRequestsForEmployee(String type, int id) {
        return requestDAO.getRequestsWithId(type, id);
    }

    public Request approveRequest(int id) {
        return requestDAO.approveRequest(id);
    }

    public Request denyRequest(int id) {
        return requestDAO.denyRequest(id);
    }

    public Request addGrade(String grade) {
        return requestDAO.addGrade(grade);
    }
}
