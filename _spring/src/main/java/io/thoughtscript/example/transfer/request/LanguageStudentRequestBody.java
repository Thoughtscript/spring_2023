package io.thoughtscript.example.transfer.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class LanguageStudentRequestBody extends GenericRequestBody {
    private String name;
}
