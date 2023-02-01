package io.thoughtscript.example.services;

import io.thoughtscript.example.domain.Language;
import io.thoughtscript.example.domain.LanguageStudent;
import io.thoughtscript.example.reactiverepositories.LanguageMongoReactiveRepository;
import io.thoughtscript.example.reactiverepositories.LanguageStudentMongoReactiveRepository;
import io.thoughtscript.example.transfer.request.LanguageStudentRequestBody;
import io.thoughtscript.example.transfer.request.LanguageStudentUpdateRequestBody;
import io.thoughtscript.example.transfer.response.GenericResponseBody;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.List;

@Slf4j
public class FunctionalRouterWebHandler {

    @Autowired
    LanguageMongoReactiveRepository languageMongoReactiveRepository;

    @Autowired
    LanguageStudentMongoReactiveRepository languageStudentMongoReactiveRepository;

    /**
     * Handlers.
     */

    public Mono<ServerResponse> getOneLanguageStudent(ServerRequest request) {
        return request.bodyToMono(LanguageStudentRequestBody.class)
                .log()
                .flatMap(body -> languageStudentMongoReactiveRepository
                        .findById(body.getName())
                        .log()
                        .flatMap(l -> ServerResponse.ok().bodyValue(l)));
    }

    public Mono<ServerResponse> saveOneLanguageStudent(ServerRequest request) {
        return request.bodyToMono(LanguageStudentRequestBody.class)
                .log()
                .flatMap(body -> languageStudentMongoReactiveRepository
                        .save(new LanguageStudent(body.getName()))
                        .log()
                        .then(languageStudentMongoReactiveRepository
                                .findById(body.getName())
                                .log()
                                .flatMap(l -> ServerResponse.ok().bodyValue(l))
                        ));
    }

    public Mono<ServerResponse> deleteOneLanguageStudent(ServerRequest request) {
        return request.bodyToMono(LanguageStudentRequestBody.class)
                .log()
                .flatMap(body -> languageStudentMongoReactiveRepository
                        .deleteById(body.getName())
                        .log()
                        .flatMap(l -> ServerResponse.ok().bodyValue(new GenericResponseBody(body.getName() + " deleted"))));
    }

    public Mono<ServerResponse> updateOneLanguageStudent(ServerRequest request) {
        return request.bodyToMono(LanguageStudentUpdateRequestBody.class)
                .log()
                .flatMap(body -> languageStudentMongoReactiveRepository
                        .findById(body.getName())
                        .log()
                        .flatMap(t -> {

                            //t.setLanguages(body.getLanguages());
                            //t.setPrimaryLanguage(body.getPrimaryLanguage());
                            t.setPrimaryLanguageString(body.getPrimaryLanguage().getName());

                            List<String> languageIds = new ArrayList<>();

                            for (Language l : body.getLanguages()) {
                                languageIds.add(l.getName());
                                log.info(t.getName() + " also knows the language: " + l.getName());
                            }

                            t.setLanguageIds(languageIds);

                            return languageStudentMongoReactiveRepository
                                    .save(t)
                                    .then(languageStudentMongoReactiveRepository
                                            .findById(body.getName())
                                            .log()
                                            .flatMap(l -> ServerResponse.ok().bodyValue(l)));
                        }));
    }

    public Mono<ServerResponse> getAllLanguageStudent(ServerRequest request) {
        return languageStudentMongoReactiveRepository
                .findAll()
                .log()
                .collectList()
                .flatMap(l -> ServerResponse.ok().bodyValue(l));

    }
}
