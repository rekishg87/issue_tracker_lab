package nl.rivium.resources;


import nl.rivium.dao.RolesDAO;
import nl.rivium.dao.RolesDAOImpl;
import nl.rivium.dao.UserDAO;
import nl.rivium.dao.UserDAOImpl;
import nl.rivium.entities.User;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * Created by Rekish on 9/15/2015.
 * API Calls that are associated with User.
 */

// Create an instance only once for every request.
// Because a second API call should get new data from the database.
@RequestScoped
@Path("user")
public class UserResource {
    //@Inject
    //private UserDAO USER_DAO;

    private final UserDAO USER_DAO = new UserDAOImpl();
    private final RolesDAO ROLES_DAO = new RolesDAOImpl();

    //When deploying a JAX-RS application using servlet then, HttpServletRequest is available using @Inject.
    @Inject
    private HttpServletRequest request;




    // Authenticate user with a username and password and return the user role.
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
            // If user entered correct credentials, return status 200 and the user role int;
            final int userRole = ROLES_DAO.userAccess(input.getUsername());
            return Response.status(Response.Status.OK)
                    .entity(userRole)
                    .build();
        } else {
            return Response.status(Response.Status.FORBIDDEN).build();
        }
    }

    // Register a new user
    @POST
    @Path("signup")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response registerUser(User input) {
        final String addUser = USER_DAO.registerUser(input.getUsername(), input.getPassword(), input.getEmail());

        // If a duplicate user has been found, return a bad request (400).
        if(addUser.equals(input.getUsername())) {
            System.out.println("UserResource if 400: " + addUser);
            return Response
                    .status(Response.Status.BAD_REQUEST)
                    .build();
        } else { // When an new user has successfully been registered, return a OK (200).
            return Response.status(Response.Status.OK).build();
        }
    }

    //When accessing the api call there should be a valid session otherwise invalidate old session.
    // Logout user.
    @POST
    @Path("signout")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response signoutUser() {

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
