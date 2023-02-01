package io.thoughtscript.example.services;


import io.thoughtscript.example.domain.Language;
import io.thoughtscript.example.domain.LanguageStudent;
import io.thoughtscript.example.repositories.LanguageStudentMongoRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class LanguageStudentWebService {

    @Autowired
    LanguageStudentMongoRepository languageStudentMongoRepository;

    public LanguageStudent findOneUserById(String name) {
        return languageStudentMongoRepository.findByName(name);
    }

    public LanguageStudent saveLanguageStudent(String name) {
        languageStudentMongoRepository.save(new LanguageStudent(name));
        return languageStudentMongoRepository.findByName(name);
    }

    public void deleteLanguageStudent(String name) {
        languageStudentMongoRepository.deleteById(name);
    }

    public LanguageStudent updateLanguageStudent(String name, Language primaryLanguage, List<Language> languages) {
        LanguageStudent student = languageStudentMongoRepository.findByName(name);
        student.setPrimaryLanguage(primaryLanguage);
        student.setLanguages(languages);
        languageStudentMongoRepository.save(student);
        return languageStudentMongoRepository.findByName(name);
    }

    public List<LanguageStudent> findAllLanguageStudents() {
        return languageStudentMongoRepository.findAll();
    }
}
