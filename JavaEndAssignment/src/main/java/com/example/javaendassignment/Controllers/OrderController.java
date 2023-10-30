package com.example.javaendassignment.Controllers;

import com.example.javaendassignment.Database.Database;
import com.example.javaendassignment.Models.Order;
import com.example.javaendassignment.Models.Product;
import com.example.javaendassignment.Models.User;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.*;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;


public class OrderController {
  @FXML
  private Button btnAddProduct;
  @FXML
  private Label messageLabel;
  @FXML
  private TableView<Product> TableOrderProducts;
  @FXML
  private TextField txtFirstName;
  @FXML
  private TextField txtLastName;
  @FXML
  private TextField txtEmailAddress;
  @FXML
  private TextField txtPhoneNumber;

  private ObservableList<Product> orderedProductsList = FXCollections.observableArrayList();
  private Database database;

  public void setDatabase(Database database) {
    this.database = database;
  }

  public void initialize() {
    TableOrderProducts.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
    TableOrderProducts.setItems(orderedProductsList);
  }

  public void onAddButtonClicked() {
    try {
      FXMLLoader addOrderDialogLoader = new FXMLLoader(getClass().getResource("/com/example/javaendassignment/AddProductDialog.fxml"));
      Parent root = addOrderDialogLoader.load();

      AddProductDialogController dialogController = addOrderDialogLoader.getController();
      dialogController.setDatabase(database);
      dialogController.setOrderController(this);

      Dialog<ButtonType> dialog = new Dialog<>();
      dialog.setTitle("Add Product To Order");
      dialog.getDialogPane().setContent(root);
      dialog.showAndWait();
    } catch (IOException ex) {
      messageLabel.setText("Error Loading Add Product Dialog");
    }
  }

  public void getOrderedProduct(Product product) {
    orderedProductsList.add(product);
  }

  public void onDeletebtnClicked() {
    try {
      Product selectedProduct = TableOrderProducts.getSelectionModel().getSelectedItem();
      if (selectedProduct != null) {
        orderedProductsList.remove(selectedProduct);
        database.increaseStock(selectedProduct.getName(),selectedProduct.getQuantity());
      }
    } catch (Exception e){
      messageLabel.setText("Error Occurred While Deleting the Product");
    }
  }

  public void onCreateBtnClicked() {
    String firstName = txtFirstName.getText();
    String lastName = txtLastName.getText();
    String emailAddress = txtEmailAddress.getText();
    String phoneNumber = txtPhoneNumber.getText();
    if (firstName.isEmpty() || lastName.isEmpty() || phoneNumber.isEmpty() || emailAddress.isEmpty()) {
      messageLabel.setText("Please fill in all the fields.");
      return;
    }
    if(TableOrderProducts.getItems().isEmpty()){
      messageLabel.setText("Please click on 'Add Products' button and add at least one product to create order");
      return;
    }
    if(!validateTextFields(firstName,lastName)){
      return;
    }
    try{
      int telephoneNumber = Integer.parseInt(phoneNumber);
      User customer = new User(firstName,lastName,emailAddress,telephoneNumber);
      LocalDateTime now = LocalDateTime.now();
      DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss dd-MM-yyyy");
      String dateTime = now.format(formatter);

      Order order = new Order(dateTime,customer,new ArrayList<>(orderedProductsList));
      database.addOrderToDatabase(order);
      clearTextFields();
      messageLabel.setText("Order Successfully Created");
      TableOrderProducts.setItems(FXCollections.observableArrayList());
    }catch (Exception ex){
      messageLabel.setText("Error Occurred While Creating Order");
    }
  }
  public void clearTextFields(){
    try {
      txtFirstName.clear();
      txtLastName.clear();
      txtEmailAddress.clear();
      txtPhoneNumber.clear();
    }catch (Exception e){
      messageLabel.setText("Clearing TextFields Failed");
    }
  }
  private boolean validateTextFields(String firstName, String lastName) {
    if (database.isOnlyLetters(firstName) || database.isOnlyLetters(lastName)) {
      messageLabel.setText("First name and last name should contain only characters.");
      return false;
    }
//    if (!database.isPositiveNumber(txtPhoneNumber.getText())) {
//      messageLabel.setText("Please enter a positive phone number.");
//      return false;
//    }
    return true;
  }

}
