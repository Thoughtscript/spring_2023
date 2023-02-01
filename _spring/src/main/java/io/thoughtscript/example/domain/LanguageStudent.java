package io.thoughtscript.example.domain;

import io.thoughtscript.example.Constants;
import lombok.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.List;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = Constants.LANGUAGE_STUDENT_COLLECTION)
@Slf4j
public class LanguageStudent {

    @Id
    @Field("name")
    private String name;

    @DBRef
    @Field("languages")
    private List<Language> languages;

    @DBRef
    @Field("primary_language")
    private Language primaryLanguage;

    public LanguageStudent(String name) {
        this.name = name;
    }
}
