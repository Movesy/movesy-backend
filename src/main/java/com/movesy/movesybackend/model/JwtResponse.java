package com.movesy.movesybackend.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serial;
import java.io.Serializable;

@RequiredArgsConstructor
@Getter
public class JwtResponse implements Serializable {

    @Serial
    private static final long serialVersionUID = -8091879091924046844L;

    @NotNull(message = "JwtToken should not be null")
    @NotBlank(message = "JwtToken should not be blank")
    private final String jwtToken;

    @NotNull(message = "User should not be null")
    private final User user;
}