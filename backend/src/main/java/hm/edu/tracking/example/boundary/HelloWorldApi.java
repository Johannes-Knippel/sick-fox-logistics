package hm.edu.tracking.example.boundary;

import hm.edu.tracking.example.entity.Message;
import lombok.NoArgsConstructor;
import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.media.Content;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponses;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 * A simple hello world service.
 * Takes the name given in the URL path and greets this person.
 */
@Path("hello")
@NoArgsConstructor
public class HelloWorldApi {

    /**
     * Returns a Hello World {@link Message}.
     *
     * @returns the message "Hello World"
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @APIResponses(
            value = {
                    @APIResponse(
                            responseCode = "200",
                            description = "Hello World.",
                            content = @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = Message.class))),}
    )
    @Operation(
            summary = "Get Hello World message",
            description = "Returns a Hello World message.")
    public Message hello() {
        return new Message("Hello World");
    }

    /**
     * Returns a Hello World message as plain text.
     *
     * @returns the String "Hello World"
     */
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    @APIResponses(
            value = {
                    @APIResponse(
                            responseCode = "200",
                            description = "Hello World.",
                            content = @Content(mediaType = "text/plain")),}
    )
    @Operation(
            summary = "Get Hello World",
            description = "Returns Hello World as plain text.")
    public String helloPlainText() {
        return "Hello World";
    }

    /**
     * Returns a Hello World {@link Message} for the given name.
     * The name is taken from the path parameter 'name'.
     *
     * @param name to greet
     * @return the concatenated message
     */
    @GET
    @Path("{name}")
    @Produces(MediaType.APPLICATION_JSON)
    @APIResponses(
            value = {
                    @APIResponse(
                            responseCode = "200",
                            description = "Hello World.",
                            content = @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = Message.class))),}
    )
    @Operation(
            summary = "Get parameterized Hello message",
            description = "Returns a Hello message for the given path parameter.")
    public Message helloName(@PathParam("name") String name) {
        return new Message("Hello " + name);
    }

}
