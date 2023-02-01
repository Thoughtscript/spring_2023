package io.thoughtscript.example.services;


import io.thoughtscript.example.domain.Language;
import io.thoughtscript.example.domain.LanguageStudent;
import io.thoughtscript.example.reactiverepositories.LanguageMongoReactiveRepository;
import io.thoughtscript.example.reactiverepositories.LanguageStudentMongoReactiveRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
public class LanguageStudentReactiveWebService {

    @Autowired
    LanguageStudentMongoReactiveRepository languageStudentMongoReactiveRepository;

    @Autowired
    LanguageMongoReactiveRepository languageMongoReactiveRepository;

    public Mono<LanguageStudent> findOneUserById(String name) {
        return languageStudentMongoReactiveRepository.findById(name);
    }

    public Mono<LanguageStudent> saveUser(String name) {
        return languageStudentMongoReactiveRepository
                .save(new LanguageStudent(name))
                .log()
                .flatMap(t -> languageStudentMongoReactiveRepository
                        .findById(name));
    }

    public void deleteUser(String name) {
        languageStudentMongoReactiveRepository.deleteById(name);
    }

    public Mono<LanguageStudent> updateUser(String name, Language primaryLanguage, List<Language> languages) {
        return languageStudentMongoReactiveRepository
                .findById(name)
                .log()
                .flatMap(t -> {

                    t.setPrimaryLanguageString(primaryLanguage.getName());

                    List<String> languageIds = new ArrayList<>();

                    for (Language l : languages) {
                        languageIds.add(l.getName());
                        log.info(t.getName() + " also knows the language: " + l.getName());
                    }

                    t.setLanguageIds(languageIds);

                    return languageStudentMongoReactiveRepository
                            .save(t)
                            .log()
                            .then(languageStudentMongoReactiveRepository.findById(name));
                });

    }

    public Mono<List<LanguageStudent>> findAllUsers() {
        return languageStudentMongoReactiveRepository
                .findAll()
                .log()
                .collectList();
    }
}
