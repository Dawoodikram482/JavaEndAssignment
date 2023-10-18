package com.example.javaendassignment.Controllers;
import com.example.javaendassignment.Database.Database;
import com.example.javaendassignment.Models.Role;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.control.Label;
import javafx.scene.control.Button;

import java.text.SimpleDateFormat;
import java.util.Date;


public class DashboardController {
  @FXML
  private Label welcomeLabel;
  @FXML
  private Label roleLabel;
  @FXML
  private Label dateTimeLabel;

  public void start(String username, Role userRole){
    welcomeLabel.setText("Welcome "+ username + "!");
    roleLabel.setText("Your role is "+ userRole + ".");
    SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm");
    Date now = new Date();
    dateTimeLabel.setText(dateFormat.format(now));
  }

  public void onCreateOrderBtnClicked() {
  }

  public void onProductInventoryBtnClicked() {
  }

  public void onOrderHistoryBtnClicked() {
  }
}
