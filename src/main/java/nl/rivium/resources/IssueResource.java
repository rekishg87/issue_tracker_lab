package nl.rivium.resources;

import nl.rivium.dao.IssueDAO;
import nl.rivium.dao.IssueDAOImpl;
import nl.rivium.entities.Issue;
import javax.enterprise.context.RequestScoped;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

/**
 * Created by Rekish on 9/14/2015.
 * API Calls that are associated with Issue.
 */

// Create an instance only once for every request.
// Because a second API call should get new data from the database.
@RequestScoped
@Path("issues")
public class IssueResource {
    private final IssueDAO ISSUE_DAO = new IssueDAOImpl();

    //When deploying a JAX-RS application using servlet then, HttpServletRequest is available using @Context.
    @Context
    //static because the HttpServletRequest should be available throughout the whole class.
    static HttpServletRequest request;

    //When accessing the api call there should be a valid session (a user must be logged in)
    // GET all issues details.
    @GET
    @Path("getAllIssues")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllIssues() {
        final List<Issue> issues = ISSUE_DAO.getAllIssuesList();

        // When there is a valid session without creating a new session, the api call can be accessed.
        if (request.getSession(false) != null) {
            return Response.ok(issues).build();
        } else { // No valid session, so no user is logged in or session became invalid.
            return Response.status(Response.Status.FORBIDDEN).build();
        }
    }
    //When accessing the api call there should be a valid session (a user must be logged in).
    // POST a new issue API call.
    @POST
    @Path("create")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createIssue(Issue input ) {
        final List<Issue> issues = ISSUE_DAO.createIssue
                (input.getDescription(), input.getSubject(), input.getCategoryId(),
                        input.getPriorityId(), input.getCreatedBy());

        // When there is a valid session without creating a new session, the api call can be accessed.
        if (request.getSession(false) != null) {
            return Response.ok(issues).build();
        } else { // No valid session, so no user is logged in or session became invalid.
            return Response.status(Response.Status.FORBIDDEN).build();
        }
    }

    //When accessing the api call there should be a valid session (a user must be logged in).
    // POST a new edited/update issue API call.
    @POST
    @Path("update")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response updateIssue(Issue input) {
        final List<Issue> issues = ISSUE_DAO.updateIssue(
               input.getId(), input.getPriorityId(), input.getSubject(), input.getDescription(),
                input.getAssigneeId(), input.getCategoryId(), input.getStatusId());

        // When there is a valid session without creating a new session, the api call can be accessed.
        if (request.getSession(false) != null) {
            return Response.ok(issues).build();
        } else { // No valid session, so no user is logged in or session became invalid.
            return Response.status(Response.Status.FORBIDDEN).build();
        }
    }

    //When accessing the api call there should be a valid session (a user must be logged in).
    // POST a removed issue API call.
    @POST
    @Path("remove")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response removeIssue(Issue input) {
        final List<Issue> issues = ISSUE_DAO.removeIssue(input.getId());

        // When there is a valid session without creating a new session, the api call can be accessed.
        if (request.getSession(false) != null) {
            return Response.ok(issues).build();
        } else { // No valid session, so no user is logged in or session became invalid.
            return Response.status(Response.Status.FORBIDDEN).build();
        }
    }
}
