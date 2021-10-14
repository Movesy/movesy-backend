package com.movesy.movesybackend.model;

public class Offer {
    String ID;
    int price;

    public Offer(String ID, int price) {
        this.ID = ID;
        this.price = price;
    }

    public String getID() {
        return ID;
    }

    public int getPrice() {
        return price;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public void setPrice(int price) {
        this.price = price;
    }
}
