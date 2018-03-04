package com.springsec.demo.web;

import lombok.Data;

/**
 * Created by Elimane on Mar, 2018, at 23:11
 */
@Data
public class RegisterForm {

    private String username;
    private String password;
    private String confirmPassword;
}
