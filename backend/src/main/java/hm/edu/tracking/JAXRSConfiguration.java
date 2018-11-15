package hm.edu.tracking;

import lombok.NoArgsConstructor;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

/**
 * Declares that this app exposes a REST interface and should have the REST services published under the /api/* URI.
 */
@ApplicationPath("api")
@NoArgsConstructor
public class JAXRSConfiguration extends Application {
}
