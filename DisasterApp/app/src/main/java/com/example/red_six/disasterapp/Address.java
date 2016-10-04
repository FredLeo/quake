package com.example.red_six.disasterapp;

/**
 * Created by Robert on 11/09/2016.
 */
public class Address {
    //private int id;
    private String name;
    private String address;
    private String city;
    private String region;
    public Address()
    {
    }
    public Address(String name, String address, String city, String region)
    {
        //this.id=id;
        this.name=name;
        this.address=address;
        this.city=city;
        this.region=region;

    }
//    public void setId(int id) {
//        this.id = id;
//    }
    public void setName(String name) {
        this.name = name;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setRegion(String region) {
        this.region = region;
    }
//    public int getId() {return id;    }
    public String getName() {
        return name;
    }
    public String getAddress() {
        return address;
    }

    public String getCity() {
        return city;
    }

    public String getRegion() {
        return region;
    }
}
