package com.example.javaendassignment.Database;
import com.example.javaendassignment.Models.*;
import com.example.javaendassignment.Models.Exceptions.ResultNotFoundException;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Database {
  private final List<User> users;
  private List<Product> products;
  private List<Order> orders;
  private SerializedDataSender dataSender;

  public Database() {
    products = new ArrayList<>();
    orders = new ArrayList<>();
    users = new ArrayList<>();

    users.add(new User("Dawood",  "Ikram&123", Role.Sales));
    users.add(new User("Parranasian",  "Parrapeero&123", Role.Manager));

    loadDataFromDataFile();
  }
  public void startingData(){
    products.add((new Product(10, "The Big Bazoonga", "Guitar", 1599.99, "The Big Bazoonga Guitar version one")));
    products.add((new Product(10, "The Yaba Daba Doo", "Guitar", 1399.99, "Drop the goo and yaba daba doo")));

    List<Product> orderList = new ArrayList<>();
    User customer = new User("Parranasian", "Parrapeero","Parranasianp@gmail.com",85280345);
    orderList.add(new Product(3,"The Big Bazoonga", "Guitar",1599.99));
    orders.add(new Order("12:30:00 30-10-2023",customer, (ArrayList<Product>) orderList));
  }
  public Role getUserRole(String username){
    for (User user: users){
      if(user.getUserName().equals(username)){
       return user.getRole();
      }
    }
    return null;
  }
  public boolean isOnlyLetters(String input) {
    for (char c : input.toCharArray()) {
      if (!Character.isLetter(c) && !Character.isSpaceChar(c)) {
        return true;
      }
    }
    return false;
  }
  public boolean isPositiveNumber(String input) {
    if (input == null || input.isEmpty() || input.charAt(0) == '0') {
      return false;
    }

    for (char c : input.toCharArray()) {
      if (!Character.isDigit(c)) {
        return false;
      }
    }
    return true;
  }

  public User loginWithCredentials(String username, String password){
    for (User user: users){
      if (user.getUserName().equals(username)&& user.getPassword().equals(password)){
        return user;
      }
    }
    throw new ResultNotFoundException("Invalid Username and Password combination");
  }
  public void loadDataFromDataFile(){
    File file = new File("DataFile.dat");
    if(!file.exists()){
      startingData();
      sendDataToFile();
      return;
    }
    try(ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream(file));){

      dataSender = (SerializedDataSender) inputStream.readObject();
      users.clear();
      products.clear();
      orders.clear();

      products.addAll(dataSender.products);
      users.addAll(dataSender.users);
      orders.addAll(dataSender.orders);
    } catch (IOException e) {
      throw new RuntimeException(e);
    } catch (ClassNotFoundException e) {
      throw new RuntimeException(e);
    }
  }
  public void sendDataToFile() {
    dataSender = new SerializedDataSender(users, products, orders);
    File file = new File("DataFile.dat");
    try(ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream(file));) {
      outputStream.writeObject(dataSender);
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }

  public List<Product> obtainProducts(){
    return products;
  }
  public void addProductsToDatabase(Product product){
    products.add(product);
    sendDataToFile();
  }
  public void deleteProductsFromDatabase(Product product){
    products.remove(product);
    sendDataToFile();
  }
  public Product getProductByName(String name){
    for(Product product:products){
      if(product.getName()==name){
        return product;
      }
    }
    return null;
  }
  public void increaseStock(String name, int quantity){
    Product product = getProductByName(name);
    int stock = product.getStock();
    product.setStock(stock+quantity);
  }
  public void decreaseStock(String name, int quantity){
    Product product = getProductByName(name);
    int stock = product.getStock();
    product.setStock(stock-quantity);
  }

  public List<Order> getOrders(){return orders;}
  public void addOrderToDatabase(Order order){
    orders.add(order);
    sendDataToFile();
  }
}
