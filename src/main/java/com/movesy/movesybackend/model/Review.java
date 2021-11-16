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
@Document(collection = "reviews")
public class Review {
    @Id
    private String id;

    @NotNull(message = "TransporterID cannot be null")
    @NotBlank(message = "TransporterID cannot be blank")
    private String transporterID;

    @NotNull(message = "PackageID cannot be null")
    @NotBlank(message = "PackageID cannot be blank")
    private String packageID;

    @NotBlank(message = "Username cannot be empty at Review")
    @NotNull(message = "Username cannot be empty at Review")
    @Length(min = 4, max = 20)
    @Pattern(regexp = "[a-zA-Z0-9]+", message = "Username can be 4-20 characters long and should only contain letters between a-z, A-Z and numbers between 0-9")
    private String customerUsername;

    @NotNull(message = "Reviews must have creation time")
    private Date time;

    @NotNull(message = "Rating should not be empty")
    @Min(value = 0, message = "The rating should be equal to, or greater than 0")
    @Max(value = 5, message = "The rating should be equal to, or lesser than 5")
    private int rating;

    @NotBlank(message = "Description should not be empty")
    @NotNull(message = "Description should not be empty")
    @Length(max = 5000)
    @Pattern(regexp = "[a-zA-Z0-9., \"%/()'+!?;:@&<>$-]+", message = "Description length must be between 0-5000 characters long and should not contain very special characters! (Allowed symbold are: ., \"%/()'+!?;:@&<>$-)")
    private String description;
}
