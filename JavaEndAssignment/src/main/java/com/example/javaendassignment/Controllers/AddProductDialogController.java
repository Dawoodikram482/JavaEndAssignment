package com.example.javaendassignment.Controllers;

import com.example.javaendassignment.Database.Database;
import com.example.javaendassignment.Models.Product;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;

public class AddProductDialogController {
  @FXML
  private Button cancelOrderBtn;
  @FXML
  private Label warningLabel;
  @FXML
  private TextField quantityTextField;
  @FXML
  private TableView <Product> TableProductsInventory;
  private ObservableList <Product> products;
  private Database database;
  private OrderController orderController;
  public void setOrderController (OrderController orderController){this.orderController = orderController;}
  public void setDatabase(Database database){
    this.database = database;
    try{
      loadProducts();
    }catch (Exception e){
      warningLabel.setText("Database Setup Failed");
      e.printStackTrace();
    }
  }
  public void initialize(){
    try {
      TableProductsInventory.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
    }catch (Exception ex){
      warningLabel.setText("Initialization Failed");
    }
  }
  public void loadProducts(){
    try {
      products = FXCollections.observableArrayList(database.obtainProducts());
      TableProductsInventory.setItems(products);
    }catch (Exception ex){
      warningLabel.setText("Error Occurred While Loading Products");
      ex.printStackTrace();
    }
  }

  public void onCancelButtonClicked(){
    try{
      Stage stage = (Stage) cancelOrderBtn.getScene().getWindow();
      stage.close();
    }catch (Exception ex){
      warningLabel.setText("Error Occurred While Closing the Screen");
    }
  }

  public void onAddBtnClicked() {
    try{
      int quantity = Integer.parseInt(quantityTextField.getText());
      Product selection = TableProductsInventory.getSelectionModel().getSelectedItem();
      if(selection == null){
        warningLabel.setText("Please Choose a Product");
        return;
      }
      if(quantity<= selection.getStock()){
        Product orderedProduct = new Product(quantity,selection.getName(),selection.getCategory(), selection.getPrice());
        database.decreaseStock(selection.getName(),quantity);
        orderController.getOrderedProduct(orderedProduct);
        warningLabel.setText("");
        quantityTextField.clear();
      }
      else {
        warningLabel.setText("Not Enough Stock");
      }
    }catch (Exception ex){
      warningLabel.setText("Error Occured While Adding Product");
      ex.printStackTrace();
    }
  }
}
