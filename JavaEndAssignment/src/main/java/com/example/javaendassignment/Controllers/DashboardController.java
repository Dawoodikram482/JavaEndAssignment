package com.example.javaendassignment.Controllers;
import com.example.javaendassignment.Database.Database;
import com.example.javaendassignment.Models.User;

import javafx.fxml.FXML;

import javafx.scene.control.Label;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
public class DashboardController {

  @FXML
  private Label welcomeLabel;
  @FXML
  private Label roleLabel;
  @FXML
  private Label dateTimeLabel;
  public User user;
  public Database database;
  public void setDatabase(Database database){
    this.database = database;
  }

  public void userInstance(User user){
    this.user = user;
  }

  public void start(){
    welcomeLabel.setText("Welcome "+ user.getUserName()+ "!");
    roleLabel.setText("Your role is "+ user.getRole()+ ".");
    getUpdatedDateTime();
  }
  public void getUpdatedDateTime() {
    try {
      LocalDateTime now = LocalDateTime.now();
      DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
      String formattedDateTime = now.format(formatter);
      dateTimeLabel.setText("It is now: " + formattedDateTime);
    } catch (Exception e) {
      dateTimeLabel.setText("Error displaying the date and time.");
    }
  }

}
