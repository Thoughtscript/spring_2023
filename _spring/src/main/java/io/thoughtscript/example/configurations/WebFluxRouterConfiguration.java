package io.thoughtscript.example.configurations;

import static io.thoughtscript.example.Constants.*;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.web.reactive.function.server.RequestPredicates.DELETE;
import static org.springframework.web.reactive.function.server.RequestPredicates.GET;
import static org.springframework.web.reactive.function.server.RequestPredicates.POST;
import static org.springframework.web.reactive.function.server.RequestPredicates.PUT;
import static org.springframework.web.reactive.function.server.RequestPredicates.accept;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;

import io.thoughtscript.example.services.FunctionalRouterWebHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.web.reactive.function.server.RouterFunctions.route;

@Configuration
public class WebFluxRouterConfiguration {
    @Bean
    public FunctionalRouterWebHandler handler() {
        return new FunctionalRouterWebHandler();
    }

        @Bean
        @Autowired
        public RouterFunction<ServerResponse> routes(FunctionalRouterWebHandler handler) {
            return route(POST(API_ROUTER_USER_ONE).and(accept(APPLICATION_JSON)), handler::getOneUser)
                    .andRoute(POST(API_ROUTER_USER_NEW).and(accept(APPLICATION_JSON)), handler::saveOneUser)
                    .andRoute(DELETE(API_ROUTER_USER_ONE).and(accept(APPLICATION_JSON)), handler::deleteOneUser)
                    .andRoute(PUT(API_ROUTER_USER_ONE).and(accept(APPLICATION_JSON)), handler::updateOneUser)
                    .andRoute(GET(API_ROUTER_USER_ALL).and(accept(APPLICATION_JSON)), handler::getAllUsers);
        }

}