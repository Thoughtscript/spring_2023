package io.thoughtscript.example.transfer.auth;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TokenAuth {
  private String username;
  private String token;
}
