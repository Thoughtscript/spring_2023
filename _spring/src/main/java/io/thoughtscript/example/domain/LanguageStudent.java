package io.thoughtscript.example.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DocumentReference;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "student")
@Slf4j
public class LanguageStudent {

    @Id
    @Field("name")
    private String name;

    /*
     * Looks like there several known issues with @DBRef. It looks like it was removed:
     *
     * https://stackoverflow.com/questions/50058861/how-to-use-db-references-with-reactive-spring-data-mongodb
     * https://www.fisenko.net/posts/customize-spring-mongodb-dbref-resolution/
     *
     * Also: @DocumentReference(lookup = "{ 'name' : ?#{#target} }") does not appear to persist fields.
     *
     * https://docs.spring.io/spring-data/mongodb/docs/current-SNAPSHOT/reference/html/#mongo-template.save-update-remove
     * https://stackoverflow.com/questions/74479486/spring-data-reactive-mongodb-null-reference-when-acessing-object-from-documents
     *
     * Two questions here - why aren't DocumentReferences working/how should they be implemented presently? Why aren't the fields persisting at all?
     */

    /*
    @DocumentReference(lookup = "{ 'name' : ?#{#target} }")
    @Field("languages")
    private List<Language> languages;

    @DocumentReference(lookup = "{ 'name' : ?#{#target} }")
    @Field("primary_language")
    private Language primaryLanguage;
    */

    @Field("languageIds")
    private List<String> languageIds;

    @Field("primary_language_string")
    private String primaryLanguageString;

    public LanguageStudent(String name) {
        this.name = name;
    }

    public LanguageStudent(String name, List<Language> languages, Language primaryLanguage) {
        this.primaryLanguageString = primaryLanguage.getName();
        log.info(name + " has primary language: " + primaryLanguage.getName());

        this.name = name;
        //this.languages = languages;
        //this.primaryLanguage = primaryLanguage;

        List<String> languageIds = new ArrayList<>();

        for (Language l : languages) {
            languageIds.add(l.getName());
            log.info(name + " also knows the language: " + l.getName());
        }

        this.languageIds = languageIds;
    }
}
