package com.movesy.movesybackend.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.*;
import java.util.Date;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "packages")
public class Package {
    @Id
    private String id;

    @NotNull(message = "Name cannot be null")
    @NotBlank(message = "Name cannot be null")
    @Length(min = 5, max = 30)
    @Pattern(regexp = "[a-zA-Z]+")
    private String name;

    @NotNull(message = "UserID cannot be null")
    private String userID;

    private String transporterID;

    @NotNull(message = "From location cannot be null")
    private Location from;

    @NotNull(message = "To location cannot be null")
    private Location to;

    @NotNull(message = "Creation date cannot be null")
    private Date creationDate;

    @NotNull(message = "Deadline cannot be null")
    private Date deadline;

    @Min(0)
    @Max(Integer.MAX_VALUE)
    private int price;

    @NotNull(message = "Weight cannot be null")
    @Min(0)
    @Max(Integer.MAX_VALUE)
    private int weight;


    private Size size;

    @NotNull(message = "Status cannot be null")
    private Status status;
}


