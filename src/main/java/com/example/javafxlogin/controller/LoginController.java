package com.example.javafxlogin.controller;

import com.example.javafxlogin.view.RegisterView;
import com.example.javafxlogin.models.User;
import com.example.javafxlogin.service.LoginService;
import com.example.javafxlogin.service.MsgLabelService;
import com.gluonhq.charm.glisten.control.TextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.List;

public class LoginController {

    @FXML
    private Button btnCancel;

    @FXML
    private Button btnCreateAccount;

    @FXML
    private Button btnLogin;

    @FXML
    private PasswordField txtPassword;

    @FXML
    private TextField txtUserName;

    @FXML
    private Label lblLoginMessage;

    private final RegisterView registerView = new RegisterView();

    @FXML
    public void onLogin(ActionEvent event) {

        if (!txtUserName.getText().isBlank() && !txtPassword.getText().isBlank()) {
            if (isValidLogin(txtUserName.getText(), txtPassword.getText())) {
                MsgLabelService.succesLabel(lblLoginMessage, "Login, Realizado com sucesso!");
            } else {
                MsgLabelService.errorLabel(lblLoginMessage, "Invalid Login, Tente Novamente!");
            }
        } else {
            MsgLabelService.errorLabel(lblLoginMessage, "Campos username e password obrigatorios!");
        }
    }

    @FXML
    public void onCancel() {
        Stage stage = (Stage) btnCancel.getScene().getWindow();
        stage.close();
    }

    private boolean isValidLogin(String username, String password) {
        List<User> userNameListFound = LoginService.findByUserName(username);
        User userFound = userNameListFound.get(0);

        return userFound.getUserName().equals(username) && userFound.getPassword().equals(password);
    }

    @FXML
    public void createAccountForm(){
        Stage registerStage = new Stage();
        try {
            registerView.start(registerStage);
            onCancel();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
