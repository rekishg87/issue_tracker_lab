package nl.rivium.resources;

import nl.rivium.dao.CategoryDAO;
import nl.rivium.dao.CategoryDAOImpl;
import nl.rivium.entities.Category;

import javax.enterprise.context.ApplicationScoped;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
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
 */

@ApplicationScoped
@Path("category")
public class CategoryResource {
    private final CategoryDAO CATEGORY_DAO = new CategoryDAOImpl();

    @Context
    private static HttpServletRequest request;

    private EntityManagerFactory factory =
            Persistence.createEntityManagerFactory("issueUnit");
    private EntityManager manager = factory.createEntityManager();

    @GET
    @Path("all")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getCategory() {
        final List<Category> issues = CATEGORY_DAO.getCategoryList();

        /*return Response
                .status(Response.Status.OK)
                .header("Access-Control-Allow-Origin", "http://localhost:8080")
                .entity(issues)
                .build();*/

        if (request.getSession(false) != null) {
            return Response.ok(issues).build();
        } else {
            return Response.status(Response.Status.FORBIDDEN).build();
        }
    }
}
