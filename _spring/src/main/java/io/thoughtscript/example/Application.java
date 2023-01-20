package io.thoughtscript.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.data.mongo.MongoDataAutoConfiguration;
import org.springframework.boot.autoconfigure.mongo.MongoAutoConfiguration;
import org.springframework.boot.autoconfigure.mongo.embedded.EmbeddedMongoAutoConfiguration;
import org.springframework.boot.autoconfigure.security.reactive.ReactiveSecurityAutoConfiguration;
import org.springframework.boot.autoconfigure.security.reactive.ReactiveUserDetailsServiceAutoConfiguration;
import org.springframework.boot.autoconfigure.security.servlet.UserDetailsServiceAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;

@SpringBootApplication

//Disable to prevent auto-configuration of security
// @EnableAutoConfiguration(exclude = {
//        ReactiveUserDetailsServiceAutoConfiguration.class,
//        ReactiveSecurityAutoConfiguration.class,
///        UserDetailsServiceAutoConfiguration.class,
//       MongoAutoConfiguration.class,
//        MongoDataAutoConfiguration.class})

@AutoConfigureAfter(EmbeddedMongoAutoConfiguration.class)

//Only applies to .properties files
//@PropertySources({
//        @PropertySource("classpath:email.properties")
//})
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
