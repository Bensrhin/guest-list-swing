package com.guestlist;


public class Guest {

    private String lastName;
    private String firstName;
    private String address;

    public Guest(String lastName, String firstName, String address) {
        this.lastName = lastName;
        this.firstName = firstName;
        this.address = address;
    }


    @Override
    public String toString() {
        return lastName + "  " + firstName + "  " + address;
    }

    public String getLastName() {
        return this.lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFirstName() {
        return this.firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

}