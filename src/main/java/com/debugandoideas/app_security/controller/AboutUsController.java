package com.debugandoideas.app_security.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.Map;

@RestController
@RequestMapping("/about_us")
public class AboutUsController {

    @GetMapping
    //Esta es otra manera de establecer roles !!!! además de ponerlo en la Clase SecurityConfig
    //@PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_ADMIN')")
    public Map<String, String > welcome(){
        return Collections.singletonMap("msj", "about us");
    }
}
