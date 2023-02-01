package io.thoughtscript.example.transfer.auth;

import io.thoughtscript.example.domain.Language;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class AuthenticatedUpdate extends AuthenticatedUuid {
  private Language newPrimaryLanguage;
  private List<Language> newLanguages;
  private String newName;
}
