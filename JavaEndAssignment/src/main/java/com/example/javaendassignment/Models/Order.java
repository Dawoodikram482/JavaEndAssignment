package com.example.javaendassignment.Models;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class Order implements Serializable {
  private Customer customer;
  private List<OrderItem> orderItemList;
  private Date orderDate;
  public Order(Customer customer, List<OrderItem> orderItemList, Date orderDate){
    this.customer = customer;
    this.orderItemList = orderItemList;
    this.orderDate= orderDate;
  }

  public Date getOrderDate() {
    return orderDate;
  }

  public List<OrderItem> getOrderItemList() {
    return orderItemList;
  }

  public Customer getCustomer() {
    return customer;
  }
}
