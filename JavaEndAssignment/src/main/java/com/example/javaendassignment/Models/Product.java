package com.example.javaendassignment.Models;


  import java.io.Serializable;

  public class Product implements Serializable{
    private String name;
    private Category category;
    private String description;
    private double price;
    private int stock;
    public Product(String name, Category category, String description, double price, int stock){
      this.name = name;
      this.category = category;
      this.description = description;
      this.price = price;
      this.stock = stock;
    }

    public String getName() {
      return name;
    }

    public void setName(String name) {
      this.name = name;
    }

    public Category getCategory() {
      return category;
    }

    public void setCategory(Category category) {
      this.category = category;
    }

    public double getPrice() {
      return price;
    }

    public void setPrice(double price) {
      this.price = price;
    }

    public String getDescription() {
      return description;
    }

    public void setDescription(String description) {
      this.description = description;
    }

    public int getStock() {
      return stock;
    }

    public void setStock(int stock) {
      this.stock = stock;
    }
  }
