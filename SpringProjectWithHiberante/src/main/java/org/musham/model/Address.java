package org.musham.model;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import jakarta.persistence.Embeddable;
import org.springframework.data.mongodb.core.mapping.Field;

public class Address {
//    @Field("street")
    private String street;

//    @Field("city")
    private String city;

//    @Field("state")
    private String state;

//    @Field("zipCode")
    private String zipCode;

    // Constructors, Getters, and Setters
    public Address() {}

//    public Address(String street, String city, String state, String zipCode) {
//        this.street = street;
//        this.city = city;
//        this.state = state;
//        this.zipCode = zipCode;
//    }

    // Getters and Setters

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }
}
