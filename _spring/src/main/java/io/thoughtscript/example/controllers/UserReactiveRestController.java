package io.thoughtscript.example.controllers;

import io.thoughtscript.example.Constants;
import io.thoughtscript.example.domain.email.MongoEmail;
import io.thoughtscript.example.domain.user.MongoUser;
import io.thoughtscript.example.security.PasswordlessAuthenticator;
import io.thoughtscript.example.services.UserReactiveWebService;
import io.thoughtscript.example.transfer.auth.AuthenticatedUpdate;
import io.thoughtscript.example.transfer.auth.AuthenticatedUuid;
import io.thoughtscript.example.transfer.auth.TokenAuth;
import io.thoughtscript.example.transfer.response.CustomResponse;
import java.util.ArrayList;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ServerWebInputException;
import reactor.core.publisher.Mono;

/**
 * X-Team WebFlux Protected Reactive User Controller.
 *
 * @Author - Adam InTae Gerard - https://www.linkedin.com/in/adamintaegerard/
 */

@Slf4j
@RestController
public class UserReactiveRestController {

  @Autowired
  PasswordlessAuthenticator passwordlessAuthenticator;

  @Autowired
  UserReactiveWebService userReactiveWebService;

  /**
   * Helpers.
   */

  @ExceptionHandler
  public CustomResponse handleMissingParams(ServerWebInputException ex) {
    return new CustomResponse(null, null, "Please supply a username and email address!");
  }

  /**
   * CRUD Operations.
   */

  @PostMapping(Constants.API_FLUX_USER_ONE)
  public Mono<CustomResponse> getOneUser(@RequestBody AuthenticatedUuid tokenAuth) {
    Boolean auth = passwordlessAuthenticator.authenticate(tokenAuth).block();
    MongoUser user = new MongoUser(tokenAuth.getUsername(), null, null, null, null);
    try {
      if (auth) user = userReactiveWebService.findOneUserById(tokenAuth.getId()).block();
    } catch (Exception ex) {
      log.error("Exception: " + ex);
      return Mono.just(new CustomResponse(user, "Failed!"));
    }
    return Mono.just(new CustomResponse(user, auth ? "Success!" : "Failed!"));
  }

  @PostMapping(Constants.API_FLUX_USER_NEW)
  public Mono<CustomResponse> saveOneUser(@RequestBody AuthenticatedUpdate tokenAuth) {
    Boolean auth = passwordlessAuthenticator.authenticate(tokenAuth.getUsername(), tokenAuth.getToken()).block();
    log.info(tokenAuth.getNewUsername() + tokenAuth.getNewName() + tokenAuth.getNewPhone() + tokenAuth.getNewEmail());
    MongoUser user = new MongoUser(tokenAuth.getNewUsername(), tokenAuth.getNewName(), tokenAuth.getNewPhone(), new MongoEmail(tokenAuth.getNewEmail()), null);
    try {
      log.info(user.toString());
      if (auth) userReactiveWebService
            .saveUser(tokenAuth.getNewUsername(),
                tokenAuth.getNewName(),
                tokenAuth.getNewPhone(),
                tokenAuth.getNewEmail());
    } catch (Exception ex) {
      log.error("Exception: " + ex);
      return Mono.just(new CustomResponse(user,"Failed!"));
    }
    return Mono.just(new CustomResponse(user, auth ? "Success!" : "Failed!"));
  }

  @DeleteMapping(Constants.API_FLUX_USER_ONE)
  public Mono<CustomResponse> deleteOneUser(@RequestBody AuthenticatedUuid tokenAuth) {
    Boolean auth = passwordlessAuthenticator.authenticate(tokenAuth).block();
    MongoUser user = new MongoUser(tokenAuth.getId(), null, null, null, null);
    try {
      if (auth) userReactiveWebService.deleteUser(tokenAuth.getId());
    } catch (Exception ex) {
      log.error("Exception: " + ex);
      return Mono.just(new CustomResponse(user,"Failed!"));
    }
    return Mono.just(new CustomResponse(user, auth ? "Success!" : "Failed!"));
  }

  @PutMapping(Constants.API_FLUX_USER_ONE)
  public Mono<CustomResponse> updateOneUser(@RequestBody AuthenticatedUpdate tokenAuth) {
    Boolean auth = passwordlessAuthenticator.authenticate(tokenAuth.getUsername(), tokenAuth.getToken()).block();
    log.info(tokenAuth.getNewUsername() + tokenAuth.getNewName() + tokenAuth.getNewPhone() + tokenAuth.getNewEmail());
    MongoUser user = new MongoUser(tokenAuth.getNewUsername(), tokenAuth.getNewName(), tokenAuth.getNewPhone(), new MongoEmail(tokenAuth.getNewEmail()), null);
    try {
      if (auth) userReactiveWebService
            .updateUser(tokenAuth.getNewUsername(),
                tokenAuth.getNewName(),
                tokenAuth.getNewPhone(),
                tokenAuth.getNewEmail());
    } catch (Exception ex) {
      log.error("Exception: " + ex);
      return Mono.just(new CustomResponse(user, "Failed!"));
    }
    return Mono.just(new CustomResponse(user, auth ? "Success!" : "Failed!"));
  }

  @PostMapping(Constants.API_FLUX_USER_ALL)
  public Mono<CustomResponse> getAllUsers(@RequestBody TokenAuth tokenAuth) {
    Boolean auth = passwordlessAuthenticator.authenticate(tokenAuth).block();
    List<MongoUser> userList = new ArrayList<>();
    try {
      if (auth) {
        userList = userReactiveWebService.findAllUsers().block();
      }
    } catch (Exception ex) {
      log.error("Exception: " + ex);
      return Mono.just(new CustomResponse(userList, "Failed!"));
    }
    return Mono.just(new CustomResponse(userList, (auth) ? "Success!" : "Failed!"));
  }
}