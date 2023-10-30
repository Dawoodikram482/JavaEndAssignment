package com.example.javaendassignment.Controllers;

import com.example.javaendassignment.Database.Database;
import com.example.javaendassignment.Models.Product;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;

public class AddProductDialogController {
  @FXML
  private TextField seachTextBox;
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
  private FilteredList<Product> filteredProducts;
  public void setOrderController (OrderController orderController){this.orderController = orderController;}
  public void initialize(Database database){
    try {
      this.database = database;
      TableProductsInventory.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
      products = FXCollections.observableArrayList(database.obtainProducts());
      TableProductsInventory.setItems(products);

      filteredProducts = new FilteredList<>(products,product -> true);
      TableProductsInventory.setItems(filteredProducts);

      seachTextBox.textProperty().addListener((observable, oldValue, newValue) -> {
        if (newValue.length() >= 3) {
          filterProducts(newValue);
        } else {
          filteredProducts.setPredicate(p -> true);
        }
      });
    }catch (Exception ex){
      warningLabel.setText("Initialization Failed");
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
  private void filterProducts(String searchInput) {
    filteredProducts.setPredicate(product -> {
      if (searchInput == null || searchInput.isEmpty()) {
        return true;
      }
      String lowerCaseSearchInput = searchInput.toLowerCase();

      return product.getName().toLowerCase().contains(lowerCaseSearchInput);
    });
  }
}
