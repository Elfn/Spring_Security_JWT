package com.springsec.demo.web;

import com.springsec.demo.entities.AppUser;
import com.springsec.demo.services.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Elimane on Mar, 2018, at 01:56
 */
@RestController
public class AccountRestController {

    @Autowired
    private AccountService accountService;

    @PostMapping("/register")
    public AppUser register(@RequestBody AppUser user)
    {
        return accountService.saveUser(user);
    }

}
