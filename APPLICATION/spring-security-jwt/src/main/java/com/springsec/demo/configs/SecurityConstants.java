package com.springsec.demo.configs;

/**
 * Created by Elimane on Mar, 2018, at 02:28
 */


public class SecurityConstants {

    //In that class we are generating  Jwt's constitutionals items

    public static final  String SECRET = "Elsior@gmail.com";
    public static final  long EXPIRATION_TIME = 864_000_000; //10 DAYS in millis
    public static final  String TOKEN_PREFIX = "Bearer ";
    public static final  String HEADER_STRING = "Authorization";

}
