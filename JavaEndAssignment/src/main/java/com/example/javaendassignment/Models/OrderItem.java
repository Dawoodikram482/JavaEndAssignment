package com.example.javaendassignment.Models;

import java.io.Serializable;

public class OrderItem extends Product implements Serializable {
  private int quantity;
  public OrderItem(String name, Category category, String description, double price, int stock, int quantity) {
    super(name, category, description, price, stock);
    this.quantity = quantity;
  }

  public int getQuantity() {
    return quantity;
  }

  public void setQuantity(int quantity) {
    this.quantity = quantity;
  }
  public double getTotalPrice(){
    return getPrice()*quantity;
  }
}
