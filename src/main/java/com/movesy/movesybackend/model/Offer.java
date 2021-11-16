package com.movesy.movesybackend.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "offers")
public class Offer {
    @Id
    String id;

    @NotNull(message = "PackageID should not be null")
    @NotBlank(message = "PackageID should not be blank")
    String packageID;

    @NotNull(message = "TransporterID should not be null")
    @NotBlank(message = "TransporterID should not be blank")
    String transporterID;

    @NotNull(message = "Price should not be null")
    @Min(value = 0, message = "The price is smaller than 0 in Offer")
    int price;
}
