package com.movesy.movesybackend.model;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Document(collection = "users")
public class User {
    @Id
    private String id;
    @NotNull(message = "Role is mandatory")
    private Role role;
    @NotNull(message = "Username is mandatory")
    @NotBlank(message = "Username is mandatory")
    private String username;
    @NotNull(message = "Password is mandatory")
    @NotBlank(message = "Password is mandatory")
    private String password;
    @NotNull(message = "Email is mandatory")
    @NotBlank(message = "Email is mandatory")
    @Email
    private String email;
    @NotNull(message = "Telephone is mandatory")
    @NotBlank(message = "Telephone is mandatory")
    private String telephone;
    @NotNull(message = "Size is mandatory")
    private Size size;
}
