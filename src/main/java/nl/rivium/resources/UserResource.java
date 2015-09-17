package nl.rivium.resources;

import nl.rivium.dao.UserDAO;
import nl.rivium.dao.UserDAOImpl;
import nl.rivium.entities.User;

import javax.enterprise.context.ApplicationScoped;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

/**
 * Created by Rekish on 9/15/2015.
 */

@ApplicationScoped
@Path("user")
public class UserResource {
    private static final UserDAO USER_DAO = new UserDAOImpl();
    @Context
    private static HttpServletRequest request;

    private EntityManagerFactory factory =
            Persistence.createEntityManagerFactory("issueUnit");
    private EntityManager manager = factory.createEntityManager();

    @POST
    @Path("login")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response auth (User input) {
        final boolean userFound = USER_DAO.auth(input.getUsername(), input.getPassword());

        if(userFound) {
            return Response.status(Response.Status.OK).entity(userFound).build();
        } else {
            return Response.status(Response.Status.FORBIDDEN).build();
        }

//        List<User> listUsers =
//                manager.createQuery("SELECT u FROM User u").getResultList();
//        System.out.println(listUsers);

//        if("rekish".equals(input.getUsername())) {
//            request.getSession(true);
//            return Response.ok().build();
//        } else {
//            return Response.status(Response.Status.FORBIDDEN).build();
//        }
        //return Response.ok(listUsers).build();
    }
}
