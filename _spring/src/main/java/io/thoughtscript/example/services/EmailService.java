package io.thoughtscript.example.services;

import io.thoughtscript.example.Constants;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class EmailService {

    @Autowired
    JavaMailSender javaMailSender;

    public void sendMagicEmail(String emailAddress, String token) {
        SimpleMailMessage email = new SimpleMailMessage();

        StringBuffer emailContent = new StringBuffer();
        emailContent.append(Constants.EMAIL_MAGIC_LINK_GREETING);
        emailContent.append(Constants.AUTH_LOGIN_ENDPOINT_FULLY_QUALIFIED);
        emailContent.append("?token=");
        emailContent.append(token);
        emailContent.append("&email=");
        emailContent.append(emailAddress);

        email.setFrom("testapp@test.com");
        email.setTo(emailAddress);
        email.setText(emailContent.toString());
        email.setSubject("test");

        log.info(email.getTo()[0]);
        log.info(email.getText());
        log.info(email.getSubject());

        javaMailSender.send(email);
    }
}
