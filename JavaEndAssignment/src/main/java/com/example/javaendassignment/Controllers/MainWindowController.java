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
  public void setDatabase(Database database){this.database = database;}
  public void userInstanse(User user){this.user = user;}
  public void start(String username, Role userRole){
    welcomeLabel.setText("Welcome "+ username+ "!");
    roleLabel.setText("Your role is "+ userRole+ ".");
  }
  public void clearLabels(){
    welcomeLabel.setText("");
    roleLabel.setText("");
  }
  public void onDashboardBtnClicked() throws IOException{
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
  public void onOrderHistoryBtnClicked() throws IOException{
    clearLabels();
    switchToOrderHistoryView();
  }
  public void switchToOrderView() throws IOException {
    try {
      FXMLLoader fxmlLoader = new FXMLLoader(MusicApplication.class.getResource("CreateOrder.fxml"));
      Parent root = fxmlLoader.load();
      OrderController orderController = fxmlLoader.getController();
      orderController.initialize();
      voidVbox.getChildren().setAll(root);
    }catch (IOException ex){
      ex.printStackTrace();
    }
  }
  public void switchToProductView() throws IOException{
    try {
      FXMLLoader productFxmlLoader = new FXMLLoader(MusicApplication.class.getResource("ProductInventory.fxml"));
      Parent root = productFxmlLoader.load();
      ProductInventoryController productInventoryController = productFxmlLoader.getController();
      productInventoryController.setDatabase(database);
      productInventoryController.displayProducts();
      voidVbox.getChildren().setAll(root);
    }catch (IOException ex){
      ex.printStackTrace();
    }
  }
  public void switchToOrderHistoryView() throws IOException{
    try {
      FXMLLoader orderHistoryFxmlLoader = new FXMLLoader(MusicApplication.class.getResource("OrderHistory.fxml"));
      Parent root = orderHistoryFxmlLoader.load();
      OrderHistoryController historyController = orderHistoryFxmlLoader.getController();
      historyController.initialize();
      voidVbox.getChildren().setAll(root);

    }catch (IOException ex){
      ex.printStackTrace();
    }
  }
  public void switchToDashboardView()throws IOException {
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
