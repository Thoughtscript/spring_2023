package io.thoughtscript.example.services;

import io.thoughtscript.example.domain.Language;
import io.thoughtscript.example.repositories.LanguageMongoReactiveRepository;
import io.thoughtscript.example.transfer.request.LanguageRequestBody;
import io.thoughtscript.example.transfer.request.LanguageUpdateRequestBody;
import io.thoughtscript.example.transfer.response.GenericResponseBody;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

@Slf4j
public class FunctionalRouterWebHandler {

    @Autowired
    LanguageMongoReactiveRepository languageMongoReactiveRepository;

    /**
     * Handlers.
     */

    public Mono<ServerResponse> getOneLanguage(ServerRequest request) {
        return request.bodyToMono(LanguageRequestBody.class)
                .log()
                .flatMap(body -> languageMongoReactiveRepository
                        .findById(body.getName())
                        .log()
                        .flatMap(l -> ServerResponse.ok().bodyValue(l)));
    }

    public Mono<ServerResponse> saveOneLanguage(ServerRequest request) {
        return request.bodyToMono(LanguageUpdateRequestBody.class)
                .log()
                .flatMap(body -> languageMongoReactiveRepository
                        .save(new Language(body.getName(), body.getGreeting()))
                        .log()
                        .then(languageMongoReactiveRepository
                                .findById(body.getName())
                                .log()
                                .flatMap(l -> ServerResponse.ok().bodyValue(l))
                        ));
    }

    public Mono<ServerResponse> deleteOneLanguage(ServerRequest request) {
        return request.bodyToMono(LanguageRequestBody.class)
                .log()
                .flatMap(body -> languageMongoReactiveRepository
                        .deleteById(body.getName())
                        .log()
                        .flatMap(l -> ServerResponse.ok().bodyValue(new GenericResponseBody(body.getName() + " deleted"))));
    }

    public Mono<ServerResponse> updateOneLanguage(ServerRequest request) {
        return request.bodyToMono(LanguageUpdateRequestBody.class)
                .log()
                .flatMap(body -> languageMongoReactiveRepository
                        .findById(body.getName())
                        .log()
                        .flatMap(t -> {
                            t.setGreeting(body.getGreeting());
                            return languageMongoReactiveRepository
                                    .save(t)
                                    .then(languageMongoReactiveRepository
                                            .findById(body.getName())
                                            .log()
                                            .flatMap(l -> ServerResponse.ok().bodyValue(l)));
                        }));
    }

    public Mono<ServerResponse> getAllLanguage(ServerRequest request) {
        return languageMongoReactiveRepository
                .findAll()
                .log()
                .collectList()
                .flatMap(l -> ServerResponse.ok().bodyValue(l));
    }
}
