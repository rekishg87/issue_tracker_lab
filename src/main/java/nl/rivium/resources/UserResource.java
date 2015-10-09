package nl.rivium.resources;

import nl.rivium.dao.UserDAO;
import nl.rivium.dao.UserDAOImpl;
import nl.rivium.entities.User;

import javax.enterprise.context.ApplicationScoped;
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

        if(userFound) {
            if(request.getSession(false) == null) {
                request.getSession(true);
            } else if(request.getSession(false) != null) {
                request.getSession().invalidate();
                request.getSession(true);
            }

            return Response
                    .status(Response.Status.OK)
                    .header("Access-Control-Allow-Origin", "http://localhost:8080")
                    .build();
        } else {
            return Response.status(Response.Status.FORBIDDEN).build();
        }
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

    @GET
    @Path("signout")
    @Produces(MediaType.APPLICATION_JSON)
    public Response singoutUser() {

        if(request.getRequestedSessionId() != null) {
            request.getSession(false).invalidate();
            return Response
                    .status(Response.Status.OK)
                    .header("Access-Control-Allow-Origin", "http://localhost:8080")
                    .build();
        } else if(request.getRequestedSessionId() == null) {
            return Response
                    .status(Response.Status.OK)
                    .header("Access-Control-Allow-Origin", "http://localhost:8080")
                    .build();

        }
        else {
            return Response
                    .status(Response.Status.INTERNAL_SERVER_ERROR)
                    .header("Access-Control-Allow-Origin", "http://localhost:8080")
                    .build();
        }

    }

    @GET
    @Path("validate")
    @Produces(MediaType.APPLICATION_JSON)
    public Response validateSession() {

        if(request.getRequestedSessionId() == null) {
            return Response
                    .status(Response.Status.OK)
                    .entity(request.getRequestedSessionId())
                    .header("Access-Control-Allow-Origin", "http://localhost:8080")
                    .build();
        } else if(request.getRequestedSessionId() != null) {
            return Response
                    .status(Response.Status.OK)
                    .entity(request.getRequestedSessionId())
                    .header("Access-Control-Allow-Origin", "http://localhost:8080")
                    .build();
        }
        else {
            return Response
                    .status(Response.Status.FORBIDDEN)
                    .entity(request.getRequestedSessionId())
                    .header("Access-Control-Allow-Origin", "http://localhost:8080")
                    .build();
        }

    }
}
