package com.example.javaendassignment.Controllers;

import com.example.javaendassignment.Database.Database;
import com.example.javaendassignment.Models.Exceptions.ResultNotFoundException;
import com.example.javaendassignment.Models.Role;
import com.example.javaendassignment.Models.User;
import com.example.javaendassignment.MusicApplication;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.control.PasswordField;
import javafx.scene.control.Label;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;

public class LoginController {

    private Database database;
    @FXML
    private TextField txtFieldUserName;
    @FXML
    private PasswordField pswdFieldPassword;
    @FXML
    private Label lblErrorMessage;
    @FXML
    private Button btnLogin;
    public void start(Database database){
        this.database = database;
        btnLogin.setDisable(true);
        pswdFieldPassword.textProperty().addListener((observable, oldValue, newValue) -> {
            boolean isValidPassword = checkPasswordValidity(newValue);
            btnLogin.setDisable(!isValidPassword);
        });
    }
    protected boolean checkPasswordValidity(String password) {
        boolean hasLetters = false;
        boolean hasDigits = false;
        boolean hasSpecial = false;

        for (char c : password.toCharArray()) {
            if (Character.isDigit(c)) {
                hasDigits = true;
            } else if (Character.isLetter(c)) {
                hasLetters = true;
            } else {
                hasSpecial = true;
            }
        }
        return password.length() > 7 && (hasLetters && hasDigits && hasSpecial);
    }
    @FXML
    protected void onLoginButtonClick() throws IOException{
        String username = txtFieldUserName.getText();
        String password = pswdFieldPassword.getText();

       try {
           User user = database.loginWithCredentials(username, password);
           Role userRole = database.getUserRole(username);
           openDashboardWindow(username,userRole);

       }catch (ResultNotFoundException ex){
           lblErrorMessage.setText(ex.getMessage());
       }
    }
    private void openDashboardWindow(String  username, Role userRole) throws IOException {
        try {
            FXMLLoader dashboardLoader = new FXMLLoader(MusicApplication.class.getResource("Dashboard.fxml"));
            Parent root = dashboardLoader.load();
            DashboardController controller = dashboardLoader.getController();
            controller.start(username, userRole);
            Scene scene = new Scene(root, 643, 1093);
            Stage stage = (Stage) btnLogin.getScene().getWindow();
            stage.setTitle("Dashboard");
            stage.setScene(scene);
        }catch (IOException e){
            e.printStackTrace();
        }
    }
}