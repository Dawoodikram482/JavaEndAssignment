package com.example.javaendassignment.Models.Exceptions;


public class AccountLockedException extends Exception{
  public AccountLockedException(String message){
    super(message);
  }
}
