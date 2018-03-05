package com.springsec.demo.configs;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;

/**
 * Created by Elimane on Mar, 2018, at 01:27
 */

//That filter appear before all others filter in spring security configuration and is executed at each request
public class JWTAuthorizationFilter extends OncePerRequestFilter {
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

//    //Here is response given from the server to respond HTTP OPTIONS METHOD
        response.addHeader("Access-Control-Allow-Origin", "*");

        response.addHeader("Access-Control-Allow-Headers", "Origin, " +
                "Accept, " +
                "X-Requested-With, " +
                "Content-Type, " +
                "Access-Control-Request-Method, " +
                "Access-Control-Request-Headers, " +
                "Authorization");

        response.addHeader("Access-Control-Expose-Headers",
                "Access-Control-Allow-Origin," +
                        "Access-Control-Allow-Credentials," +
                        "Authorization");
//
//        //Here we need to ckeck(In the server) if the request from client is of type OPTIONS(pre-visualization request)
//        //before send a response from server
        if (request.getMethod().equals("OPTIONS")) {
            response.setStatus(HttpServletResponse.SC_OK);
        }
        else
        {
            //Here we are getting request header
            String jwtPrefix = request.getHeader(SecurityConstants.HEADER_STRING);

            //Here we are checking request
            if (jwtPrefix == null || !jwtPrefix.startsWith(SecurityConstants.TOKEN_PREFIX)) {
                //Means That if there is no prefix or prefix is equal to "Bearer "
                //so we can consider that request as an authorization request with in its header
                // => "authorization" which means that the client can access to server's internal resources
                //filterChain.doFilter() will redirect that request to the second filter
                filterChain.doFilter(request, response);

                //Means that we went out doFilterInternal() method
                return;
            }

            //Here we are making new JWT for client authenticated

            Claims claims = Jwts.parser()
                    .setSigningKey(SecurityConstants.SECRET)

                    //Prefix deletion to create new token
                    .parseClaimsJws(jwtPrefix.replace(SecurityConstants.TOKEN_PREFIX, ""))
                    .getBody();

            String username = claims.getSubject();

            ArrayList<Map<String, String>> roles = (ArrayList<Map<String, String>>) claims.get("roles");
            Collection<GrantedAuthority> authorities = new ArrayList<>();

            //We get all user authenticated roles
            roles.forEach(role -> {
                authorities.add(new SimpleGrantedAuthority(role.get("authority")));
            });

            //We make authentication token for user
            UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(username, null, authorities);

            //We load the token in spring security context in order to make it known by spring security
            SecurityContextHolder.getContext().setAuthentication(authenticationToken);

            //We route request to the second filter(JWTAuthenticationFilter)
            filterChain.doFilter(request, response);

        }

    }
}
