package com.example.javaendassignment.Models;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Order implements Serializable {
  private User user;
  private ArrayList<Product> products;
  private Date orderDate;
  public Order(User user, Date orderDate, ArrayList <Product> products){
    this.user = user;
    this.products = products;
    this.orderDate= orderDate;
  }

  public Date getOrderDate() {
    return orderDate;
  }
  public User getUser() {
    return user;
  }

  public ArrayList<Product> getProducts() {
    return products;
  }

  public void setProducts(ArrayList<Product> products) {
    this.products = products;
  }
}
