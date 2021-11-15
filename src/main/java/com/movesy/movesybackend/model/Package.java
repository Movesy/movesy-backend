package com.movesy.movesybackend.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
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
    @Min(0)
    @Max(20)
    private String name;

    @NotNull(message = "UserID cannot be null")
    @NotBlank(message = "UserID cannot be blank")
    private String userID;

    @NotNull(message = "TransporterID cannot be null")
    @NotBlank(message = "TransporterID cannot be blank")
    private String transporterID;

    @NotNull(message = "From location cannot be null")
    private Location from;

    @NotNull(message = "To location cannot be null")
    private Location to;

    @NotNull(message = "Creation date cannot be null")
    private Date creationDate;

    @NotNull(message = "Deadline cannot be null")
    private Date deadline;

    @NotNull(message = "Price cannot be null")
    @Min(0)
    @Max(Integer.MAX_VALUE)
    private int price;

    @NotNull(message = "Weight cannot be null")
    @Min(0)
    @Max(Integer.MAX_VALUE)
    private int weight;

    @NotNull(message = "Size cannot be null")
    private Size size;

    @NotNull(message = "Status cannot be null")
    private Status status;
}


