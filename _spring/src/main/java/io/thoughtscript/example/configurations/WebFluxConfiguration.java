package io.thoughtscript.example.configurations;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.config.CorsRegistry;
import org.springframework.web.reactive.config.EnableWebFlux;
import org.springframework.web.reactive.config.WebFluxConfigurer;

import static io.thoughtscript.example.Constants.*;

@Configuration
@EnableWebFlux
public class WebFluxConfiguration implements WebFluxConfigurer {

    /**
     * CORS configuration
     */

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/api/**")
                .allowedOrigins(CORS_ALLOWED_ORIGINS)
                .allowedMethods("POST", "GET", "PUT", "DELETE")
                .allowCredentials(false).maxAge(CORS_MAX_AGE);

        registry.addMapping("/api/students/**")
                .allowedOrigins(CORS_ALLOWED_ORIGINS)
                .allowedMethods("POST", "GET", "PUT", "DELETE")
                .allowCredentials(false).maxAge(CORS_MAX_AGE);

        registry.addMapping("/api/flux/**")
                .allowedOrigins(CORS_ALLOWED_ORIGINS)
                .allowedMethods("POST", "GET", "PUT", "DELETE")
                .allowCredentials(false).maxAge(CORS_MAX_AGE);

        registry.addMapping("/api/functional/**")
                .allowedOrigins(CORS_ALLOWED_ORIGINS)
                .allowedMethods("POST", "GET", "PUT", "DELETE")
                .allowCredentials(false).maxAge(CORS_MAX_AGE);

        registry.addMapping("/api/unsecured/**")
                .allowedOrigins(CORS_ALLOWED_ORIGINS)
                .allowedMethods("POST", "GET", "PUT", "DELETE")
                .allowCredentials(false).maxAge(CORS_MAX_AGE);

        registry.addMapping(AUTH_LOGIN_ENDPOINT)
                .allowedOrigins(CORS_ALLOWED_ORIGINS)
                .allowedMethods("POST")
                .allowCredentials(false).maxAge(CORS_MAX_AGE);

    }

}
