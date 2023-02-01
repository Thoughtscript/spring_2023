package io.thoughtscript.example.reactiverepositories;

import io.thoughtscript.example.domain.LanguageStudent;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LanguageStudentMongoReactiveRepository extends ReactiveMongoRepository<LanguageStudent, String> {
}
