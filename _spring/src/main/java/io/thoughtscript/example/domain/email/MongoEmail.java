package io.thoughtscript.example.domain.email;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@Document(collection = "emails")
public class MongoEmail {

  @Id
  private String address;

  public MongoEmail(String address) {
    this.address = address;
  }

}