package com.debugandoideas.app_security.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
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
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;
import org.springframework.security.web.csrf.CsrfTokenRequestAttributeHandler;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import javax.sql.DataSource;
import java.util.List;


@Configuration
//@EnableMethodSecurity
public class SecurityConfig {

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        http.addFilterBefore(new ApiKeyFilter(), BasicAuthenticationFilter.class);

        //CSRF
        //var requestHandler = new CsrfTokenRequestAttributeHandler();
        //requestHandler.setCsrfRequestAttributeName("_csrf");


        http.authorizeHttpRequests(auth -> /// "/loans/**"  hace que todos los endpoints encima de loans estan protegidos
                        //auth.requestMatchers("/loans", "/balance", "/accounts","/cards").authenticated()//Estos enpoints estan protegidos
                        auth
                                //SI TRAGAJAMOS CON AUTHORITIES
                                //.requestMatchers("/loans").hasAuthority("VIEW_LOANS")
                                //.requestMatchers("/balance").hasAuthority("VIEW_BALANCE")
                                //.requestMatchers("/cards").hasAuthority("VIEW_CARDS")
                                //.requestMatchers("/accounts").hasAnyAuthority("VIEW_ACCOUNT", "VIEW_CARDS") //Los que tienen ROLE de ver accounts(VIEW_ACCOUNT) o ver cards(VIEW_CARDS) pueden ver /accounts
                                .requestMatchers("/loans", "/balance").hasRole("USER")
                                //.requestMatchers("/balance").hasRole("USER")
                                //.requestMatchers("/cards").hasRole("ADMIN")
                                .requestMatchers("/accounts", "/cards").hasRole("ADMIN")

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


        //Añado el cords definido abajo
        http.cors(cors -> corsConfigurationSource());//este es el metodo de abajo

        //Añado el csrf
        /*
        http.csrf(csrf ->
                    csrf.csrfTokenRequestHandler(requestHandler)
                        .ignoringRequestMatchers("/welcome", "/about_us")
                            .csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse()))
                .addFilterAfter(new CsrfCookieFilter(), BasicAuthenticationFilter.class);
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

    @Bean
    CorsConfigurationSource corsConfigurationSource(){
        var config = new CorsConfiguration();
        //config.setAllowedOrigins(List.of("http://localhost:4200", "http://my-ap.com"));//Los front end permitidos
        config.setAllowedOrigins(List.of("*"));//Todos están permitidos
        //config.setAllowedMethods(List.of("GET", "POST", "PUT", "DELETE"));
        config.setAllowedMethods(List.of("*"));//Todos los metodos están permitidos
        config.setAllowedMethods(List.of("*"));


        var source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", config);
        return source;
    }
}




























