package com.movesy.movesybackend.model;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Document(collection = "users")
public class User {
    @Id
    private String id;
    private Role role;
    private String username;
    private String password;
    private String email;
    private String telephone;
    private Size size;
}
