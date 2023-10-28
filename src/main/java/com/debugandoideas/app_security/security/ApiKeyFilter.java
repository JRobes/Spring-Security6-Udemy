package com.debugandoideas.app_security.security;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Optional;

public class ApiKeyFilter extends OncePerRequestFilter {

    private static final String API_KEY = "myKey";
    private static final String API_KEY_HEADER = "api_key";

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        try{
            //Optional sirve para evitar el NullPointerException
            final var apiKeyOpt = Optional.of(request.getHeader(API_KEY_HEADER));
            final var apiKey = apiKeyOpt.orElseThrow(() -> new BadCredentialsException("No header api key"));
            if(!apiKey.equals(API_KEY)){
                throw new BadCredentialsException("Invalid api kay");
            }
        }catch (Exception e){
            throw new BadCredentialsException("Invalid api kay");
        }

        filterChain.doFilter(request, response);
    }
}
