package com.debugandoideas.app_security.controller;

import com.debugandoideas.app_security.entities.JWTRequest;
import com.debugandoideas.app_security.entities.JWTResponse;
import com.debugandoideas.app_security.service.JWTService;
import com.debugandoideas.app_security.service.JwtUserDetailService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class AuthController {
    private final AuthenticationManager authenticationManager;
    private final JwtUserDetailService jwtUserDetailService;
    private final JWTService jwtService;
    @PostMapping("/authenticate")
    public ResponseEntity<?> postToken(@RequestBody JWTRequest request){
        this.authenticate(request);
        final var userDetails = this.jwtUserDetailService.loadUserByUsername(request.getUsername());
        final String token = this.jwtService.generateToken(userDetails);
        return ResponseEntity.ok(new JWTResponse(token));
    }

    private void authenticate(JWTRequest request){
        try{
            this.authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));
        }catch (BadCredentialsException | DisabledException e){
            throw new RuntimeException(e.getMessage());
        }
    }
}