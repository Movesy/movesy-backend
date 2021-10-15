package com.movesy.movesybackend.model;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class Location {
    double latitude;
    double longitude;

    Location(double latitude, double longitude) {
        this.latitude = latitude;
        this.longitude = longitude;
    }
}
