package com.example.javaendassignment.Controllers;
import com.example.javaendassignment.Database.Database;
import com.example.javaendassignment.Models.Product;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class AddProductPopUpController implements Initializable {
  @FXML
  private TextField txtQuantity;
  @FXML
  private Button addToOrderBtn;
  @FXML
  private Button cancelOrderBtn;
  @FXML
  private Label errorMessageLabel;
  @FXML
  private TableView <Product> ProductsTableView;
  private Database database;
  public void setDatabase(Database database){
    this.database = database;
    try{
      loadProducts();
    }catch (Exception e){
//      errorMessageLabel.setText("Error Loading Data");
      e.printStackTrace();
    }
  }


  public void onCancelButtonClicked() {
    try{
      Stage stage = (Stage) cancelOrderBtn.getScene().getWindow();
      stage.close();
    }catch (Exception ex){
//      errorMessageLabel.setText("Error occurred while closing the stage");
      ex.printStackTrace();
    }
  }

  @Override
  public void initialize(URL url, ResourceBundle resourceBundle) {
    try{
      ProductsTableView.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
    }catch (Exception ex){
//      errorMessageLabel.setText("Initialization Failed");
      ex.printStackTrace();
    }
  }
  public void loadProducts(){
    try {
      ObservableList<Product> products = FXCollections.observableArrayList(database.obtainProducts());
      ProductsTableView.setItems(products);
    }catch (Exception e){
//      errorMessageLabel.setText("Loading Products Failed");
      e.printStackTrace();
    }
  }

}
