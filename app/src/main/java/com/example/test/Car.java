package com.example.test;

public class Car {
    private String id, body, drive, transmission, year, price, stamp, model, generation, photo;

    public Car() {
    }

    public Car(String id, String photo, String body, String drive, String transmission, String year, String price, String stamp, String model, String generation) {
        this.id = id;
        this.photo = photo;
        this.body = body;
        this.drive = drive;
        this.transmission = transmission;
        this.year = year;
        this.price = price;
        this.stamp = stamp;
        this.model = model;
        this.generation = generation;
    }

    public String getId() {
        return id;
    }

    public String getBody() {
        return body;
    }

    public String getDrive() {
        return drive;
    }

    public String getTransmission() {
        return transmission;
    }

    public String getYear() {
        return year;
    }

    public String getPrice() {
        return price;
    }

    public String getStamp() {
        return stamp;
    }

    public String getModel() {
        return model;
    }
    public String getPhoto(){ return photo;}

    public String getGeneration() {
        return generation;
    }
}
