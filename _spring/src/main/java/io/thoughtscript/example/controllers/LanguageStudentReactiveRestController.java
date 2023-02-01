package io.thoughtscript.example.controllers;

import io.thoughtscript.example.Constants;
import io.thoughtscript.example.security.PasswordlessAuthenticator;
import io.thoughtscript.example.services.LanguageStudentReactiveWebService;
import io.thoughtscript.example.transfer.auth.AuthenticatedUpdate;
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
public class LanguageStudentReactiveRestController {

    @Autowired
    PasswordlessAuthenticator passwordlessAuthenticator;

    @Autowired
    LanguageStudentReactiveWebService languageStudentReactiveWebService;

    /**
     * CRUD Operations.
     */

    @PostMapping(Constants.API_FLUX_USER_ONE)
    // Mono<LanguageStudent> will be automatically wrapped with a ResponseEntity
    public Mono<Object> getOneUser(@RequestBody AuthenticatedUuid tokenAuth) {
        return passwordlessAuthenticator
                .authenticate(tokenAuth)
                .log()
                .flatMap(t -> {
                    if (t) {
                        return languageStudentReactiveWebService
                                .findOneUserById(tokenAuth.getName())
                                .log()
                                .flatMap(x -> Mono.just(new ResponseEntity(x, HttpStatus.OK)));
                    }

                    return Mono.just(new ResponseEntity("Please supply a valid token!", HttpStatus.UNAUTHORIZED));
                });
    }

    @PostMapping(Constants.API_FLUX_USER_NEW)
    public Mono<Object> saveOneUser(@RequestBody AuthenticatedUpdate tokenAuth) {
        return passwordlessAuthenticator.
                authenticate(tokenAuth.getTokenUsername(), tokenAuth.getToken())
                .log()
                .flatMap(t -> {
                    if (t) {
                        return languageStudentReactiveWebService
                                .saveUser(tokenAuth.getName())
                                .log()
                                .flatMap(x -> Mono.just(new ResponseEntity(x, HttpStatus.OK)));
                    }

                    return Mono.just(new ResponseEntity("Please supply a valid token!", HttpStatus.UNAUTHORIZED));
                });
    }

    @DeleteMapping(Constants.API_FLUX_USER_ONE)
    public Mono<ResponseEntity> deleteOneUser(@RequestBody AuthenticatedUuid tokenAuth) {
        return passwordlessAuthenticator.
                authenticate(tokenAuth.getTokenUsername(), tokenAuth.getToken())
                .log()
                .flatMap(t -> {
                    if (t) {
                        languageStudentReactiveWebService.deleteUser(tokenAuth.getName());
                        return Mono.just(new ResponseEntity("Success", HttpStatus.OK));
                    }

                    return Mono.just(new ResponseEntity("Please supply a valid token!", HttpStatus.UNAUTHORIZED));
                });
    }

    @PutMapping(Constants.API_FLUX_USER_ONE)
    public Mono<ResponseEntity> updateOneUser(@RequestBody AuthenticatedUpdate tokenAuth) {
        return passwordlessAuthenticator.
                authenticate(tokenAuth.getTokenUsername(), tokenAuth.getToken())
                .log()
                .flatMap(t -> {
                    if (t) {
                        return languageStudentReactiveWebService
                                .updateUser(tokenAuth.getName(), tokenAuth.getNewPrimaryLanguage(), tokenAuth.getNewLanguages())
                                .log()
                                .flatMap(x -> Mono.just(new ResponseEntity(x, HttpStatus.OK)));
                    }

                    return Mono.just(new ResponseEntity("Please supply a valid token!", HttpStatus.UNAUTHORIZED));
                });
    }

    @PostMapping(Constants.API_FLUX_USER_ALL)
    public Mono<ResponseEntity> getAllUsers(@RequestBody TokenAuth tokenAuth) {
        return passwordlessAuthenticator.
                authenticate(tokenAuth.getTokenUsername(), tokenAuth.getToken())
                .log()
                .flatMap(t -> {
                    if (t) {
                        return languageStudentReactiveWebService
                                .findAllUsers()
                                .log()
                                .flatMap(x -> Mono.just(new ResponseEntity(x, HttpStatus.OK)));
                    }

                    return Mono.just(new ResponseEntity("Please supply a valid token!", HttpStatus.UNAUTHORIZED));
                });
    }
}