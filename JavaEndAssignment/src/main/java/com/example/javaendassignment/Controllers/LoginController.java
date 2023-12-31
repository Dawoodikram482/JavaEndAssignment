package com.example.javaendassignment.Controllers;

import com.example.javaendassignment.Database.Database;
import com.example.javaendassignment.Models.Exceptions.AccountLockedException;
import com.example.javaendassignment.Models.Exceptions.ResultNotFoundException;
import com.example.javaendassignment.Models.Role;
import com.example.javaendassignment.Models.User;
import com.example.javaendassignment.MusicApplication;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
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
    private User user;
    private static  final int MAX_LOGIN_ATTEMPTS = 3;
    private int loginAttempts = 0;

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
           if(loginAttempts<=MAX_LOGIN_ATTEMPTS) {
               user = database.loginWithCredentials(username, password);
               Role userRole = database.getUserRole(username);
               openMainWindow(username, userRole);
               loginAttempts = 0;
           } else{
               throw new AccountLockedException("Account has been locked. Too many unsuccessful login attempts");
           }
       }catch (ResultNotFoundException ex){
           lblErrorMessage.setText(ex.getMessage());

           loginAttempts++;

           if (loginAttempts >= MAX_LOGIN_ATTEMPTS) {
               showAccountLockAlertAndExit();
           }
       } catch (AccountLockedException e) {
         throw new RuntimeException(e);
       }
    }
    private void showAccountLockAlertAndExit() {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Account Locked");
        alert.setHeaderText("Account Locked");
        alert.setContentText("Your account has been locked");

        ButtonType okButton = new ButtonType("OK");
        alert.getButtonTypes().setAll(okButton);

        alert.showAndWait().ifPresent(response -> {
                    if (response == okButton) {
                        System.exit(0);
                    }});
    }
    private void openMainWindow(String  username, Role userRole) throws IOException {
        try {
            FXMLLoader mainwindowLoader = new FXMLLoader(MusicApplication.class.getResource("MainWindow.fxml"));
            Parent root = mainwindowLoader.load();
            MainWindowController controller = mainwindowLoader.getController();
            controller.userInstance(user);
            controller.setDatabase(database);
            controller.start(username, userRole);
            Scene scene = new Scene(root, 1093, 662);
            Stage stage = (Stage) btnLogin.getScene().getWindow();
            stage.setTitle("Parranasian's Music Shop");
            stage.setScene(scene);
        }catch (IOException e){
            e.printStackTrace();
        }
    }
}