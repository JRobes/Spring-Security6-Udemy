package com.debugandoideas.app_security.security;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Component
public class MyPasswordEncoder /*implements PasswordEncoder*/ {

    public String encode(CharSequence rawPassword) {
        return String.valueOf(rawPassword.toString().hashCode());//Esto es la lógica que podemos cambiar nosotros
    }


    public boolean matches(CharSequence rawPassword, String encodedPassword) {
        var passworAsString = String.valueOf(rawPassword.toString().hashCode());
        return encodedPassword.equals(passworAsString);
    }
}
