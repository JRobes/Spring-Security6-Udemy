package com.debugandoideas.app_security.security;

import com.debugandoideas.app_security.repositories.CustomerRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.stream.Collectors;

@Component
@AllArgsConstructor
public class MyAutenticationProvider implements AuthenticationProvider {

    private CustomerRepository customerRepository;
    private PasswordEncoder passwordEncoder;
    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        final var username = authentication.getName();
        final var pwd = authentication.getCredentials().toString();
        final var customerFromDb = this.customerRepository.findByEmail(username);
        final var customer = customerFromDb.orElseThrow(() -> new BadCredentialsException("Invalid credentials") {//BadCredentialsException es la implementacion de la abstracción AuthenticationException
        });
        final var customerPwd = customer.getPassword();
        if(passwordEncoder.matches(pwd, customerPwd)){
            //final var authorities = Collections.singletonList(new SimpleGrantedAuthority(customer.getRole()));
            final var roles = customer.getRoles();
            final var authorities = roles.stream().map(role -> new SimpleGrantedAuthority(role.getName())).collect(Collectors.toList());

            return new UsernamePasswordAuthenticationToken(username, pwd, authorities);
        }
        else{
            throw new BadCredentialsException("Invalid credentials");
        }

    }

    @Override
    public boolean supports(Class<?> authentication) {

        //podíamos simplemente dejarlo como está: return null;
        return UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication);

    }
}
