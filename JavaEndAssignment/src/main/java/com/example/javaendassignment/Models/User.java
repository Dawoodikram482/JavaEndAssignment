package com.example.javaendassignment.Models;

import java.io.Serializable;

public class User implements Serializable {
  private String firstName;
  private String lastName;
  private String emailAddress;
  private int phoneNumber;
  private String userName;
  private String password;
  private Role role;
  public User(String userName, String password,Role role) {
    this.userName = userName;
    this.password = password;
    this.role = role;
  }
  public User(String firstName, String lastName, String emailAddress, int phoneNumber){
    this.firstName = firstName;
    this.lastName = lastName;
    this.emailAddress = emailAddress;
    this.phoneNumber = phoneNumber;
}
  public String getUserName() {
    return userName;
  }

  public String getPassword() {
    return password;
  }

  public Role getRole() {
    return role;
  }

  public String getFirstName() {
    return firstName;
  }

  public String getLastName() {
    return lastName;
  }

  public String getEmailAddress() {
    return emailAddress;
  }

  public int getPhoneNumber() {
    return phoneNumber;
  }

}
