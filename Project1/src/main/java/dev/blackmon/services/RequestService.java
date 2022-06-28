package dev.blackmon.services;

import dev.blackmon.models.Request;
import dev.blackmon.repositories.RequestDAO;

public class RequestService {
    private RequestDAO requestDAO = new RequestDAO();

    public Request createReimbursementRequest(Request r) {
        return requestDAO.createReimbursementRequest(r);
    }
}
