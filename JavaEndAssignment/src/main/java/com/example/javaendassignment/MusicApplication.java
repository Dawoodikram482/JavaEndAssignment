package com.example.javaendassignment;

import com.example.javaendassignment.Controllers.LoginController;
import com.example.javaendassignment.Database.Database;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class MusicApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(MusicApplication.class.getResource("LoginScreen.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 401, 256);
        stage.setTitle("Login");
        stage.setScene(scene);

        Database database = new Database();

        LoginController controller = fxmlLoader.getController();
        controller.start(database);

        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}