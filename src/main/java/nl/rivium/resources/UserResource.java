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

/**
 * Created by Rekish on 9/15/2015.
 * API Calls that are associated with User.
 */

//Create an instance only once, for the duration of the application,
// otherwise with each api request it creates a new instance
@ApplicationScoped
@Path("user")
public class UserResource {
    private final UserDAO USER_DAO = new UserDAOImpl();

    //When deploying a JAX-RS application using servlet then, HttpServletRequest is available using @Context.
    @Context
    //static because the HttpServletRequest should be available throughout the whole class.
    static HttpServletRequest request;

    // Authenticate user with a username and password
    @POST
    @Path("login")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response authenticate (User input) {
        final boolean userFound = USER_DAO.authenticate(input.getUsername(), input.getPassword());

        if(userFound) {
            // When a valid user is found and no new session is being created and there is no session.
            // Create a new session for that user.
            if(request.getSession(false) == null) {
                request.getSession(true);
                // When a valid user is found and no new session is being created but there is a old session.
                // Invalidate that old session first and create a new session for that user.
            } else if(request.getSession(false) != null) {
                request.getSession().invalidate();
                request.getSession(true);
            }
            return Response.status(Response.Status.OK).build();
        } else {
            return Response.status(Response.Status.FORBIDDEN).build();
        }
    }

    // Register a new user
    @POST
    @Path("signup")
    @Produces(MediaType.TEXT_PLAIN)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response registerUser(User input) {
        final String addUser = USER_DAO.registerUser(input.getUsername(), input.getPassword(), input.getEmail());

        // If no valid information is entered, return a bad request (400).
        if(addUser == null) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
        // If a dublicate user has been found, return a bad request (400).
        else if (addUser.isEmpty()) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
        // When an new user has successfully been registered, return a OK (200).
        else {
            return Response.status(Response.Status.OK).build();
        }
    }

    // Logout user.
    @POST
    @Path("signout")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response signoutUser(User input) {

        // When there is a session and the session is valid.
        // Do not create a new session and invalidate the valid session.
        if(request.getRequestedSessionId() != null && request.isRequestedSessionIdValid()) {
            request.getSession(false).invalidate();
            return Response.status(Response.Status.OK).build();
        }
        // When there is no valid session but there is a old session.
        // Invalidate that old session.
        else if(!request.isRequestedSessionIdValid() && request.getRequestedSessionId() != null) {
            request.getSession(false).invalidate();
            return Response.status(Response.Status.OK).build();
        }
        // When there is no valid session and there is no old session.
        // Do nothing, because there is no user logged in and there is no old session to invalidate.
        return Response.status(Response.Status.FORBIDDEN).build();
    }

    // Validate if user is logged in and if the user has a valid session.
    @GET
    @Path("validate")
    @Produces(MediaType.APPLICATION_JSON)
    public Response validateSession() {

        // If there is no sessionId, return FORBIDDEN (403).
        if(request.getRequestedSessionId() == null) {
            return Response.status(Response.Status.FORBIDDEN).build();
        }
        // If there is a sessionId but it is not valid, return FORBIDDEN (403).
        else if(!request.isRequestedSessionIdValid()) {
            return Response.status(Response.Status.FORBIDDEN).build();
        }
        // When there is a sessionId and it is valid, return (200).
        else {
            return Response.status(Response.Status.OK).build();
        }

    }
}
