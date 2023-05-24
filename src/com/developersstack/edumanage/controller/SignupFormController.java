package com.developersstack.edumanage.controller;

import com.developersstack.edumanage.db.Database;
import com.developersstack.edumanage.db.DatabaseAccessCode;
import com.developersstack.edumanage.model.User;
import com.developersstack.edumanage.util.security.PasswordManager;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;

public class SignupFormController {
    public AnchorPane context;
    public TextField txtFirstName;
    public PasswordField txtPassword;
    public TextField txtEmail;
    public TextField txtLastName;

    public void signUpOnAction(ActionEvent actionEvent) throws IOException {
        String email = txtEmail.getText().toLowerCase();
        String firstName = txtFirstName.getText();
        String lastName = txtLastName.getText();
        String password = new PasswordManager().encrypt(txtPassword.getText().trim());

        User u = new User(firstName,lastName,email,password);

        try{
            if(new DatabaseAccessCode().saveUser(u)){
                new Alert(Alert.AlertType.INFORMATION, "Welcome!").show();
                setUi("LoginForm");
            }else{
                new Alert(Alert.AlertType.WARNING, "Try Again").show();
            }
        }catch (SQLException | ClassNotFoundException e){
            new Alert(Alert.AlertType.ERROR, e.toString()).show();
        }
    }

    public void alreadyHaveAnAccountOnAction(ActionEvent actionEvent) throws IOException {
        setUi("LoginForm");
    }
    private void setUi(String location) throws IOException {
        Stage stage = (Stage) context.getScene().getWindow();
        stage.setScene(new Scene(
                FXMLLoader.load(getClass().getResource("../view/"+location+".fxml"))));
        stage.centerOnScreen();
    }
}