package com.example.javaendassignment.Models;

public class User extends Person {
  private String userName;
  private String password;
  private Role role;
  public User(String firstName, String lastName, String password,Role role) {
    super(firstName, lastName);
    this.userName = firstName;
    this.password = password;
    this.role = role;
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
}
