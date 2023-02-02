package io.thoughtscript.example.services;

import io.thoughtscript.example.Constants;
import io.thoughtscript.example.transfer.request.MailHogEmailRequestBody;
import io.thoughtscript.example.transfer.response.MailHogEmailResponseBody;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Slf4j
@Service
public class EmailService {

    public void sendMagicEmail(String email, String token) {
        try {

            StringBuffer emailContent = new StringBuffer();
            emailContent.append(Constants.EMAIL_MAGIC_LINK_GREETING);
            emailContent.append(Constants.AUTH_LOGIN_ENDPOINT_FULLY_QUALIFIED);
            emailContent.append("?token=");
            emailContent.append(token);
            emailContent.append("&email=");
            emailContent.append(email);

            org.springframework.web.reactive.function.client.WebClient
                    .create()
                    .post()
                    .uri(Constants.SMTP_SERVER)
                    .bodyValue(new MailHogEmailRequestBody(emailContent.toString()))
                    .retrieve()
                    .toEntity(MailHogEmailResponseBody.class).flatMap(responseEntity -> {
                        log.info("WebClient message sent!");
                        log.info(responseEntity.toString());
                        log.info(responseEntity.getBody().getMessage());
                        return Mono.empty();
                    });

        } catch (Exception ex) {

        }
    }
}
