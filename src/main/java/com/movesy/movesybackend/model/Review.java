package com.movesy.movesybackend.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.*;
import java.util.Date;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "reviews")
public class Review {
    @Id
    private String id;

    private String transporterID;
    private String packageID;
    @NotBlank(message = "Username cannot be empty at Review")
    @NotNull(message = "Username cannot be empty at Review")
    @Pattern(regexp = "/^[a-zA-Z]{5,20}$/",message = "Username length must be in 5 to 20 range")
    private String customerUsername;
    @NotNull(message = "You must give a time for Review")
    private Date time;
    @Min(value = 0 , message = "The rating of an offer is smaller than 0")
    @Max(value = 5  , message = "The rating of an offer is greater than 5")
    private int rating;
    @Pattern(regexp = "/^[a-zA-Z]{0,5000}$/",message = "Description length must be in 0 to 5000 range")
    private String description;
}
