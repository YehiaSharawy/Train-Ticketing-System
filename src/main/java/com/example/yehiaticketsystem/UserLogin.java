package com.example.yehiaticketsystem;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class UserLogin extends MainPage{
    public Button AvailableTrainsButton;
    public Button RegisterButton;
    @FXML
    private Label LoginMessageLabel;
    @FXML
    private TextField UsernameTextfield;
    @FXML
    private PasswordField PasswordField;
    @FXML
    private Button LoginButton;

    public void loginButtonOnAction(ActionEvent event) throws Exception{
        if (UsernameTextfield.getText().isBlank() == false && PasswordField.getText().isBlank() == false)
            validateLogin();
        else
            LoginMessageLabel.setText("Please enter username and password!");
    }
    public void registerButtonOnAction(ActionEvent event) throws Exception{
        Stage stage = new Stage();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("RegisterUser.fxml"));
        stage.setScene(new Scene(loader.load(), 600, 400));
        stage.initStyle(StageStyle.UNDECORATED);
        stage.show();
    }
    public void availableTrainsButtonOnAction(ActionEvent event) throws Exception{
        Stage stage = new Stage();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("TrainSchedule.fxml"));
        stage.setScene(new Scene(loader.load(), 1080, 600));
        stage.initStyle(StageStyle.UNDECORATED);
        stage.show();
    }
    public void validateLogin(){
        DatabaseConnection connectNow = new DatabaseConnection();
        Connection connectDB = connectNow.getConnection();
        String verifyLogin = "SELECT count(1) FROM UserAccounts WHERE username = '" + UsernameTextfield.getText() + "' AND password = '" + PasswordField.getText() + "'";
        try{
            Statement statement = connectDB.createStatement();
            ResultSet queryResult = statement.executeQuery(verifyLogin);
            while(queryResult.next()){
                if(queryResult.getInt(1)==1){
                    Stage stage = new Stage();
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("MainPage.fxml"));
                    stage.initStyle(StageStyle.UNDECORATED);
                    stage.setScene(new Scene(loader.load(), 472, 708));
                    stage.show();
                    LoginMessageLabel.setText(" ");
                    Stage stage1 = (Stage)LoginButton.getScene().getWindow();
                    stage1.close();
                }else {
                    LoginMessageLabel.setText("Invalid login. Please try again.");
                }
            }
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }
}
