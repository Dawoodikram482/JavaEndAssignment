package com.example.javaendassignment.Controllers;

import com.example.javaendassignment.Database.Database;
import com.example.javaendassignment.Models.Role;
import com.example.javaendassignment.Models.User;
import com.example.javaendassignment.MusicApplication;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class MainWindowController {

  @FXML
  private Label welcomeLabel;
  @FXML
  private Label roleLabel;
  @FXML
  private Button createOrderButton;
  @FXML
  private Button productInventoryButton;
  @FXML
  private Button orderHistoryButton;
  @FXML
  private Button dashboardButton;
  @FXML
  private VBox voidVbox;
  private Database database;
  private User user;

  public void setDatabase(Database database) {
    this.database = database;
  }

  public void userInstanse(User user) {
    this.user = user;
    try {
      validateUserRole();
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  public void start(String username, Role userRole) {
    welcomeLabel.setText("Welcome " + username + "!");
    roleLabel.setText("Your role is " + userRole + ".");
  }

  public void validateUserRole() {
    try {
      Role userRole = user.getRole();

      if (userRole == Role.Sales) {
        dashboardButton.setDisable(false);
        createOrderButton.setDisable(false);
        productInventoryButton.setDisable(true);
        orderHistoryButton.setDisable(false);
      } else if (userRole == Role.Manager) {
        dashboardButton.setDisable(false);
        createOrderButton.setDisable(true);
        productInventoryButton.setDisable(false);
        orderHistoryButton.setDisable(false);
      }
    } catch (Exception e) {
//      message.setText("Error allowing access based on role.");
      e.printStackTrace();
    }
  }

  public void clearLabels() {
    welcomeLabel.setText("");
    roleLabel.setText("");
  }

  public void onDashboardBtnClicked() throws IOException {
    clearLabels();
    switchToDashboardView();
  }

  public void onCreateOrderBtnClicked() throws IOException {
    clearLabels();
    switchToOrderView();
  }

  public void onProductInventoryBtnClicked() throws IOException {
    clearLabels();
    switchToProductView();
  }

  public void onOrderHistoryBtnClicked() throws IOException {
    clearLabels();
    switchToOrderHistoryView();
  }

  public void switchToOrderView() throws IOException {
    try {
      FXMLLoader fxmlLoader = new FXMLLoader(MusicApplication.class.getResource("CreateOrder.fxml"));
      Parent root = fxmlLoader.load();
      OrderController orderController = fxmlLoader.getController();
      orderController.setDatabase(database);
      voidVbox.getChildren().setAll(root);
    } catch (IOException ex) {
      ex.printStackTrace();
    }
  }

  public void switchToProductView() throws IOException {
    try {
      FXMLLoader productFxmlLoader = new FXMLLoader(MusicApplication.class.getResource("ProductInventory.fxml"));
      Parent root = productFxmlLoader.load();
      ProductInventoryController productInventoryController = productFxmlLoader.getController();
      productInventoryController.setDatabase(database);
      productInventoryController.displayProducts();
      voidVbox.getChildren().setAll(root);
    } catch (IOException ex) {
      ex.printStackTrace();
    }
  }

  public void switchToOrderHistoryView() throws IOException {
    try {
      FXMLLoader orderHistoryFxmlLoader = new FXMLLoader(MusicApplication.class.getResource("OrderHistory.fxml"));
      Parent root = orderHistoryFxmlLoader.load();
      OrderHistoryController historyController = orderHistoryFxmlLoader.getController();
      historyController.setDatabase(database);
      voidVbox.getChildren().setAll(root);
    } catch (IOException ex) {
      ex.printStackTrace();
    }
  }

  public void switchToDashboardView() throws IOException {
    try {
      FXMLLoader dashboardLoader = new FXMLLoader(MusicApplication.class.getResource("DashboardView.fxml"));
      Parent root = dashboardLoader.load();
      DashboardController dashboardController = dashboardLoader.getController();
      dashboardController.userInstance(user);
      dashboardController.setDatabase(database);
      dashboardController.start();
      voidVbox.getChildren().setAll(root);
    } catch (IOException ex) {
      ex.printStackTrace();
    }
  }
}
