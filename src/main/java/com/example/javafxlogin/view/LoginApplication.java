package com.example.javafxlogin.view;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class LoginApplication extends Application {

    public static void main(String[] args) {
        launch();
    }

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(LoginApplication.class.getResource("/fxml/login.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 530, 443);
        stage.setTitle("Login");
        stage.setScene(scene);
        stage.show();
    }

}