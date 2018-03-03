package com.springsec.demo.services;

import com.springsec.demo.entities.AppUser;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Optional;

/**
 * Created by Elimane on Feb, 2018, at 23:19
 */
@Service
@Slf4j
public class UserDetailsImpl implements UserDetailsService {

    @Autowired
    private AccountService accountService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        AppUser currentUser = accountService.findUserByUsername(username);

        Optional<AppUser> appUserOptional = Optional.ofNullable(currentUser);

        if(!appUserOptional.isPresent())
        {
            log.debug("There is no user found!!!!");
            throw  new UsernameNotFoundException(username);
        }

        //GET USER ROLES
        Collection<GrantedAuthority> authorities = new ArrayList<>();

        appUserOptional.get().getAppRoles().forEach(r -> {authorities.add(new SimpleGrantedAuthority(r.getRoleName()));});

        return  new User(appUserOptional.get().getUsername(),
                appUserOptional.get().getPassword(),
                authorities);

    }
}
