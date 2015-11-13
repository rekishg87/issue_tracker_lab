package nl.rivium.resources;

import nl.rivium.dao.StatusDAO;
import nl.rivium.dao.StatusDAOImpl;
import nl.rivium.entities.Status;

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
@Path("status")
public class StatusResource {
    private final StatusDAO STATUS_DAO = new StatusDAOImpl();

    @Context
    private static HttpServletRequest request;

    private EntityManagerFactory factory =
            Persistence.createEntityManagerFactory("issueUnit");
    private EntityManager manager = factory.createEntityManager();

    @GET
    @Path("all")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getStatus() {
        final List<Status> issues = STATUS_DAO.getStatusList();

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
