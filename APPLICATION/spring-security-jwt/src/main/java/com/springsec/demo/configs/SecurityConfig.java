package com.springsec.demo.configs;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * Created by Elimane on Feb, 2018, at 05:37
 */
@Configuration
@EnableWebSecurity//To activate web security
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private UserDetailsService userDetailsService;//Authentication system based on service layer

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;//Hashing function to encode password in db

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(bCryptPasswordEncoder);

//        auth.inMemoryAuthentication()
//                .withUser("admin").password("1234").roles("ADMIN","USER")
//                .and()
//                .withUser("student").password("123").roles("USER");

    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable();
//        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);//To say to spring don't create httpSession

        http.formLogin();
        http.authorizeRequests().anyRequest().authenticated();


    }
}
