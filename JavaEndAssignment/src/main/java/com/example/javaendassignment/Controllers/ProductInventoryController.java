package com.example.javaendassignment.Controllers;

import com.example.javaendassignment.Database.Database;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Label;
import javafx.scene.control.Button;

import java.text.SimpleDateFormat;
import java.util.Date;
public class ProductInventoryController {
  @FXML
  private TableView TableProductsInventory;
  @FXML
  private Button btnAddProduct;
  @FXML
  private Button btnDeleteProduct;
  @FXML
  private Button btnEditProduct;

  public void initialize() {

  }
}
