package io.thoughtscript.example.controllers;

import static io.thoughtscript.example.Constants.AUTH_LOGIN_ENDPOINT;

import io.thoughtscript.example.security.PasswordlessAuthenticator;
import io.thoughtscript.example.services.EmailService;
import io.thoughtscript.example.transfer.auth.ForMagicLink;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;
import java.util.List;
import java.util.concurrent.*;

@Slf4j
@RestController
public class PasswordlessRestController {

  @Autowired
  EmailService emailService;

  @Autowired
  PasswordlessAuthenticator passwordlessAuthenticator;

  private ExecutorService nonBlockingService = Executors.newSingleThreadExecutor();

  @PostMapping(AUTH_LOGIN_ENDPOINT)
  public void generateMagicLink(@RequestBody ForMagicLink forMagicLink) {
    try {
      String email = forMagicLink.getEmail();
      String token = passwordlessAuthenticator.generateToken(email);
      nonBlockingService.execute(() -> {
        emailService.sendMagicEmail(email, token);
      });
    } catch (Exception ex) {
      log.error("Exception sending magic email: " + ex);
    }
  }

}
