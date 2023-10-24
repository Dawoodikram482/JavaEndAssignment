package com.example.javaendassignment.Controllers;

import com.example.javaendassignment.Database.Database;
import com.example.javaendassignment.Models.Product;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

public class ProductInventoryController {
  private ObservableList<Product> products;
  @FXML
  private Label MessageLabel;
  @FXML
  private TableColumn stockColumn;
  @FXML
  private TableColumn nameColumn;
  @FXML
  private TableColumn categoryColumn;
  @FXML
  private TableColumn priceColumn;
  @FXML
  private TableColumn descriptionColumn;
  @FXML
  private TextField stockTextField;
  @FXML
  private TextField nameTextField;
  @FXML
  private TextField categoryTextField;
  @FXML
  private TextField priceTextField;
  @FXML
  private TextField descriptionTextField;
  @FXML
  private TableView <Product> TableProductsInventory;
  @FXML
  private Button btnAddProduct;
  @FXML
  private Button btnDeleteProduct;
  @FXML
  private Button btnEditProduct;
  private Product product;
  private Database database;

  public void setDatabase(Database database){this.database = database;}
  public void initialize() {
    try {
      TableProductsInventory.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
    }catch (Exception e){
      MessageLabel.setText("Initialization Failed");
    }
  }
  public void displayProducts(){
    try{
      products = FXCollections.observableArrayList(database.obtainProducts());
      TableProductsInventory.setItems(products);
    }catch (Exception ex){
      MessageLabel.setText("Error loading products");
      ex.printStackTrace();
    }
  }

  public void onAddProductButtonClicked() {
    try{
      int stock = Integer.parseInt(stockTextField.getText());
      String productName = nameTextField.getText();
      String productCategory = categoryTextField.getText();
      double productPrice = Double.parseDouble(priceTextField.getText());
      String productDescription = descriptionTextField.getText();

      Product product =new Product(stock,productName,productCategory,productPrice,productDescription);
      database.addProductsToDatabase(product);
      products.add(product);
      clearTextFields();
      MessageLabel.setText("Product Successfully added!");
    }catch (Exception e){
      MessageLabel.setText("Product adding failed");e.printStackTrace();
    }
  }
  public void clearTextFields(){
    try {
      stockTextField.clear();
      nameTextField.clear();
      categoryTextField.clear();
      priceTextField.clear();
      descriptionTextField.clear();
    }catch (Exception e){
      MessageLabel.setText("Clearing TextFields Failed");
    }
  }

  public void onDeleteButtonClicked() {
    try{
      ObservableList <Product> selectedProducts = TableProductsInventory.getSelectionModel().getSelectedItems();
      for(Product product:selectedProducts){
        database.deleteProductsFromDatabase(product);
      }
      products.removeAll(selectedProducts);
      MessageLabel.setText("Product Successfully Deleted!");
    }catch (Exception e){
      MessageLabel.setText("Product Deletion Failed!");
    }
  }

  public void onEditButtonClicked() {
    try{
      if(product == null){
        fillTextFieldWithProductDetails();
      }
      else {
        updateProductDetails();
        revertToPromptText();
      }
    }catch (Exception ex){
      MessageLabel.setText("No Product Selected");
    }
  }
  public void fillTextFieldWithProductDetails(){
    try{
      Product selectedProduct = TableProductsInventory.getSelectionModel().getSelectedItem();
      if (selectedProduct!=null){
        stockTextField.setPromptText(String.valueOf(selectedProduct.getStock()));
        nameTextField.setPromptText(String.valueOf(selectedProduct.getName()));
        categoryTextField.setPromptText(String.valueOf(selectedProduct.getCategory()));
        priceTextField.setPromptText(String.valueOf(selectedProduct.getPrice()));
        descriptionTextField.setPromptText(String.valueOf(selectedProduct.getDescription()));
        product = selectedProduct;
      }
    }catch (Exception ex){
      MessageLabel.setText("Error Setting Product Details as Prompt Text");
    }
  }
  public void revertToPromptText(){
    try{
      stockTextField.setPromptText("Stock");
      nameTextField.setPromptText("Name");
      categoryTextField.setPromptText("Category");
      priceTextField.setPromptText("Price");
      descriptionTextField.setPromptText("Description");
    }catch (Exception ex){
      MessageLabel.setText("Error in Reverting Prompt Text");
    }
  }
  public void updateProductDetails(){
    String stock = stockTextField.getText();
    String name = nameTextField.getText();
    String category = categoryTextField.getText();
    String  price = priceTextField.getText();
    String description = descriptionTextField.getText();
    try{
      int updatedStock = stock.isEmpty()? product.getStock(): Integer.parseInt(stock);
      String updatedName = name.isEmpty()? product.getName(): name;
      String updatedCategory = category.isEmpty()? product.getCategory(): category;
      double updatedPrice = price.isEmpty()? product.getPrice(): Double.parseDouble(price);
      String updatedDescription = description.isEmpty()? product.getDescription(): description;

      Product updatedProduct = new Product(updatedStock,updatedName,updatedCategory,updatedPrice,updatedDescription);
      database.deleteProductsFromDatabase(product);
      database.addProductsToDatabase(updatedProduct);

      int selectionIndex = products.indexOf(product);
      products.set(selectionIndex,updatedProduct);

      clearTextFields();
      product = null;
      MessageLabel.setText("Product Successfully Edited");
    }catch (Exception ex){
      MessageLabel.setText("Failed to Edit Product Details");
    }

  }

}
