package io.thoughtscript.example.repositories;

import io.thoughtscript.example.domain.Language;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LanguageMongoReactiveRepository extends ReactiveMongoRepository<Language, String> {
}