package dev.blackmon.controllers;

import dev.blackmon.models.Request;
import dev.blackmon.services.RequestService;
import io.javalin.http.Context;

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
}
