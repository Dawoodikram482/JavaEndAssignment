package com.example.javaendassignment.Database;

import com.example.javaendassignment.Models.Order;
import com.example.javaendassignment.Models.Product;
import com.example.javaendassignment.Models.User;


import java.io.Serializable;
import java.util.List;

public class SerializedDataSender implements Serializable {
  public List <User> users;
  public List <Product> products;
  public List<Order> orders;
  public SerializedDataSender(List<User> users, List<Product> products, List<Order> orders){
    this.users = users;
    this.orders = orders;
    this.products = products;
  }
}
