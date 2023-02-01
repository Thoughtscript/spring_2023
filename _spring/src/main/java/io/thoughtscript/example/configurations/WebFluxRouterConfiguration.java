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

@Configuration
public class WebFluxRouterConfiguration {
    @Bean
    public FunctionalRouterWebHandler handler() {
        return new FunctionalRouterWebHandler();
    }

        @Bean
        @Autowired
        public RouterFunction<ServerResponse> routes(FunctionalRouterWebHandler handler) {
            // Must be a POST to accept body
            return route(POST(API_ROUTER_LANGUAGES_ONE).and(accept(APPLICATION_JSON)), handler::getOneLanguage)
                    .andRoute(POST(API_ROUTER_LANGUAGES_NEW).and(accept(APPLICATION_JSON)), handler::saveOneLanguage)
                    .andRoute(DELETE(API_ROUTER_LANGUAGES_ONE).and(accept(APPLICATION_JSON)), handler::deleteOneLanguage)
                    .andRoute(PUT(API_ROUTER_LANGUAGES_ONE).and(accept(APPLICATION_JSON)), handler::updateOneLanguage)
                    .andRoute(GET(API_ROUTER_LANGUAGES_ALL).and(accept(APPLICATION_JSON)), handler::getAllLanguage);
        }

}