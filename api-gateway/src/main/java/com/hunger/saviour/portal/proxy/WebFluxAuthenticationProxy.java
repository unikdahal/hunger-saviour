package com.hunger.saviour.portal.proxy;

import com.hunger.saviour.portal.dtos.AuthRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.circuitbreaker.ReactiveCircuitBreakerFactory;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

/**
 * WebFluxAuthenticationProxy interacts with the `user-service` for token
 * validation and retrieval. It implements resilience patterns like circuit breakers
 * and retries using Resilience4j.
 */
@Slf4j
@Component
public class WebFluxAuthenticationProxy {

    private final WebClient webClient;
    private final ReactiveCircuitBreakerFactory reactiveCircuitBreakerFactory;

    @Value("${user-service.url}")
    private String userServiceBaseUrl;

    /**
     * Constructor to initialize WebClient and Resilience4j Circuit Breaker.
     *
     * @param webClientBuilder           The WebClient builder instance.
     * @param reactiveCircuitBreakerFactory The Resilience4j Circuit Breaker factory.
     */
    public WebFluxAuthenticationProxy(WebClient.Builder webClientBuilder, ReactiveCircuitBreakerFactory reactiveCircuitBreakerFactory) {
        this.webClient = webClientBuilder.build();
        this.reactiveCircuitBreakerFactory = reactiveCircuitBreakerFactory;
    }

    /**
     * Retrieves an access token for authentication through the `user-service`.
     *
     * @param authRequest The authentication payload containing credentials.
     * @return A {@link Mono} containing the retrieved token.
     */
    public Mono<String> getToken(AuthRequest authRequest) {
        log.info("Sending authentication request to user-service");
        return webClient
                .post()
                .uri(userServiceBaseUrl + "/users/login")
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(authRequest)
                .retrieve()
                .bodyToMono(String.class)
                .subscribeOn(Schedulers.boundedElastic())
                .doOnSuccess(result -> log.info("Token retrieved successfully"))
                .doOnError(e -> log.error("Error while retrieving token: {}", e.getMessage()));
    }

    /**
     * Validates a token by communicating with the `user-service` through WebClient.
     * Uses a Resilience4j circuit breaker for fault tolerance.
     *
     * @param token The Bearer token to validate.
     * @return A {@link Mono} containing the validation result (true/false).
     */
    public Mono<Boolean> validateToken(String token) {
        log.info("Validating token with user-service");
        return reactiveCircuitBreakerFactory.create("userServiceCircuitBreaker")
                .run(webClient
                                .get()
                                .uri(uriBuilder -> uriBuilder
                                        .path(userServiceBaseUrl + "/users/validate")
                                        .queryParam("token", token)
                                        .build())
                                .retrieve()
                                .bodyToMono(Boolean.class)
                                .subscribeOn(Schedulers.boundedElastic())
                                .doOnSuccess(isValid -> log.info("Token validation result: {}", isValid)),
                        throwable -> {
                            log.warn("Fallback triggered during token validation. Assuming token is invalid.");
                            return Mono.just(false);
                        });
    }
}