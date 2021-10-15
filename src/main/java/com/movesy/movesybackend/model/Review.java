package com.movesy.movesybackend.model;

import java.util.Date;

public class Review {
    String id;
    String transporterID;
    String customerUsername;
    Date time;
    int rating;
    String description;

    public Review(String id, String transporterID, String customerUsername, Date time, int rating, String description) {
        this.id = id;
        this.transporterID = transporterID;
        this.customerUsername = customerUsername;
        this.time = time;
        this.rating = rating;
        this.description = description;
    }

    public String getId() {
        return id;
    }

    public String getTransporterID() {
        return transporterID;
    }

    public String getCustomerUsername() {
        return customerUsername;
    }

    public Date getTime() {
        return time;
    }

    public int getRating() {
        return rating;
    }

    public String getDescription() {
        return description;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setTransporterID(String transporterID) {
        this.transporterID = transporterID;
    }

    public void setCustomerUsername(String customerUsername) {
        this.customerUsername = customerUsername;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
