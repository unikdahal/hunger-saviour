package com.hunger.saviour.portal.filter;

import com.hunger.saviour.portal.exceptions.UnauthorizedException;
import com.hunger.saviour.portal.proxy.WebFluxAuthenticationProxy;
import com.hunger.saviour.portal.util.ErrorResponseUtil;
import com.hunger.saviour.portal.util.TokenUtils;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.MDC;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import java.util.UUID;

/**
 * AuthenticationFilter is responsible for filtering incoming requests and validating
 * authorization tokens for secure routes. It delegates token validation to the
 * {@link WebFluxAuthenticationProxy}, applies retry mechanisms, and provides request
 * ID tracking.
 */
@Component
@Slf4j
public class AuthenticationFilter extends AbstractGatewayFilterFactory<AuthenticationFilter.Config> {

    private final RouteValidator validator;
    private final WebFluxAuthenticationProxy authenticationProxy;

    /**
     * Constructor to initialize dependencies for {@link AuthenticationFilter}.
     *
     * @param validator           {@link RouteValidator} to check if a route is secure.
     * @param authenticationProxy {@link WebFluxAuthenticationProxy} to handle token validation.
     */
    public AuthenticationFilter(RouteValidator validator, WebFluxAuthenticationProxy authenticationProxy) {
        super(Config.class);
        this.validator = validator;
        this.authenticationProxy = authenticationProxy;
    }

    /**
     * Applies the filter to validate tokens on secure routes.
     *
     * @param config Configuration object for the filter.
     * @return {@link GatewayFilter} that processes the request.
     */
    @Override
    public GatewayFilter apply(Config config) {
        return (exchange, chain) -> {
            // Generate a request ID for observability
            String requestId = UUID.randomUUID().toString();
            MDC.put("requestId", requestId);
            log.info("Processing incoming request - ID: {}, Path: {}", requestId, exchange.getRequest().getPath());

            // Verify if the route is secure
            if (validator.isSecuredRoute(exchange.getRequest())) {
                log.info("Secured route detected - ID: {}", requestId);

                // Check for Authorization header
                if (!exchange.getRequest().getHeaders().containsKey(HttpHeaders.AUTHORIZATION)) {
                    return ErrorResponseUtil.onError(exchange, "Missing Authorization Header", HttpStatus.UNAUTHORIZED, requestId);
                }

                try {
                    // Extract and validate token
                    String token = TokenUtils.extractToken(exchange.getRequest().getHeaders().getFirst(HttpHeaders.AUTHORIZATION));

                    // Validate the token through a proxy service
                    return authenticationProxy.validateToken(token)
                            .flatMap(isValid -> {
                                if (Boolean.TRUE.equals(isValid)) {
                                    log.info("Token validation successful - ID: {}", requestId);
                                    return chain.filter(exchange); // Allow the request if token is valid
                                } else {
                                    return ErrorResponseUtil.onError(exchange, "Invalid Token", HttpStatus.UNAUTHORIZED, requestId);
                                }
                            })
                            .onErrorResume(error -> ErrorResponseUtil.handleError(exchange, error, requestId)); // Handle validation errors
                } catch (UnauthorizedException ex) {
                    return ErrorResponseUtil.onError(exchange, ex.getMessage(), HttpStatus.UNAUTHORIZED, requestId);
                }
            }

            return chain.filter(exchange); // Allow non-secure routes directly
        };
    }

    /**
     * Configuration class for injecting filter-specific properties in the future.
     */
    public static class Config {
    }
}