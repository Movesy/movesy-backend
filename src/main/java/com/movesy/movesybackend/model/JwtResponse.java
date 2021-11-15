package com.movesy.movesybackend.model;

import java.io.Serial;
import java.io.Serializable;

public class JwtResponse implements Serializable {

    @Serial
    private static final long serialVersionUID = -8091879091924046844L;
    private final String jwtToken;
    private final User user;

    public JwtResponse(String jwtToken, User user) {
        this.jwtToken = jwtToken;
        this.user = user;
    }

    public String getToken() {
        return this.jwtToken;
    }

    public User getUser() {
        return this.user;
    }
}