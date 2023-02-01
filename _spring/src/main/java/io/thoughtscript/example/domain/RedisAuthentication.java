package io.thoughtscript.example.domain;

import io.thoughtscript.example.Constants;
import java.util.Date;

import lombok.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.annotation.Id;

@Slf4j
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RedisAuthentication {

  @Id
  private String username;
  private String token;
  private long expires;

  public RedisAuthentication(String username, String token) {
    this.username = username;
    this.token = token;
    this.expires = new Date().getTime() + Constants.FIFTEEN_MINS;
  }

  public boolean hasExpired() {
    log.info("Checking if token has expired!");
    return this.expires > new Date().getTime() + Constants.FIFTEEN_MINS;
  }

}
