package nl.rivium.resources;

import nl.rivium.dao.CategoryDAO;
import nl.rivium.dao.CategoryDAOImpl;
import nl.rivium.entities.Category;

import javax.enterprise.context.ApplicationScoped;
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
 * API Calls that are associated with Category.
 */

//Create an instance only once, for the duration of the application,
// otherwise with each api request it creates a new instance
@ApplicationScoped
@Path("category")
public class CategoryResource {
    private final CategoryDAO CATEGORY_DAO = new CategoryDAOImpl();

    //When deploying a JAX-RS application using servlet then, HttpServletRequest is available using @Context.
    @Context
    //static because the HttpServletRequest should be available throughout the whole class.
    static HttpServletRequest request;

    @GET
    @Path("all")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getCategory() {
        final List<Category> categories = CATEGORY_DAO.getCategoryList();

        // When there is a valid session without creating a new session, the api call can be accessed.
        if (request.getSession(false) != null) {
            return Response.ok(categories).build();
        } else { // No valid session, so no user is logged in or session became invalid.
            return Response.status(Response.Status.FORBIDDEN).build();
        }
    }
}
