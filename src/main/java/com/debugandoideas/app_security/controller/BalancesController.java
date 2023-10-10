package com.debugandoideas.app_security.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.Map;

@RestController
@RequestMapping("/balance")
public class BalancesController {

    @GetMapping
    public Map<String, String > welcome(){
        return Collections.singletonMap("msj", "balances");
    }
}