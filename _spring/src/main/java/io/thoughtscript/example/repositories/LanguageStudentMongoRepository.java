package io.thoughtscript.example.repositories;

import io.thoughtscript.example.Constants;
import io.thoughtscript.example.domain.LanguageStudent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class LanguageStudentMongoRepository {

    @Autowired
    MongoTemplate mongoTemplate;

    public LanguageStudent findByName(String name) {
        LanguageStudent student = mongoTemplate.findById(name, LanguageStudent.class);
        return student;
    }

    public void save(LanguageStudent student) {
        mongoTemplate.save(student, Constants.LANGUAGE_STUDENT_COLLECTION);
    }

    public void deleteById(String name) {
        LanguageStudent student = mongoTemplate.findById(name, LanguageStudent.class);
        mongoTemplate.remove(student);
    }

    public List<LanguageStudent> findAll() {
        return mongoTemplate.findAll(LanguageStudent.class);
    }
}
