package io.thoughtscript.example.prepopulate;

import io.thoughtscript.example.domain.Language;
import io.thoughtscript.example.domain.LanguageStudent;
import io.thoughtscript.example.domain.RedisAuthentication;
import io.thoughtscript.example.reactiverepositories.AuthenticationRedisReactiveRepository;
import io.thoughtscript.example.reactiverepositories.LanguageMongoReactiveRepository;
import io.thoughtscript.example.reactiverepositories.LanguageStudentMongoReactiveRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Component
public class
PopulateDataRunner implements CommandLineRunner {

  @Autowired
  LanguageMongoReactiveRepository languageMongoReactiveRepository;

  @Autowired
  LanguageStudentMongoReactiveRepository languageStudentMongoReactiveRepository;

  @Autowired
  AuthenticationRedisReactiveRepository authenticationRedisReactiveRepository;

  @Override
  public void run(String... args) throws Exception {

    log.info(
            "Beginning to pre-populate the database with data... waiting 20 seconds ...");

    try {
      Thread.sleep(20000);

      /**
       * Generate and Persist to MongoDB.
       */
      Language korean = new Language("korean");
      Language english = new Language("english");
      Language german = new Language("german");
      Language java = new Language("java");
      Language python = new Language("python");
      Language javascript = new Language("javascript");

      log.info("Preparing to pre-populate the database with data...");
      languageMongoReactiveRepository.saveAll(Flux.just(korean, english, german, java, python, javascript)).subscribe();
      log.info("Verifying persist to MongoDB...");
      languageMongoReactiveRepository.findAll().log().map(Language::getName).subscribe(log::info);

      Thread.sleep(10000);

      List<Language> adamsLanguages = new ArrayList<>();
      adamsLanguages.add(english);
      adamsLanguages.add(java);
      adamsLanguages.add(python);
      adamsLanguages.add(javascript);

      LanguageStudent adam = new LanguageStudent("adam", adamsLanguages, english);

      List<Language> bobsLanguages = new ArrayList<>();
      bobsLanguages.add(english);
      bobsLanguages.add(german);
      bobsLanguages.add(korean);

      LanguageStudent bob = new LanguageStudent("bob", bobsLanguages, german);

      List<Language> marysLanguage = new ArrayList<>();
      marysLanguage.add(java);
      marysLanguage.add(python);
      marysLanguage.add(javascript);

      LanguageStudent mary = new LanguageStudent("mary", marysLanguage, java);

      log.info("Preparing to pre-populate the database with data...");
      languageStudentMongoReactiveRepository.saveAll(Flux.just(adam, bob, mary)).subscribe();
      log.info("Verifying persist to MongoDB...");
      languageStudentMongoReactiveRepository.findAll().log().map(LanguageStudent::getName).subscribe(log::info);
      languageStudentMongoReactiveRepository.findAll().log().map(LanguageStudent::getLanguageIds).subscribe(t -> log.info(String.valueOf(t)));
      languageStudentMongoReactiveRepository.findAll().log().map(LanguageStudent::getPrimaryLanguageString).subscribe(log::info);

      Thread.sleep(10000);

      /**
       * Generate Default Authentication.
       */

      RedisAuthentication redisAuthenticationXiu = new RedisAuthentication("xiu", "22222");
      RedisAuthentication redisAuthenticationSolomon = new RedisAuthentication("solomon", "33333");

      authenticationRedisReactiveRepository.save(redisAuthenticationXiu).subscribe();
      authenticationRedisReactiveRepository.save(redisAuthenticationSolomon).subscribe();
      log.info("Verifying authentication persist to Redis...");
      Thread.sleep(5000);

      authenticationRedisReactiveRepository.findOneByUsername(redisAuthenticationXiu.getUsername()).subscribe();
      authenticationRedisReactiveRepository.findOneByUsername(redisAuthenticationSolomon.getUsername()).subscribe();

    } catch (Exception ex) {
      log.error("Exception pre-populating database with contact and email data: " + ex);
      ex.printStackTrace();
    } finally {
      log.info("Pre-population of database complete!");
    }

  }

}
