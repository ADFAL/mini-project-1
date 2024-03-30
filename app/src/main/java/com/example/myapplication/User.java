package com.example.myapplication;

import androidx.annotation.NonNull;

public class User {
    private String firstName;
    private String lastName;
    private String gender;
    private String city;
    private String urlImage;

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getUrlImage() {
        return urlImage;
    }

    public User(String firstName, String lastName, String gender, String city,String urlImage) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.gender = gender;
        this.city = city;
        this.urlImage = urlImage;
    }

    public String fullName() {
        return String.format("%s %s", firstName, lastName);
    }

    @NonNull
    @Override
    public String toString() {
        return String.format("Hi, i'm %s, i'm %s.\nI live in %s.",
                fullName(),
                gender.equals("male") ? "♂" : "♀",
                city);

    }
}
