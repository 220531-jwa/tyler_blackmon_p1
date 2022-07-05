package dev.blackmon.controllers;

import dev.blackmon.models.Request;
import dev.blackmon.services.RequestService;
import io.javalin.http.Context;

import java.util.List;

public class RequestController {
    private static final RequestService requestService = new RequestService();

    public static void createReimbursementRequest(Context ctx) {
        try {
            Request r = ctx.bodyAsClass(Request.class);

            Request result = requestService.createReimbursementRequest(r);

            if (result != null) {
                ctx.status(201);
            } else {
                ctx.status(424);
            }
        } catch (RuntimeException e) {
            e.printStackTrace();
        }
    }

    public static void getRequestsFromId(Context ctx) {
        String currentUserType = ctx.cookieStore("employeeType");
        int currentUserId = ctx.cookieStore("id");
        List<Request> result = requestService.getRequestsForEmployee(currentUserType, currentUserId);

        if (result != null) {
            ctx.status(200);
            ctx.json(result);
        } else {
            ctx.status(404);
        }
    }

    public static void approveRequest(Context ctx) {
        Request result = requestService.approveRequest(Integer.parseInt(ctx.body().replace("{", "").replace("}", "").replace(" ", "").replace("id", "").replace(":", "").replace("\"", "").trim()));

        if (result != null) {
            ctx.status(200);
            ctx.json(result);
        } else {
            ctx.status(424);
        }
    }

    public static void denyRequest(Context ctx) {
        Request result = requestService.denyRequest(Integer.parseInt(ctx.body().replace("{", "").replace("}", "").replace(" ", "").replace("id", "").replace(":", "").replace("\"", "").trim()));
        if (result != null) {
            ctx.status(200);
            ctx.json(result);
        } else {
            ctx.status(424);
        }
    }

    public static void addGrade(Context ctx) {
        Request result = requestService.addGrade(ctx.body().replace("{", "").replace("}", "").replace(" ", "").replace("id", "").replace(":", "").replace("\"", "").trim());
    }
}
