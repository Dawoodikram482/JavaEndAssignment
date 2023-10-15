package com.example.javaendassignment.Database;
import com.example.javaendassignment.Models.*;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Database {
  private final List<User> users;
  private List<Product> products;
  private List<Order> orders;
  // private List<Costumer> costumers;

  public Database() {
    products = new ArrayList<>();
    orders = new ArrayList<>();
    users = new ArrayList<>();
    //costumers = new ArrayList<>();

    users.add(new User("Dawood", "Ikram", "Ikram123", Role.Sales));
    users.add(new User("Parranasian", "Parrapeero", "Parrapeero123", Role.Manager));
  }
  public void addProductsToList(){
    Product fenderGuitar = new Product("Fender Stratocaster electric guitar",Category.Guitar,
        "One of the most iconic and recognizable electric guitars in the world," +
            " known for its sleek design and versatile sound.",1999.99,3);
    Product gibsonGuitar = new Product("The Gibson Les Paul electric guitar", Category.Guitar,
        "Celebrated for its rich, warm tones and solid body design.  " +
            "Gibson Les Paul Standard is highly regarded.",2199.49,2);
    Product steinwayPiano = new Product("Steinway & Sons: Model D", Category.Piano,
        "One of the most prestigious grand pianos in the world, known for its exceptional " +
            "craftsmanship and superior sound quality.",2499.99,2);
    Product yamahaPiano = new Product("Yamaha CFX", Category.Piano,
        "A celebrated concert grand piano that has gained recognition for its clear and powerful sound.",
        2999.99,4);
    Product drum = new Product("Ludwig Classic Maple",Category.Drum,
        "Highly regarded for its warm and versatile sound, making it a favorite among drummers",
        2999.99,1);
    products.add(fenderGuitar);
    products.add(gibsonGuitar);
    products.add(steinwayPiano);
    products.add(yamahaPiano);
    products.add(drum);
  }

}
