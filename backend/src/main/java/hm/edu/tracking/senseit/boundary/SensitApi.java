package hm.edu.tracking.senseit.boundary;

import hm.edu.tracking.senseit.entity.SenseitMessage;
import lombok.NoArgsConstructor;
import lombok.extern.java.Log;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.*;
import java.util.List;
import java.util.logging.Level;

@Path("senseit")
@RequestScoped
@NoArgsConstructor
@Log //Other loggers e.g. via @Log4j, @Log4j2
public class SensitApi {

    @Inject
    private SenseItRepo service;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getBooks() {
        log.info("SensitApi: GET request on /api/senseit");
        List<SenseitMessage> senseitMessages = service.getAllBooks();
        if (senseitMessages.isEmpty()) {
            return Response.noContent().build();
        }
        // Wrap collection in order to avoid type loss
        GenericEntity<List<SenseitMessage>> booksEntity = new GenericEntity<List<SenseitMessage>>(senseitMessages) {
        };
        return Response.ok(booksEntity).build();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/{name}")
    public Response getBooksPerName(@PathParam("name") String name) {
        log.info("SensitApi: GET request on /api/senseit");
        List<SenseitMessage> senseitMessages = service.getAllBooks();

        senseitMessages.removeIf(mes -> !mes.getName().equals(name));

        if (senseitMessages.isEmpty()) {
            return Response.noContent().build();
        }
        // Wrap collection in order to avoid type loss
        GenericEntity<List<SenseitMessage>> booksEntity = new GenericEntity<List<SenseitMessage>>(senseitMessages) {
        };
        return Response.ok(booksEntity).build();
    }

    @GET
    @Path("/{id:[0-9]*}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getBook(@PathParam("id") long id) {
        log.log(Level.INFO, "SensitApi: GET request on /api/senseit/{0}", id);
        if (service.hasEntity(id)) {
            return Response.ok(service.find(id)).build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addBook(SenseitMessage newSenseitMessage,
                            @Context UriInfo uriInfo) {
        log.info("SensitApi: POST request on /api/senseit");
        Response.ResponseBuilder builder;
        SenseitMessage created = service.createOrUpdate(newSenseitMessage);
        log.info(() -> String.format("SenseitMessage %d created.", created.getId()));
        builder = Response.created(uriInfo.getAbsolutePathBuilder()
                .path(Long.toString(created.getId())).build()).entity(created);
        return builder.build();
    }

    @DELETE
    @Path("/{id:[0-9]*}")
    public Response deleteBook(@PathParam("id") long id) {
        log.info("SensitApi: DELETE request on /api/senseit");
        if (service.hasEntity(id)) {
            SenseitMessage toBeDeleted = service.find(id);
            service.remove(toBeDeleted);
            log.info(() -> String.format("SenseitMessage %d deleted.", id));
            return Response.ok().build();
        } else {
            log.warning(() -> String.format("SenseitMessage %d not present.", id));
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }


}
