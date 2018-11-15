//======================================================================================================================
// Copyright (c) 2018 BMW Group. All rights reserved.
//======================================================================================================================
package hm.edu.tracking.microservice.boundary;

import org.eclipse.microprofile.health.Health;
import org.eclipse.microprofile.health.HealthCheck;
import org.eclipse.microprofile.health.HealthCheckResponse;

import javax.enterprise.context.ApplicationScoped;

/**
 * Microprofile /health endpoint to check the applications health
 */
@Health
@ApplicationScoped
public class HealthProbe implements HealthCheck {

    /**
     * Returns the health status of this application
     *
     * @return Health status of otd-ms-tmpl
     */
    @Override
    public HealthCheckResponse call() {
        return HealthCheckResponse
                .named("otd-ms-tmpl")
                .up()
                .withData("stage", "test")
                .build();
    }

}
