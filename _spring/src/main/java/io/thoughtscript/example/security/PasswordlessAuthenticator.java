package io.thoughtscript.example.security;

import io.thoughtscript.example.domain.RedisAuthentication;
import io.thoughtscript.example.repositories.AuthenticationRedisBlockingRepository;
import io.thoughtscript.example.transfer.auth.TokenAuth;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class PasswordlessAuthenticator {

    @Autowired
    AuthenticationRedisBlockingRepository authenticationRedisBlockingRepository;

    public Boolean authenticate(TokenAuth tokenAuth) {
        RedisAuthentication principal = authenticationRedisBlockingRepository.findOneByUsernameBlocking(tokenAuth.getTokenUsername());

        if (principal != null) {

            boolean isValidName = tokenAuth.getTokenUsername().equals(principal.getUsername());
            boolean isValidToken = tokenAuth.getToken().equals(principal.getToken());
            boolean hasExpired = !principal.hasExpired();

            return isValidName && isValidToken && hasExpired;
        }

        return false;
    }

    public Boolean authenticate(String username, String token) {
        RedisAuthentication principal = authenticationRedisBlockingRepository.findOneByUsernameBlocking(username);

        if (principal != null) {

            boolean isValidName = username.equals(principal.getUsername());
            boolean isValidToken = token.equals(principal.getToken());
            boolean hasExpired = !principal.hasExpired();

            return isValidName && isValidToken && hasExpired;
        }

        return false;
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

        authenticationRedisBlockingRepository.saveBlocking(new RedisAuthentication(username, token.toString()));

        return token.toString();
    }
}
