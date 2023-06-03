package com.example.yehiaticketsystem;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.sql.Connection;
import java.sql.Statement;


public class RegisterUser {
    @FXML
    private Button haveanaccountButton;
    @FXML
    private TextField firstnameTextField;
    @FXML
    private TextField lastnameTextField;
    @FXML
    private TextField usernameTextField;
    @FXML
    private TextField passwordTextField;
    @FXML
    private Label registerationMessageLabel;

    public void haveanaccountButtonOnAction(){
        haveanaccountButton.getScene().getWindow().hide();
    }
    public void registerUser(){
        if (firstnameTextField.getText().isBlank() == false && lastnameTextField.getText().isBlank() == false && usernameTextField.getText().isBlank() == false && passwordTextField.getText().isBlank() == false)
            validateRegistration();
        else
            registerationMessageLabel.setText("Please enter all your credentials required");
    }
    public void validateRegistration(){
        DatabaseConnection connectNow = new DatabaseConnection();
        Connection connectDB = connectNow.getConnection();
        String firstname = firstnameTextField.getText();
        String lastname = lastnameTextField.getText();
        String username = usernameTextField.getText();
        String password = passwordTextField.getText();
        String insertToRegister = "INSERT INTO UserAccounts (firstname, lastname, username, password) VALUES ('" + firstname + "', '" + lastname + "', '" + username + "', '" + password + "')";
        try{
            Statement statement = connectDB.createStatement();
            statement.executeUpdate(insertToRegister);
            registerationMessageLabel.setText("Registration Successful");
        }catch (Exception e){
            e.printStackTrace();
            e.getCause();
        }
    }
}
