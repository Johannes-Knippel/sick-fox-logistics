package hm.edu.tracking;

import lombok.NoArgsConstructor;
import org.eclipse.microprofile.openapi.annotations.OpenAPIDefinition;
import org.eclipse.microprofile.openapi.annotations.info.Contact;
import org.eclipse.microprofile.openapi.annotations.info.Info;
import org.eclipse.microprofile.openapi.annotations.servers.Server;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

/**
 * Declares that this app exposes a REST interface and should have the REST services published under the /api/* URI.
 */
@ApplicationPath("api")
@OpenAPIDefinition(
        info = @Info(
                title = "Bookstore",
                version = "1.0",
                description = "API of the bookstore service",
                contact = @Contact(name = "Matthias Strobl", email = "matthias.strobl@bmw.de")
        ),
        servers = {
                @Server(
                        description = "Integration instance",
                        url = "https://localhost:8080/tracking/")
        }
)
@NoArgsConstructor
public class JAXRSConfiguration extends Application {
}
