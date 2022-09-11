package com.example.javafxlogin.controller;

import com.example.javafxlogin.view.LoginApplication;
import com.example.javafxlogin.models.User;
import com.example.javafxlogin.service.LoginService;
import com.example.javafxlogin.service.MsgLabelService;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.List;

public class RegisterController {

    @FXML
    private Button btnRegistrar;

    @FXML
    private Button btnFechar;

    @FXML
    private Label lblConfirmPassword;

    @FXML
    private Label lblMsg;

    @FXML
    private Label lblUsernameMsg;

    @FXML
    private TextField txtConfirmPassword;

    @FXML
    private TextField txtNome;

    @FXML
    private TextField txtPassword;

    @FXML
    private TextField txtSobrenome;

    @FXML
    private TextField txtUsername;

    private final LoginApplication loginApplication = new LoginApplication();

    @FXML
    public void onFechar() {
        Stage stageLogin = new Stage();
        try {
            loginApplication.start(stageLogin);
            Stage stage = (Stage) btnFechar.getScene().getWindow();
            stage.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    public void onRegistrar(ActionEvent event) {
        try {
            if (!isValidRegister()) {
                resgisterUser();
                MsgLabelService.succesLabel(lblMsg, "Usuário registrado com sucesso!");
            } else {
                MsgLabelService.errorLabel(lblMsg, "Todos os campos devem ser preenchidos!");
            }
        } catch (SQLIntegrityConstraintViolationException e){
            lblUsernameMsg.setText("Username já existe!");
            MsgLabelService.errorLabel(lblMsg, "Erro, Campo Username Invalido!");
        }
    }

    @FXML
    public void onPasswordConfirm(){
        if (txtPassword.getText().equals(txtConfirmPassword.getText())) {
            MsgLabelService.succesLabel(lblConfirmPassword, "Password Confirmado!");
        } else {
            MsgLabelService.errorLabel(lblConfirmPassword, "Os password tem que ser iguais!");
        }
    }

    private void resgisterUser() throws SQLIntegrityConstraintViolationException {
        User user = User.UserBuilder.builder()
                .firstName(txtNome.getText())
                .lastName(txtSobrenome.getText())
                .userName(txtUsername.getText())
                .password(txtPassword.getText()).build();

        List<User> userList = LoginService.findByUserName(user.getUserName());
        if (userList.isEmpty()){
            LoginService.save(user);
        } else {
            throw new SQLIntegrityConstraintViolationException();
        }
    }

    public boolean isValidRegister() {
        return txtNome.getId().isBlank() || txtSobrenome.getText().isBlank() || txtUsername.getText().isBlank()
                || txtPassword.getText().isBlank() || txtConfirmPassword.getText().isBlank();
    }

}
