package com.movesy.movesybackend.model;

import lombok.*;
import org.hibernate.validator.constraints.Length;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Document(collection = "users")
public class User {
    @Id
    private String id;

    @NotNull(message = "Role should not be null")
    private Role role;

    @NotNull(message = "Username cannot be null")
    @NotBlank(message = "Username cannot be blank")
    @Length(min = 4, max = 20, message = "Username must be between 4-20 characters")
    @Pattern(regexp = "[a-zA-Z0-9]+", message = "Username should only contain letters between a-z, A-Z and numbers between 0-9")
    private String username;

    @NotNull(message = "Password cannot be null")
    @NotBlank(message = "Password cannot be blank")
    @Length(min = 5, max = 60)
    private String password;

    @NotNull(message = "Email is cannot be null")
    @NotBlank(message = "Email cannot be blank")
    @Email
    private String email;

    @NotNull(message = "Telephone cannot be null")
    @NotBlank(message = "Telephone cannot be blank")
    @Pattern(regexp = "[+]?[0-9]{1,3}[0-9]{1,2}[0-9]{7}", message = "Telephone number should be a valid number and cannot be separated with symbols or spaces. Like +36201234567 or 0611234567")
    private String telephone;

    @NotNull(message = "Size cannot be null")
    private Size size;
}
