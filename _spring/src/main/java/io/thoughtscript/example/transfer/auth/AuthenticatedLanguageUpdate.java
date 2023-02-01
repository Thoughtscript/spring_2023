package io.thoughtscript.example.transfer.auth;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AuthenticatedLanguageUpdate extends AuthenticatedUuid {
    private String newGreetings;
}

