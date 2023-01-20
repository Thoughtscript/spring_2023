package io.thoughtscript.example.reactiverepositories;

import io.thoughtscript.example.domain.email.MongoEmail;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Repository
public interface EmailMongoReactiveRepository extends ReactiveMongoRepository<MongoEmail, String> {

  Mono<MongoEmail> findOneByAddress(String address);

  Flux<MongoEmail> findAll();
}