package com.example.javaendassignment.Controllers;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import com.example.javaendassignment.Database.Database;
import java.net.URL;
import java.util.ResourceBundle;

public class LoginController implements Initializable{
  @FXML
  public AnchorPane anchorPane;
  @FXML
  private PasswordField pswdFieldPassword;
  @FXML
  private TextField txtFieldUserName;
  @FXML
  private Button btnLogin;
  @FXML
  public Label lblDisplayError;
  private  final Database database;

  public LoginController(Database database) {
    this.database = database;
  }

  @Override
  public void initialize(URL url, ResourceBundle resourceBundle) {

  }
}
