package com.springsec.demo.configs;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.springsec.demo.entities.AppUser;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;

/**
 * Created by Elimane on Feb, 2018, at 05:56
 */
public class JWTAuthenticationFilter extends UsernamePasswordAuthenticationFilter {
    private AuthenticationManager authenticationManager;

    public JWTAuthenticationFilter(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
    }


    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {

        AppUser user = null;

        //ObjectMapper provides functionality for reading and writing JSON,
        // either to and from basic POJOs (Plain Old Java Objects),
        // or to and from a general-purpose JSON Tree Model (JsonNode)
        try {
            user = new ObjectMapper().readValue(request.getInputStream(),AppUser.class);
        } catch (IOException e) {
            throw  new RuntimeException(e);
        }
//        System.out.println("Username: "+user.getUsername());
//        System.out.println("Password: "+user.getPassword());
//        System.out.println("Roles: "+user.getAppRoles());

        return authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                    user.getUsername(),
                    user.getPassword()
            ));

    }

    //After loaded user into the spring context we need to use that method to
    //tell the client that authentication is  successfull
    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) throws IOException, ServletException {

        //Here we are getting user authenticated infos
        User springUser = (User) authResult.getPrincipal();

        //Here we are generating json web token
        String jwtToken = Jwts.builder().setSubject(springUser.getUsername())
                .setExpiration(new Date(System.currentTimeMillis()+SecurityConstants.EXPIRATION_TIME))

                //HS256 IS A SYMETRIC ALGORITHM with only one (secret)
                // key that is shared between the two parties.
                // Since the same key is used both to generate the signature
                // and to validate it
                .signWith(SignatureAlgorithm.HS256,SecurityConstants.SECRET)

                .claim("roles",springUser.getAuthorities())

                //COMPACT IS TO ENCODE THE TOKEN
                .compact();

        //here we are sending response+jwt to the client in the response's header
        response.addHeader(SecurityConstants.HEADER_STRING,SecurityConstants.TOKEN_PREFIX+jwtToken);

    }
}
