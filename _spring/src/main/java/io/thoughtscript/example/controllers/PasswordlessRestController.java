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

@Slf4j
@RestController
public class PasswordlessRestController {

  @Autowired
  EmailService emailService;

  @Autowired
  PasswordlessAuthenticator passwordlessAuthenticator;

  @PostMapping(AUTH_LOGIN_ENDPOINT)
  public void generateMagicLink(@RequestBody ForMagicLink forMagicLink) {
    try {
      String username = forMagicLink.getUsername();
      String email = forMagicLink.getEmail();
      String token = passwordlessAuthenticator.generateToken(username);
      emailService.sendMagicEmail(email, username, token);
    } catch (Exception ex) {
      log.error("Exception sending magic email: " + ex);
    }
  }

}
