package com.debugandoideas.app_security.security;

import com.debugandoideas.app_security.repositories.CustomerRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional //Para llamar a la base de datos
@AllArgsConstructor
public class CustomerUserDetails implements UserDetailsService {
    private final CustomerRepository customerRepository;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return this.customerRepository.findByEmail(username)
                .map(customer -> {
                    var authotities = List.of(new SimpleGrantedAuthority(customer.getRole()));
                    return  new User(customer.getEmail(), customer.getPassword(), authotities);
                }).orElseThrow(() -> new UsernameNotFoundException("User not found"));
    }
}
