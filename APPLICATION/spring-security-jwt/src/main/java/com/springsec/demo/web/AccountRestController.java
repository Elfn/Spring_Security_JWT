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
    public AppUser register(@RequestBody RegisterForm userForm)
    {
        //GET USER INFOS FROM REGISTERFORM CLASS

        //Find user in db for checking
        AppUser currentUser = accountService.findUserByUsername(userForm.getUsername());

        if(!userForm.getPassword().equals(userForm.getConfirmPassword())) throw new RuntimeException("You must confirm the password");
        if(currentUser != null) throw new RuntimeException("This user already exist!!!");

        AppUser newUser = new AppUser();
        newUser.setUsername(userForm.getUsername());
        newUser.setPassword(userForm.getPassword());

        //First save user in db
        accountService.saveUser(newUser);
        //And add him a role
        accountService.addRoleToUser("USER",userForm.getUsername());

        return newUser;
    }

}
