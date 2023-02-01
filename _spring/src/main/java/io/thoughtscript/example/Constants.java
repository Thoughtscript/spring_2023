package io.thoughtscript.example;

public class Constants {

    //Non-property constants

    //Reactive Endpoints
    public static final String API_FLUX_USER_ONE = "/api/flux/students/one";
    public static final String API_FLUX_USER_NEW = "/api/flux/students/one/new";
    public static final String API_FLUX_USER_ALL = "/api/flux/students/all";

    //Functional Router Endpoints
    public static final String API_ROUTER_USER_ONE = "/api/functional/students/one";
    public static final String API_ROUTER_USER_NEW = "/api/functional/students/new";
    public static final String API_ROUTER_USER_ALL = "/api/functional/students/all";

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
