package io.thoughtscript.example.repositories;

import io.thoughtscript.example.domain.RedisAuthentication;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

@Slf4j
@Repository
public class AuthenticationRedisBlockingRepository {

    @Autowired
    RedisTemplate redisTemplate;

    private String toStringData(RedisAuthentication redisAuthentication) {
        return String.format("%s|%s|%s",
                redisAuthentication.getUsername(),
                redisAuthentication.getToken(),
                redisAuthentication.getExpires());
    }

    public RedisAuthentication findOneByUsernameBlocking(String username) {
        String p = redisTemplate.opsForValue().get(username).toString();
        String[] s = p.split("\\|");
        return new RedisAuthentication(s[0], s[1], Long.parseLong(s[2]));
    }

    public String saveBlocking(RedisAuthentication redisAuthentication) {
        String username = redisAuthentication.getUsername();
        String strRedisAuthentication = toStringData(redisAuthentication);
        redisTemplate.opsForValue().set(username, strRedisAuthentication);
        return redisTemplate.opsForValue().get(username).toString();
    }
}
