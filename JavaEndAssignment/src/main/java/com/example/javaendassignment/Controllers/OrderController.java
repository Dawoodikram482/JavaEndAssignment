package com.example.javaendassignment.Controllers;
import com.example.javaendassignment.Database.Database;
import com.example.javaendassignment.Models.Order;
import com.example.javaendassignment.Models.Product;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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
  private ObservableList<Product> orderedProductsList = FXCollections.observableArrayList();
  private Database database;
  public void setDatabase(Database database){this.database = database;}
  public void initialize() {
      TableOrderProducts.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
      TableOrderProducts.setItems(orderedProductsList);
  }

  public void onAddButtonClicked() {
    try{
      FXMLLoader addOrderDialogLoader = new FXMLLoader(getClass().getResource("/com/example/javaendassignment/AddProductDialog.fxml"));
      Parent root = addOrderDialogLoader.load();

      AddProductDialogController dialogController = addOrderDialogLoader.getController();
      dialogController.setDatabase(database);
      dialogController.setOrderController(this);

      Dialog<ButtonType> dialog = new Dialog<>();
      dialog.setTitle("Add Product To Order");
      dialog.getDialogPane().setContent(root);
      dialog.showAndWait();
    }catch (IOException ex){
      messageLabel.setText("Error Loading Add Product Dialog");
      ex.printStackTrace();
    }
  }
  public void getOrderedProduct(Product product){
    orderedProductsList.add(product);
  }

  public void onDeletebtnClicked() {
    Product selectedProduct = TableOrderProducts.getSelectionModel().getSelectedItem();
    if(selectedProduct!=null){
      orderedProductsList.remove(selectedProduct);
    }
  }
}
