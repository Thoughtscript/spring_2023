package io.thoughtscript.example;

public class Constants {

    //Non-property constants

    //Mongo
    public static final String MONGO_DB_NAME = "mongodb";
    public static final String LANGUAGE_STUDENT_COLLECTION = "student";

    //Reactive Endpoints
    public static final String API_STUDENTS_ONE = "/api/students/one";
    public static final String API_STUDENTS_NEW = "/api/students/one/new";
    public static final String API_STUDENTS_ALL = "/api/students/all";

    public static final String API_FLUX_LANGUAGES_ONE = "/api/flux/languages/one";
    public static final String API_FLUX_LANGUAGES_NEW = "/api/flux/languages/one/new";
    public static final String API_FLUX_LANGUAGES_ALL = "/api/flux/languages/all";
    public static final String API_UNSECURED_LANGUAGES_ALL = "/api/unsecured/languages/all";

    //Functional Router Endpoints
    public static final String API_ROUTER_LANGUAGES_ONE = "/api/functional/languages/one";
    public static final String API_ROUTER_LANGUAGES_NEW = "/api/functional/languages/new";
    public static final String API_ROUTER_LANGUAGES_ALL = "/api/functional/languages/all";

    //Passwordless Auth Endpoints
    public static final String AUTH_LOGIN_ENDPOINT_FULLY_QUALIFIED = "http://127.0.0.1:8181/secured";
    public static final String AUTH_LOGIN_ENDPOINT = "/magiclink";
    public static final String SMTP_SERVER = "http://127.0.0.1:1025";

    //Email Magic Link
    public static final String EMAIL_MAGIC_LINK_GREETING = "Arrrrr! Trim up your pirate beard and get ready to board here: ";

    //Token Related
    public static final long FIFTEEN_MINS = 15 * 60 * 1000;

    //CORS Related
    public static final String CORS_ALLOWED_ORIGINS = "*";
    public static final int CORS_MAX_AGE = 3600;
}
