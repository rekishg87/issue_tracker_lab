package nl.rivium.resources;

import nl.rivium.entities.Issue;

import javax.enterprise.context.ApplicationScoped;
import javax.persistence.*;
import javax.servlet.ServletException;
import javax.servlet.http.*;
import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.IOException;
import java.util.List;

/**
 * Created by Rekish on 9/14/2015.
 */

@ApplicationScoped
@Path("issues")
public class IssueResource {
    @Context
    private static HttpServletRequest request;

    //@PersistenceContext(name = "issueUnit")
    private EntityManagerFactory factory =
            Persistence.createEntityManagerFactory("issueUnit");
    private EntityManager manager = factory.createEntityManager();



    @GET()
    @Path("create/{name}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response create (@PathParam(value = "name") String name) {
        manager.getTransaction().begin();

        Issue issue = new Issue();
        issue.setCategoryId(10);
        issue.setDescription(name);
        issue.setStatusId(10);
        issue.setPriorityId(10);
        issue.setAssigneeId(10);
        manager.persist(issue);
        manager.getTransaction().commit();

        return Response.ok(issue).build();
    }

    @GET
    @Path("all")
    @Produces(MediaType.APPLICATION_JSON)
    //@Consumes(MediaType.APPLICATION_JSON)
    public Response all() {

        List<Issue> listIssues =
                manager.createQuery("SELECT i FROM Issue i").getResultList();
        request.getSession(true);

//        Cookie[] cookies = request.getCookies();
//        for(int i = 0; i < cookies.length; i++){
//            Cookie cookie = cookies[i];
//            if(("JSESSIONID".equals(cookie.getName()))){
//                cookie.setMaxAge(24*50*60); //max age of one day
//            }
//        }

        if (request.getSession(false) != null) {
            return Response.ok(listIssues).build();
        } else {
            return Response.status(Response.Status.FORBIDDEN).build();
        }
    }

    @GET
    @Path("/get/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response id(@PathParam(value = "id") int id) {
        Query query = manager.createQuery("SELECT i FROM Issue i WHERE i.id = :id");
        query.setParameter("id", id);

        List<Issue> listIssues = query.getResultList();

        return Response.ok(listIssues).build();
    }
}
