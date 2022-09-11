package com.example.javafxlogin.view;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class RegisterView extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(LoginApplication.class.getResource("/fxml/register.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 520, 550);
        Stage registerStage = new Stage();
        registerStage.setTitle("Registrar Usuário");
        registerStage.setScene(scene);
        registerStage.show();
    }

}
