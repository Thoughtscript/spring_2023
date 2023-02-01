package io.thoughtscript.example.services;

import io.thoughtscript.example.domain.Language;
import io.thoughtscript.example.repositories.LanguageMongoReactiveRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.List;

@Slf4j
@Service
public class LanguageReactiveWebService {

    @Autowired
    LanguageMongoReactiveRepository languageMongoReactiveRepository;

    public Mono<List<Language>> findAllLanguages() {
        return languageMongoReactiveRepository
                .findAll()
                .log().collectList();
    }

    public Mono<Language> findOneLanguageById(String name) {
        return languageMongoReactiveRepository.findById(name);
    }

    public Mono<Language> saveLanguage(String name) {
        return languageMongoReactiveRepository
                .save(new Language(name))
                .log()
                .flatMap(t -> languageMongoReactiveRepository
                        .findById(name));
    }

    public Mono<Language> saveLanguage(String name, String greeting) {
        return languageMongoReactiveRepository
                .save(new Language(name, greeting))
                .log()
                .flatMap(t -> languageMongoReactiveRepository
                        .findById(name));
    }

    public void deleteLanguage(String name) {
        languageMongoReactiveRepository.deleteById(name);
    }

    public Mono<Language> updateLanguage(String name, String greeting) {
        return languageMongoReactiveRepository
                .findById(name)
                .log()
                .flatMap(t -> {
                    t.setGreeting(greeting);
                    return languageMongoReactiveRepository
                            .save(t)
                            .log()
                            .then(languageMongoReactiveRepository.findById(name));
                });

    }
}
