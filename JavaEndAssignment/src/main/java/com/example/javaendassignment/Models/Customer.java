package com.example.javaendassignment.Models;

public class Customer extends Person {
  private String emailAddress;
  private int phoneNumber;
  public Customer(String firstName, String lastName, String emailAddress, int phoneNumber) {
    super(firstName, lastName);
    this.phoneNumber = phoneNumber;
    this.emailAddress = emailAddress;
  }

  public String getEmailAddress() {
    return emailAddress;
  }

  public void setEmailAddress(String emailAddress) {
    this.emailAddress = emailAddress;
  }

  public int getPhoneNumber() {
    return phoneNumber;
  }

  public void setPhoneNumber(int phoneNumber) {
    this.phoneNumber = phoneNumber;
  }


}
