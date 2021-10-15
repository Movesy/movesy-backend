package com.movesy.movesybackend.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Document(collection="packages")
public class Package {
    @Id
    private String ID;
    private Location from;
    private Location to;
    private Date deadline;
    private int price;
    private int weight;
    private Size size;

    public Package(String ID, Location from, Location to, Date deadline, int price, int weight, Size size) {
        this.ID = ID;
        this.from = from;
        this.to = to;
        this.deadline = deadline;
        this.price = price;
        this.weight = weight;
        this.size = size;
    }

    public String getID() {
        return ID;
    }

    public Location getFrom() {
        return from;
    }

    public Location getTo() {
        return to;
    }

    public Date getDeadline() {
        return deadline;
    }

    public int getPrice() {
        return price;
    }

    public int getWeight() {
        return weight;
    }

    public Size getSize() {
        return size;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public void setFrom(Location from) {
        this.from = from;
    }

    public void setTo(Location to) {
        this.to = to;
    }

    public void setDeadline(Date deadline) {
        this.deadline = deadline;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public void setSize(Size size) {
        this.size = size;
    }
}


