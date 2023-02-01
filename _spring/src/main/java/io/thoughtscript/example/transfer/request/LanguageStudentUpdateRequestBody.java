package io.thoughtscript.example.transfer.request;

import io.thoughtscript.example.domain.Language;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class LanguageStudentUpdateRequestBody extends LanguageStudentRequestBody {
    private List<Language> languages;
    private Language primaryLanguage;
}
