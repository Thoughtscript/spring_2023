package io.thoughtscript.example.prepopulate;

import io.thoughtscript.example.domain.Language;
import io.thoughtscript.example.domain.LanguageStudent;
import io.thoughtscript.example.domain.RedisAuthentication;
import io.thoughtscript.example.repositories.AuthenticationRedisBlockingRepository;
import io.thoughtscript.example.repositories.AuthenticationRedisReactiveRepository;
import io.thoughtscript.example.repositories.LanguageMongoReactiveRepository;
import io.thoughtscript.example.repositories.LanguageStudentMongoRepository;
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
  LanguageStudentMongoRepository languageStudentMongoRepository;

  @Autowired
  AuthenticationRedisReactiveRepository authenticationRedisReactiveRepository;

  @Autowired
  AuthenticationRedisBlockingRepository authenticationRedisBlockingRepository;

  @Override
  public void run(String... args) throws Exception {

    log.info(
            "Beginning to pre-populate the database with data... waiting 20 seconds ...");

    try {
      Thread.sleep(20000);

      /**
       * Generate and Persist to MongoDB.
       */
      Language korean = new Language("korean", "Anyoung haseyo");
      Language english = new Language("english", "hello");
      Language german = new Language("german", "guten tag");
      Language java = new Language("java", "hello world");
      Language python = new Language("python", "hello world");
      Language javascript = new Language("javascript", "hello world");

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
      languageStudentMongoRepository.save(adam);
      languageStudentMongoRepository.save(bob);
      languageStudentMongoRepository.save(mary);
      log.info("Verifying persist to MongoDB...");

      List<LanguageStudent> students = languageStudentMongoRepository.findAll();
      for (LanguageStudent student : students) {
          log.info(student.getName());
          log.info(student.getPrimaryLanguage().getName());
          log.info(student.getLanguages().get(0).getName());
      }

      Thread.sleep(10000);

      /**
       * Generate Default Authentication.
       */

      RedisAuthentication redisAuthenticationXiu = new RedisAuthentication("xiu@email.com", "22222");
      RedisAuthentication redisAuthenticationSolomon = new RedisAuthentication("solomon@email.com", "33333");
      RedisAuthentication redisAuthenticationAdam = new RedisAuthentication("adam.gerard@gmail.com", "435453");

      authenticationRedisReactiveRepository.save(redisAuthenticationXiu).subscribe();
      authenticationRedisReactiveRepository.save(redisAuthenticationSolomon).subscribe();
      authenticationRedisReactiveRepository.save(redisAuthenticationAdam).subscribe();
      log.info("Verifying authentication persist to Redis...");
      Thread.sleep(5000);

      authenticationRedisReactiveRepository.findOneByUsername(redisAuthenticationXiu.getUsername()).subscribe();
      authenticationRedisReactiveRepository.findOneByUsername(redisAuthenticationSolomon.getUsername()).subscribe();
      authenticationRedisReactiveRepository.findOneByUsername(redisAuthenticationAdam.getUsername()).subscribe();

      // blocking is accessible by reactive.
      // reactive is not accessible from blocking.

      authenticationRedisBlockingRepository.saveBlocking(redisAuthenticationXiu);
      authenticationRedisBlockingRepository.saveBlocking(redisAuthenticationSolomon);
      authenticationRedisBlockingRepository.saveBlocking(redisAuthenticationAdam);

      RedisAuthentication redisAuthenticationXiuBlocking = authenticationRedisBlockingRepository.findOneByUsernameBlocking(redisAuthenticationXiu.getUsername());
      RedisAuthentication redisAuthenticationSolomonBlocking = authenticationRedisBlockingRepository.findOneByUsernameBlocking(redisAuthenticationSolomon.getUsername());
      RedisAuthentication redisAuthenticatioAdamBlocking = authenticationRedisBlockingRepository.findOneByUsernameBlocking(redisAuthenticationAdam.getUsername());

      log.info(redisAuthenticationXiuBlocking.getToken());
      log.info(redisAuthenticationSolomonBlocking.getToken());
      log.info(redisAuthenticatioAdamBlocking.getToken());

    } catch (Exception ex) {
      log.error("Exception pre-populating database with contact and email data: " + ex);
      ex.printStackTrace();
    } finally {
      log.info("Pre-population of database complete!");
    }

  }

}
