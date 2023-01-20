package io.thoughtscript.example.reactiverepositories;

import io.thoughtscript.example.domain.user.MongoUser;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Repository
public interface UserMongoReactiveRepository extends ReactiveMongoRepository<MongoUser, String> {

  Mono<MongoUser> findOneByUsername(String username);

  Flux<MongoUser> findAll();
}
