package com.hunger.saviour.portal.filter;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Set;

@Service
@Slf4j
public class RouteValidator {

    // Set of open API endpoints dynamically loaded from application.yml
    private final Set<String> openApiEndpoints;

    /**
     * Constructor to initialize the open API endpoints.
     * This value is injected from the application's configuration.
     *
     * @param openApiEndpoints List of endpoints from application.yml
     */
    public RouteValidator(@Value("${application.open-api-endpoints}") Set<String> openApiEndpoints) {
        this.openApiEndpoints = Collections.unmodifiableSet(openApiEndpoints);
    }


    /**
     * Checks if the request targets a secured endpoint.
     * Secured routes are those not listed as open API endpoints.
     *
     * @param serverHttpRequest The HTTP request to validate.
     * @return True if the request targets a secured endpoint; False otherwise.
     */
    public boolean isSecuredRoute(ServerHttpRequest serverHttpRequest) {
        String requestPath = serverHttpRequest.getURI().getPath();

        // Return true if none of the open API endpoints match the request URI.
        boolean isSecured = openApiEndpoints.stream().noneMatch(requestPath::startsWith);
        log.info("Request Path: {}, Secured: {}", requestPath, isSecured);
        return isSecured;
    }
}
