package nl.rivium.resources;

import nl.rivium.dao.UserDAO;
import nl.rivium.dao.UserDAOImpl;
import nl.rivium.entities.User;

import javax.enterprise.context.ApplicationScoped;
import javax.json.JsonArray;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

/**
 * Created by Rekish on 9/15/2015.
 */

@ApplicationScoped
@Path("user")
public class UserResource {
    private final UserDAO USER_DAO = new UserDAOImpl();

    @Context
    private static HttpServletRequest request;

    @POST
    @Path("login")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response auth (User input) {
        final boolean userFound = USER_DAO.auth(input.getUsername(), input.getPassword());
        String loggedInUser = "";

        if(userFound) {
            boolean session = false;
            if(request.getSession(false) == null) {
                request.getSession(true);
                loggedInUser = input.getUsername();
                session = true;
            } else {
                request.getSession(false);
                session = false;
            }

            return Response
                    .status(Response.Status.OK)
                    //.entity(loggedInUser)
                    .header("Access-Control-Allow-Origin", "http://localhost:8080")
                    .build();
        } else {
            return Response.status(Response.Status.FORBIDDEN).build();
        }
    }

    @POST
    @Path("value")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response users (User input) {
        final JsonArray userss = USER_DAO.value(input.getUsername(), input.getPassword());

        return Response
                .status(Response.Status.OK)
                .header("Access-Control-Allow-Origin", "http://localhost:8080")
                .entity(userss)
                .build();
    }

    @POST
    @Path("signup")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response signupUser(User input) {
        final List<User> addUser = USER_DAO.signupUser(input.getUsername(), input.getPassword(), input.getEmail());


            return Response
                    .status(Response.Status.OK)
                    .entity(addUser)
                    .header("Access-Control-Allow-Origin", "http://localhost:8080")
                    .build();

            /*return Response
                    .status(Response.Status.BAD_REQUEST)
                    .entity(addUser)
                    .header("Access-Control-Allow-Origin", "http://localhost:8080")
                    .build();*/

    }
}
