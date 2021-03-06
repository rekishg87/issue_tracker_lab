package nl.rivium.resources;

import nl.rivium.dao.CategoryDAO;
import nl.rivium.dao.CategoryDAOImpl;
import nl.rivium.entities.Category;
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
 * API Calls that are associated with Category.
 */

// Create an instance only once for every request.
// No need to refresh category data every time an issue is being created.
@ApplicationScoped
@Path("category")
public class CategoryResource {
    private static final Logger LOGGER = LoggerFactory.getLogger(CategoryResource.class);
    private final CategoryDAO categoryDAO = new CategoryDAOImpl();

    //When deploying a JAX-RS application using servlet then, HttpServletRequest is available using @Inject.
    @Inject
    private HttpServletRequest request;

    //When accessing the api call there should be a valid session (a user must be logged in).
    // GET all category details.
    @GET
    @Path("all")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getCategory() {
        LOGGER.info("API called for retrieving allCategory options.");
        // When there is a valid session without creating a new session, the api call can be accessed.
        if (request.getSession(false) != null) {
            final List<Category> categories = categoryDAO.getCategoryList();
            return Response.ok(categories).build();
        // No valid session, so no user is logged in or session became invalid.
        } else {
            return Response.status(Response.Status.FORBIDDEN).build();
        }
    }
}
