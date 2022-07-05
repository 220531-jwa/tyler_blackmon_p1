package dev.blackmon;

import dev.blackmon.controllers.EmployeeController;
import dev.blackmon.controllers.RequestController;
import io.javalin.Javalin;
import io.javalin.http.staticfiles.Location;

import static io.javalin.apibuilder.ApiBuilder.*;


public class ApiDriver {
    public static void main(String[] args) {
        Javalin app = Javalin.create(config -> {
            config.addStaticFiles("/public/html", Location.CLASSPATH);
            config.addStaticFiles("/public/scripts", Location.CLASSPATH);
            config.addStaticFiles("/public/css", Location.CLASSPATH);
        });

        app.start(8080);

        app.routes(() -> {
            path("/account", () -> {
                post(EmployeeController::getEmployeeByUsernameAndPassword);
                path("/request", () -> {
                    post(RequestController::createReimbursementRequest);
                    get(RequestController::getRequestsFromId);
                    path("/approve", () -> {
                        patch(RequestController::approveRequest);
                    });
                    path("/deny", () -> {
                        patch(RequestController::denyRequest);
                    });
                    path("/grade", () -> {
                        patch(RequestController::addGrade);
                    });
                });
            });
        });

    }
}
