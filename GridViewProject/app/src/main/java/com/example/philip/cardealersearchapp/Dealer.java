package com.example.philip.cardealersearchapp;

/**
 * The POJO Dealer class to hold dealer attributes
 */

public class Dealer {
    // the dealer name
    public String name;
    // the dealer address
    public String address;

    public Dealer(String name, String address) {

        this.name = name;
        this.address = address;
    }


    public String getName() {

        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {

        return address;
    }

    public void setAddress(String address) {

        this.address = address;
    }
}
