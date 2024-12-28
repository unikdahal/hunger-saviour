package com.hunger.saviour.portal.filter;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class RouteValidator {

    // Set of open API endpoints dynamically loaded from application.yml
    public static final List<String> openApiEndpoints = List.of(
            "/users/login",
            "/users/validate",
            "/users/signup"
    );

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
