package nl.rivium.resources;

import nl.rivium.dao.StatusDAO;
import nl.rivium.dao.StatusDAOImpl;
import nl.rivium.entities.Status;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.enterprise.context.RequestScoped;
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
 * API Calls that are associated with Status.
 */

// Create an instance only once for every request.
// Because a second API call should get new data from the database.
@RequestScoped
@Path("status")
public class StatusResource {
    private static final Logger LOGGER = LoggerFactory.getLogger(StatusResource.class);
    private final StatusDAO statusDAO = new StatusDAOImpl();

    //When deploying a JAX-RS application using servlet then, HttpServletRequest is available using @Inject.
    @Inject
    private HttpServletRequest request;

    //When accessing the api call there should be a valid session (a user must be logged in).
    // GET all status details.
    @GET
    @Path("all")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getStatus() {
        LOGGER.info("API called for retrieving allStatus options.");
        // When there is a valid session without creating a new session, the api call can be accessed.
        if (request.getSession(false) != null) {
            final List<Status> statuses = statusDAO.getStatusList();
            return Response.ok(statuses).build();
        // No valid session, so no user is logged in or session became invalid.
        } else {
            return Response.status(Response.Status.FORBIDDEN).build();
        }
    }

}
