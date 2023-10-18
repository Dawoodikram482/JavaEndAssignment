package com.example.javaendassignment.Controllers;
import com.example.javaendassignment.Database.Database;
import com.example.javaendassignment.Models.Role;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Label;
import javafx.scene.control.Button;

import java.text.SimpleDateFormat;
import java.util.Date;
public class OrderController {
  @FXML
  private TableView TableOrderProducts;
  @FXML
  private TextField txtFirstName;
  @FXML
  private TextField txtLastName;
  @FXML
  private TextField txtEmailAddress;
  @FXML
  private TextField txtPhoneNumber;
}
