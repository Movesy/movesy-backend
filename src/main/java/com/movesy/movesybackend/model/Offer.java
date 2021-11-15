package com.movesy.movesybackend.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.Min;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "offers")
public class Offer {
    @Id
    String id;
    String packageID;
    String transporterID;
    @Min(value = 0,message = "The price is smaller than 0 in Offer")
    int price;
}
