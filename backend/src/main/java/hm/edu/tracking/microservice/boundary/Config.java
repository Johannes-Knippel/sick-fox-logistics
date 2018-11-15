package hm.edu.tracking.microservice.boundary;

import lombok.NoArgsConstructor;

import javax.inject.Inject;
import javax.json.*;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.Map;

/**
 * Exposes MicroProfile configs configInstance via REST endpoint
 * <p>
 * "api/configInstance" enables you to GET all configuration items
 * "api/configInstance/{configId}" enables you to GET a specific configuration item
 */
@Path("config")
@NoArgsConstructor
public class Config {
    // Get the Config based on all ConfigSources of the
    // current Thread Context ClassLoader (TCCL)
    @Inject
    private org.eclipse.microprofile.config.Config configInstance;

    /**
     * Returns all available configuration items from the microprofile config API
     *
     * @return all configuration items
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response configValues() {
        JsonBuilderFactory factory = Json.createBuilderFactory(null);
        JsonArrayBuilder sources = factory.createArrayBuilder();
        configInstance.getConfigSources().forEach(source -> {
            JsonObjectBuilder props = factory.createObjectBuilder();
            source.getProperties()
                    .entrySet()
                    .stream()
                    .sorted(Map.Entry.comparingByKey())
                    .forEachOrdered(
                            prop -> props.add(prop.getKey(), prop.getValue()));
            sources.add(factory.createObjectBuilder()
                    // Add the name of the ConfigSource
                    .add("name", source.getName())
                    // Add the ordinal of the ConfigSource
                    .add("ordinal", source.getOrdinal())
                    // Add properties of the ConfigSource
                    .add("properties", props));
        });
        JsonObject result = factory.createObjectBuilder()
                .add("configSources", sources)
                .build();

        return Response.ok(result).build();
    }

    /**
     * Get a specific configuration item from microprofiles config API
     *
     * @param key of the configuration item
     * @return the requested configuration item
     */
    @GET
    @Path("{key}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response configValue(@PathParam("key") String key) {
        JsonObjectBuilder builder = Json.createObjectBuilder();
        JsonObject result = configInstance.getOptionalValue(key, String.class)
                .map(value -> builder.add(key, value))
                .orElse(builder)
                .build();
        return Response.ok(result).build();
    }

}
