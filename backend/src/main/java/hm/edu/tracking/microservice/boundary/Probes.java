
package hm.edu.tracking.microservice.boundary;

import hm.edu.tracking.senseit.boundary.SenseItRepo;
import lombok.NoArgsConstructor;
import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponses;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

/**
 * Health-Check probes for OpenShift.
 * These are being used when deploying the application on OpenShift to determine if the application is ready and live.
 */
@Path("probes")
@NoArgsConstructor
public class Probes {

    @Inject
    private SenseItRepo service;

    /**
     * OpenShift liveness-probe for health checks
     *
     * @return 200 OK when app is available
     */
    @GET
    @Path("liveness")
    @Operation(
            summary = "Liveness probe",
            description = "Acts as a liveness probe for this microservice.")
    @APIResponses(
            value = {
                    @APIResponse(
                            responseCode = "200",
                            description = "Application is live.")})
    public Response liveness() {
        return Response.ok().build();
    }

    /**
     * OpenShift readiness-probe for health checks
     *
     * @return 200 OK when app is available
     */
    @GET
    @Path("readiness")
    @Operation(
            summary = "Readiness probe",
            description = "Acts as a readiness probe for this microservice.")
    @APIResponses(
            value = {
                    @APIResponse(
                            responseCode = "200",
                            description = "Application is ready.")})
    public Response readiness() {
        return Response.ok().build();
    }

}
