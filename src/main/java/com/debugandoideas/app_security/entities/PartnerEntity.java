package com.debugandoideas.app_security.entities;

import jakarta.persistence.*;
import lombok.Data;

import java.math.BigInteger;

@Entity
@Table(name = "partners")
@Data
public class PartnerEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private BigInteger id;
    private String clientId;
    private String clientName; //En la DB se llama columna client_name
    private String clientSecret;
    private String scopes;
    private String grantTypes;
    private String authenticationMethods; //authentication_methods
    private String redirectUri; //redirect_uri
    private String redirectUriLogout;

}
