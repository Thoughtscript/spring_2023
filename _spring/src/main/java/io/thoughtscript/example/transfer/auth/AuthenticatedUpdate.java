package io.thoughtscript.example.transfer.auth;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AuthenticatedUpdate extends TokenAuth {
  private String newUsername;
  private String newName;
  private String newPhone;
  private String newEmail;
}
