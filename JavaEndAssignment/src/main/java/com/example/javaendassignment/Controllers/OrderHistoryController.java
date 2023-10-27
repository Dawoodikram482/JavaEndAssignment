package com.example.javaendassignment.Controllers;

import com.example.javaendassignment.Database.Database;
import com.example.javaendassignment.Models.Order;
import com.example.javaendassignment.Models.Product;
import com.example.javaendassignment.Models.User;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;


public class OrderHistoryController {
  @FXML
  private TableColumn <Order, String>orderNameColumn;
  @FXML
  private TableColumn <Order, String> totalPriceColumn;
  @FXML
  private Label warningLabel;
  @FXML
  private TableView<Order> orderHistoryTableView;
  @FXML
  private TableView<Product> orderedProductsTableView;
  private ObservableList<Order> orders;
  private ObservableList<Product> products;
  private Database database;

  public void setDatabase(Database database) {
    this.database = database;
    loadOrderData();
  }

  public void initialize() {
    orderHistoryTableView.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);

    orderNameColumn.setCellValueFactory(cellDataFeatures -> {
      Order order = cellDataFeatures.getValue();
      User user = order.getUser();
      if (user != null) {
        return new SimpleStringProperty(user.getFirstName());
      } else {
        return new SimpleStringProperty("");
      }
    });

    totalPriceColumn.setCellValueFactory(cellData -> new SimpleDoubleProperty(cellData.getValue().getProducts().stream()
        .mapToDouble(Product::getTotalPrice)
        .sum()).asObject().asString());

    orderHistoryTableView.getSelectionModel().
        selectedItemProperty().
        addListener((observableValue, oldOrder, newOrder) -> {
          if (newOrder != null) {
            loadProductData(newOrder);
          }
        });
  }

  private void loadProductData(Order order) {
    products = FXCollections.observableArrayList(order.getProducts());
    orderedProductsTableView.setItems(products);
  }

  private void loadOrderData() {
    try {
      orders = FXCollections.observableArrayList(database.getOrders());
      orderHistoryTableView.setItems(orders);
    } catch (Exception e) {
      warningLabel.setText("An Error Occurred While Loading Orders");
    }
  }
}
