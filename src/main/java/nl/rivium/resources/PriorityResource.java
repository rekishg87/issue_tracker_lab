package nl.rivium.resources;

import nl.rivium.dao.PriorityDAO;
import nl.rivium.dao.PriorityDAOImpl;
import nl.rivium.entities.Priority;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

/**
 * Created by Rekish on 11/13/2015.
 * API Calls that are associated with Priority.
 */

// Create an instance only once for every request.
// No need to refresh priority data every time an issue is being created.
@ApplicationScoped
@Path("priority")
public class PriorityResource {
    private static final Logger LOGGER = LoggerFactory.getLogger(PriorityResource.class);
    private final PriorityDAO priorityDAO = new PriorityDAOImpl();

    //When deploying a JAX-RS application using servlet then, HttpServletRequest is available using @Inject.
    @Inject
    private HttpServletRequest request;

    //When accessing the api call there should be a valid session (a user must be logged in).
    // GET all priority details.
    @GET
    @Path("all")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getPriority() {
        LOGGER.info("API called for retrieving allPriority options.");
        // When there is a valid session without creating a new session, the api call can be accessed.
        if (request.getSession(false) != null) {
            final List<Priority> priorities = priorityDAO.getPriorityList();
            return Response.ok(priorities).build();
        // No valid session, so no user is logged in or session became invalid.
        } else {
            return Response.status(Response.Status.FORBIDDEN).build();
        }
    }
}
