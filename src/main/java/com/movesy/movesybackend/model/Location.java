package com.movesy.movesybackend.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Setter
@Getter
@RequiredArgsConstructor
public class Location {
    @Min(value = -180, message = "The value of longitude should be equal to or grater than -180")
    @Max(value = 180, message = "The value of longitude should be equal to or lesser than 180")
    @NotNull(message = "Longitude should not be null")
    double longitude;

    @Min(value = -90, message = "The value of latitude should be equal to or grater than -90")
    @Max(value = 90, message = "The value of latitude should be equal to or lesser than 90")
    @NotNull(message = "Latitude should not be null")
    double latitude;
}
