package dev.blackmon;

import dev.blackmon.controllers.EmployeeController;
import dev.blackmon.controllers.RequestController;
import io.javalin.Javalin;
import io.javalin.http.staticfiles.Location;

import static io.javalin.apibuilder.ApiBuilder.path;
import static io.javalin.apibuilder.ApiBuilder.post;

public class ApiDriver {
    public static void main(String[] args) {
        Javalin app = Javalin.create(config -> {
            config.addStaticFiles("/public/html", Location.CLASSPATH);
        });

        app.start(8080);

        app.routes(() -> {
            path("/account", () -> {
                post(EmployeeController::getEmployeeByUsernameAndPassword);
                path("/request", () -> {
                    post(RequestController::createReimbursementRequest);
                });
            });
        });

    }
}
