package com.debugandoideas.app_security.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import javax.sql.DataSource;


@Configuration
public class SecurityConfig {

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests(auth -> /// "/loans/**"  hace que todos los endpoints encima de loans estan protegidos
                        auth.requestMatchers("/loans", "/balance", "/accounts","/cards").authenticated()//Estos enpoints estan protegidos
                        .anyRequest().permitAll()) //anyRequest().permitAll() lo demas endpoinnts estan para libres
                        .formLogin(Customizer.withDefaults()) //la pagina web que sa     git cole por defecto
                        .httpBasic(Customizer.withDefaults()  //Lo basico de autentificacion, user pwd

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


/*
    @Bean
    InMemoryUserDetailsManager inMemoryUserDetailsManager(){
        var admin = User.withUsername("admin")
                .password("to_be_encoded")
                .authorities("ADMIN")
                .build();
        var user = User.withUsername("user")
                .password("to_be_encoded")
                .authorities("USER")
                .build();

        return new InMemoryUserDetailsManager(admin,user);
    }
*/

/*
   //Esta es otra de las implementaciones de UserDetailService

    @Autowired//NO es necesario
   @Bean
   UserDetailsService userDetailsService(DataSource dataSource){

       return new  JdbcUserDetailsManager(dataSource);
    }
*/


//Se crea nuestro propio password encoder (MyPasswordEncoder)
    @Bean
    PasswordEncoder passwordEncoder(){
        return NoOpPasswordEncoder.getInstance();
    }

/*
    @Bean
    PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
    */

}
