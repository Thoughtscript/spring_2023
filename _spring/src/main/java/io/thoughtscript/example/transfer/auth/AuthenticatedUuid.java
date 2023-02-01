package io.thoughtscript.example.transfer.auth;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AuthenticatedUuid extends TokenAuth {
  private String name;
}
