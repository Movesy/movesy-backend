package com.movesy.movesybackend.model;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;

@Setter
@Getter
public class Location {
    @Min(value = -180,message = "The value of longitude is smaller than -180")
    @Max(value =  180 , message = "The value of longitude is greater than 180")
    @NotEmpty(message = "You have to give a longitude value")
    double longitude;
    @Min(value =-90,message = "The value of latitude is smaller than -90")
    @Max(value = 90,message = "The value of latitude is greater than 90")
    @NotEmpty(message = "You must give a latitude value")
    double latitude;

    Location(double latitude, double longitude) {
        this.latitude = latitude;
        this.longitude = longitude;
    }
}
