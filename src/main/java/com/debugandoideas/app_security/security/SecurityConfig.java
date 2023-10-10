package com.debugandoideas.app_security.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;


@Configuration
public class SecurityConfig {

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests(auth ->
                        auth.requestMatchers("/loans", "/balance", "/accounts","/cards").authenticated().anyRequest().permitAll())
                        .formLogin(Customizer.withDefaults())
                        .httpBasic(Customizer.withDefaults()

                );
       //CONFIGURACION QUE HABRIA POR DEFECTO. ES COMO QUEDA SI NO SE PONE NADA
       /*
        http.authorizeHttpRequests(auth ->
                auth.anyRequest().authenticated())
                        .formLogin(Customizer.withDefaults())
                        .httpBasic(Customizer.withDefaults()

        );
        */
        return http.build();
    }
}
