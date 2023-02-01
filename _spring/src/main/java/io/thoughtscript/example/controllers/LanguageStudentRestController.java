package io.thoughtscript.example.controllers;

import io.thoughtscript.example.Constants;
import io.thoughtscript.example.domain.LanguageStudent;
import io.thoughtscript.example.security.PasswordlessAuthenticator;
import io.thoughtscript.example.services.LanguageStudentWebService;
import io.thoughtscript.example.transfer.auth.AuthenticatedLanguageStudentUpdate;
import io.thoughtscript.example.transfer.auth.AuthenticatedUuid;
import io.thoughtscript.example.transfer.auth.TokenAuth;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
public class LanguageStudentRestController {

    @Autowired
    PasswordlessAuthenticator passwordlessAuthenticator;

    @Autowired
    LanguageStudentWebService languageStudentWebService;

    /**
     * CRUD Operations.
     */

    // POST since token should be sent via Request Body
    @PostMapping(Constants.API_STUDENTS_ONE)
    public ResponseEntity getOneLanguageStudent(@RequestBody AuthenticatedUuid tokenAuth) {
        boolean authenticated = passwordlessAuthenticator.authenticate(tokenAuth);

        if (authenticated) {
            LanguageStudent student = languageStudentWebService.findOneUserById(tokenAuth.getName());
            return new ResponseEntity(student, HttpStatus.OK);
        }

        return new ResponseEntity("Please supply a valid token!", HttpStatus.UNAUTHORIZED);
    }

    @PostMapping(Constants.API_STUDENTS_NEW)
    public ResponseEntity saveOneLanguageStudent(@RequestBody AuthenticatedLanguageStudentUpdate tokenAuth) {
        boolean authenticated = passwordlessAuthenticator.authenticate(tokenAuth.getTokenUsername(), tokenAuth.getToken());
        if (authenticated) {
            languageStudentWebService.saveLanguageStudent(tokenAuth.getName());
            LanguageStudent student = languageStudentWebService.findOneUserById(tokenAuth.getName());
            return new ResponseEntity(student, HttpStatus.OK);
        }

        return new ResponseEntity("Please supply a valid token!", HttpStatus.UNAUTHORIZED);
    }

    @DeleteMapping(Constants.API_STUDENTS_ONE)
    public ResponseEntity deleteOneLanguageStudent(@RequestBody AuthenticatedUuid tokenAuth) {
        boolean authenticated = passwordlessAuthenticator.authenticate(tokenAuth.getTokenUsername(), tokenAuth.getToken());
        if (authenticated) {
            languageStudentWebService.deleteLanguageStudent(tokenAuth.getName());
            return new ResponseEntity("Success", HttpStatus.OK);
        }

        return new ResponseEntity("Please supply a valid token!", HttpStatus.UNAUTHORIZED);
    }

    @PutMapping(Constants.API_STUDENTS_ONE)
    public ResponseEntity updateOneLanguageStudent(@RequestBody AuthenticatedLanguageStudentUpdate tokenAuth) {
        boolean authenticated = passwordlessAuthenticator.authenticate(tokenAuth.getTokenUsername(), tokenAuth.getToken());
        if (authenticated) {
            LanguageStudent student = languageStudentWebService.findOneUserById(tokenAuth.getName());
            return new ResponseEntity(student, HttpStatus.OK);
        }

        return new ResponseEntity("Please supply a valid token!", HttpStatus.UNAUTHORIZED);
    }

    @PostMapping(Constants.API_STUDENTS_ALL)
    public ResponseEntity getAllLanguageStudents(@RequestBody TokenAuth tokenAuth) {
        boolean authenticated = passwordlessAuthenticator.authenticate(tokenAuth.getTokenUsername(), tokenAuth.getToken());
        if (authenticated) {
            List<LanguageStudent> students = languageStudentWebService.findAllLanguageStudents();
            return new ResponseEntity(students, HttpStatus.OK);
        }

        return new ResponseEntity("Please supply a valid token!", HttpStatus.UNAUTHORIZED);
    }
}