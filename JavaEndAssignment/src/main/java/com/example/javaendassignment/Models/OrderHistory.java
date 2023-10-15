package com.example.javaendassignment.Models;

import java.io.Serializable;
import java.util.Date;

public class OrderHistory implements Serializable {
  private String itemName;
  private double totalPrice;
  private Date orderDate;
  public OrderHistory(String itemName, double totalPrice, Date orderDate){
    this.itemName = itemName;
    this.totalPrice = totalPrice;
    this.orderDate = orderDate;
  }

  public String getItemName() {
    return itemName;
  }

  public void setItemName(String itemName) {
    this.itemName = itemName;
  }

  public Date getOrderDate() {
    return orderDate;
  }

  public void setOrderDate(Date orderDate) {
    this.orderDate = orderDate;
  }

  public double getTotalPrice() {
    return totalPrice;
  }

  public void setTotalPrice(double totalPrice) {
    this.totalPrice = totalPrice;
  }

}
