package io.thoughtscript.example.configurations;

import com.mongodb.reactivestreams.client.MongoClient;
import com.mongodb.reactivestreams.client.MongoClients;
import io.thoughtscript.example.Constants;
import io.thoughtscript.example.repositories.LanguageMongoReactiveRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.config.AbstractReactiveMongoConfiguration;
import org.springframework.data.mongodb.core.ReactiveMongoTemplate;
import org.springframework.data.mongodb.repository.config.EnableReactiveMongoRepositories;

@Configuration
@EnableReactiveMongoRepositories(basePackageClasses = {LanguageMongoReactiveRepository.class})
@ComponentScan(basePackages = "io.thoughtscript.example.repositories")
class ReactiveMongoConfiguration extends AbstractReactiveMongoConfiguration {

  @Value("${spring.data.mongodb.host}")
  private String host;
  @Value("${spring.data.mongodb.port}")
  private Integer port;

  @Override
  protected String getDatabaseName() {
    return Constants.MONGO_DB_NAME;
  }

  @Override
  public MongoClient reactiveMongoClient() {
    return MongoClients.create();
  }

  @Bean
  public ReactiveMongoTemplate reactiveMongoTemplate() {
    return new ReactiveMongoTemplate(reactiveMongoClient(), getDatabaseName());
  }

}
