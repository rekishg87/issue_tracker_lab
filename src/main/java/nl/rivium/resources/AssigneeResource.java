package nl.rivium.resources;

import nl.rivium.dao.AssigneeDAO;
import nl.rivium.dao.AssigneeDAOImpl;
import nl.rivium.entities.Assignee;

import javax.enterprise.context.RequestScoped;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;


/**
 * Created by Rekish on 11/13/2015.
 * API Calls that are associated with Assignee.
 */

// Create an instance only once for every request.
// Because a second API call should get new data from the database.
@RequestScoped
@Path("assignee")
public class AssigneeResource {
    private final AssigneeDAO ASSIGNEE_DAO = new AssigneeDAOImpl();

    //When deploying a JAX-RS application using servlet then, HttpServletRequest is available using @Context.
    @Context
    //static because the HttpServletRequest should be available throughout the whole class.
    static HttpServletRequest request;

    //When accessing the api call there should be a valid session (a user must be logged in)
    @GET
    @Path("all")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAssignee() {
        final List<Assignee> assignees = ASSIGNEE_DAO.getAssigneeList();

        // When there is a valid session without creating a new session, the api call can be accessed.
        if (request.getSession(false) != null) {
            return Response.ok(assignees).build();
        } else { // No valid session, so no user is logged in or session became invalid.
            return Response.status(Response.Status.FORBIDDEN).build();
        }
    }
}
