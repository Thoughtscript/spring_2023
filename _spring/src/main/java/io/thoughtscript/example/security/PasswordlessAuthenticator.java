package io.thoughtscript.example.security;

import io.thoughtscript.example.domain.RedisAuthentication;
import io.thoughtscript.example.reactiverepositories.AuthenticationRedisReactiveRepository;
import io.thoughtscript.example.transfer.auth.TokenAuth;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Slf4j
@Service
public class PasswordlessAuthenticator {

    @Autowired
    AuthenticationRedisReactiveRepository authenticationRedisReactiveRepository;

    public Mono<Boolean> authenticate(TokenAuth tokenAuth) {
        return authenticationRedisReactiveRepository
                .findOneByUsername(tokenAuth.getTokenUsername())
                .flatMap(principal -> {

                    if (principal != null) {

                        boolean isValidName = tokenAuth.getTokenUsername().equals(principal.getUsername());
                        boolean isValidToken = tokenAuth.getToken().equals(principal.getToken());
                        boolean hasExpired = !principal.hasExpired();

                        return Mono.just(isValidName && isValidToken && hasExpired);
                    }

                    return Mono.just(false);
                });
    }

    public Mono<Boolean> authenticate(String username, String token) {
      return authenticationRedisReactiveRepository
              .findOneByUsername(username)
              .flatMap(principal -> {

                if (principal != null) {

                  boolean isValidName = username.equals(principal.getUsername());
                  boolean isValidToken = token.equals(principal.getToken());
                  boolean hasExpired = !principal.hasExpired();

                  return Mono.just(isValidName && isValidToken && hasExpired);
                }

                return Mono.just(false);
              });
    }

    public String generateToken(String username) {

        double x = (Math.random() * 26);
        double y = (Math.random() * 36);

        StringBuffer token = new StringBuffer();

        for (int i = 0; i < y; i++) {
          double ascii = Math.floor(x + 97);
          String asciiStr = String.valueOf(ascii);
          token.append(Character.codePointOf(asciiStr));
        }

        authenticationRedisReactiveRepository.save(new RedisAuthentication(username, token.toString())).log().subscribe();

        return token.toString();
    }

}