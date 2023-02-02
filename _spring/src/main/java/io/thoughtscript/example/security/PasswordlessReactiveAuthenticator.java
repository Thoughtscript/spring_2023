package io.thoughtscript.example.security;

import io.thoughtscript.example.domain.RedisAuthentication;
import io.thoughtscript.example.repositories.AuthenticationRedisReactiveRepository;
import io.thoughtscript.example.transfer.auth.TokenAuth;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Slf4j
@Service
public class PasswordlessReactiveAuthenticator {

    @Autowired
    AuthenticationRedisReactiveRepository authenticationRedisReactiveRepository;

    public Mono<Boolean> authenticate(TokenAuth tokenAuth) {
        return authenticationRedisReactiveRepository
                .findOneByUsername(tokenAuth.getTokenUsername())
                .log()
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

        //int x = (int) Math.round(Math.random() * 26);
        int y = (int) Math.round(Math.random() * 36);

        StringBuffer token = new StringBuffer();

        for (int i = 0; i < y; i++) {
            //int ascii = x + 97;
            //String asciiStr = String.valueOf(ascii);
            //token.append(Character.toChars(Character.codePointOf(asciiStr)));
            token.append("1");
        }

        authenticationRedisReactiveRepository.save(new RedisAuthentication(username, token.toString())).log().subscribe();

        return token.toString();
    }

}