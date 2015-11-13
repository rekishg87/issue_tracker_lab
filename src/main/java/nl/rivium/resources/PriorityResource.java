package nl.rivium.resources;

import nl.rivium.dao.PriorityDAO;
import nl.rivium.dao.PriorityDAOImpl;
import nl.rivium.entities.Priority;

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
@Path("priority")
public class PriorityResource {
    private final PriorityDAO PRIORITY_DAO = new PriorityDAOImpl();

    @Context
    private static HttpServletRequest request;

    private EntityManagerFactory factory =
            Persistence.createEntityManagerFactory("issueUnit");
    private EntityManager manager = factory.createEntityManager();

    @GET
    @Path("all")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getPriority() {
        final List<Priority> issues = PRIORITY_DAO.getPriorityList();

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
