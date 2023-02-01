package io.thoughtscript.example.controllers;

import io.thoughtscript.example.Constants;
import io.thoughtscript.example.security.PasswordlessReactiveAuthenticator;
import io.thoughtscript.example.services.LanguageReactiveWebService;
import io.thoughtscript.example.transfer.auth.AuthenticatedLanguageUpdate;
import io.thoughtscript.example.transfer.auth.AuthenticatedUuid;
import io.thoughtscript.example.transfer.auth.TokenAuth;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@Slf4j
@RestController
public class LanguageReactiveRestController {

    @Autowired
    PasswordlessReactiveAuthenticator passwordlessReactiveAuthenticator;

    @Autowired
    LanguageReactiveWebService languageReactiveWebService;

    // POST since token should be sent via Request Body
    @PostMapping(Constants.API_FLUX_LANGUAGES_ONE)
    public Mono<ResponseEntity> getOneLanguage(@RequestBody AuthenticatedUuid tokenAuth) {
        return passwordlessReactiveAuthenticator.
                authenticate(tokenAuth)
                .log()
                .flatMap(t -> {
                    if (t) {
                        return languageReactiveWebService
                                .findOneLanguageById(tokenAuth.getName())
                                .log()
                                .flatMap(x -> Mono.just(new ResponseEntity(x, HttpStatus.OK)));
                    }

                    return Mono.just(new ResponseEntity("Please supply a valid token!", HttpStatus.UNAUTHORIZED));
                });
    }

    @PostMapping(Constants.API_FLUX_LANGUAGES_NEW)
    public Mono<Object> saveOneLanguage(@RequestBody AuthenticatedLanguageUpdate tokenAuth) {
        return passwordlessReactiveAuthenticator.
                authenticate(tokenAuth.getTokenUsername(), tokenAuth.getToken())
                .log()
                .flatMap(t -> {
                    if (t) {
                        return languageReactiveWebService
                                .saveLanguage(tokenAuth.getName(), tokenAuth.getNewGreetings())
                                .log()
                                .flatMap(x -> Mono.just(new ResponseEntity(x, HttpStatus.OK)));
                    }

                    return Mono.just(new ResponseEntity("Please supply a valid token!", HttpStatus.UNAUTHORIZED));
                });
    }

    @DeleteMapping(Constants.API_FLUX_LANGUAGES_ONE)
    public Mono<ResponseEntity> deleteOneLanguage(@RequestBody AuthenticatedUuid tokenAuth) {
        return passwordlessReactiveAuthenticator.
                authenticate(tokenAuth)
                .log()
                .flatMap(t -> {
                    if (t) {
                        languageReactiveWebService.deleteLanguage(tokenAuth.getName());
                        return Mono.just(new ResponseEntity("Success", HttpStatus.OK));
                    }

                    return Mono.just(new ResponseEntity("Please supply a valid token!", HttpStatus.UNAUTHORIZED));
                });
    }

    @PutMapping(Constants.API_FLUX_LANGUAGES_ONE)
    public Mono<ResponseEntity> updateOneLanguage(@RequestBody AuthenticatedLanguageUpdate tokenAuth) {
        return passwordlessReactiveAuthenticator.
                authenticate(tokenAuth.getTokenUsername(), tokenAuth.getToken())
                .log()
                .flatMap(t -> {
                    if (t) {
                        return languageReactiveWebService
                                .updateLanguage(tokenAuth.getName(), tokenAuth.getNewGreetings())
                                .log()
                                .flatMap(x -> Mono.just(new ResponseEntity(x, HttpStatus.OK)));
                    }

                    return Mono.just(new ResponseEntity("Please supply a valid token!", HttpStatus.UNAUTHORIZED));
                });
    }

    @PostMapping(Constants.API_FLUX_LANGUAGES_ALL)
    public Mono<ResponseEntity> getAllLanguages(@RequestBody TokenAuth tokenAuth) {
        return passwordlessReactiveAuthenticator
                .authenticate(tokenAuth)
                .log()
                .flatMap(t -> {
                    if (t) {
                        return languageReactiveWebService
                                .findAllLanguages()
                                .flatMap(x -> Mono.just(new ResponseEntity(x, HttpStatus.OK)));
                    }

                    return Mono.just(new ResponseEntity("Please supply a valid token!", HttpStatus.UNAUTHORIZED));
                });
    }

    @GetMapping(Constants.API_UNSECURED_LANGUAGES_ALL)
    public Mono<ResponseEntity> getAllLanguages() {
        return languageReactiveWebService
                .findAllLanguages()
                .flatMap(x -> Mono.just(new ResponseEntity(x, HttpStatus.OK)));
    }
}
