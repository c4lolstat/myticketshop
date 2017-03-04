package com.epam.www.auth.model;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

/**
 * Created by Farkas on 2017.03.04..
 */
public class JwtAuthenticationToken extends UsernamePasswordAuthenticationToken {

    private static final Logger LOGGER = LoggerFactory.getLogger(JwtAuthenticationToken.class);

    private static final long serialVersionUID = -6181695412034946378L;

    private String token;

    public JwtAuthenticationToken(String token) {
        super(null, null);
        this.token = token;
    }

    public String getToken() {
        return token;
    }

    @Override
    public Object getCredentials() {
        return null;
    }

    @Override
    public Object getPrincipal() {
        return null;
    }
}
