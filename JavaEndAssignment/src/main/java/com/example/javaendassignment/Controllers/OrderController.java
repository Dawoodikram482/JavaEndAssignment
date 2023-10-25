package com.example.javaendassignment.Controllers;
import com.example.javaendassignment.Database.Database;
import com.example.javaendassignment.Models.Product;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class OrderController  {
  @FXML
  private Button btnAddProduct;
  @FXML
  private Label messageLabel;
  @FXML
  private TableView <Product> TableOrderProducts;
  @FXML
  private TextField txtFirstName;
  @FXML
  private TextField txtLastName;
  @FXML
  private TextField txtEmailAddress;
  @FXML
  private TextField txtPhoneNumber;
  private List<Product> selectedProducts = new ArrayList<>();
  private Database database;
  public void setDatabase(Database database){this.database = database;}
  public void initialize() {
      TableOrderProducts.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
  }

  public void onAddButtonClicked() {
    try{
      FXMLLoader addOrderDialogLoader = new FXMLLoader(getClass().getResource("/com/example/javaendassignment/AddProductPopUp.fxml"));
      Parent root = addOrderDialogLoader.load();

      AddProductPopUpController dialogController = new AddProductPopUpController();
      dialogController.setDatabase(database);

      Dialog<ButtonType> dialog = new Dialog<>();
      dialog.setTitle("Add Product To Order");
      dialog.getDialogPane().setContent(root);
      dialog.showAndWait();
    }catch (IOException ex){
      messageLabel.setText("Error Loading Add Product Dialog");
      ex.printStackTrace();
    }
  }
}
