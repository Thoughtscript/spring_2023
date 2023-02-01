package io.thoughtscript.example.domain;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "language")
public class Language {

    @Id
    @Field("name")
    private String name;

    @Field("greeting")
    private String greeting;

    public Language(String name) {
        this.name = name;
    }
}
