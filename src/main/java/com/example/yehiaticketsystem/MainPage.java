package com.example.yehiaticketsystem;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;


import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;


public class MainPage implements Initializable {
    @FXML
    private ComboBox destinationComboBox;
    @FXML
    private ComboBox arrivalComboBox;
    @FXML
    private ComboBox numOfSeatsComboBox;
    @FXML
    private Label labelMessage;
    @FXML
    private Button bookButton;

    public void bookButtonOnAction() {
        if (destinationComboBox.getItems().isEmpty() == false && arrivalComboBox.getItems().isEmpty() == false && numOfSeatsComboBox.getItems().isEmpty() == false)
            labelMessage.setText("Success");
        else {
            labelMessage.setText("Please fill every box");
        }
    }
    public void fillComboBox(){
        try{
            TrainDatabaseConnection connectNow = new TrainDatabaseConnection();
            Connection connectDB = connectNow.getConnection();
            Statement statement = connectDB.createStatement();
            ResultSet queryResult = statement.executeQuery("SELECT * FROM trainSchedule");
            ObservableList<String> destinationData = FXCollections.observableArrayList();
            ObservableList<String> arrivalData = FXCollections.observableArrayList();

            while (queryResult.next()) {
                destinationData.add(queryResult.getString("DepartureLoc"));
                arrivalData.add(queryResult.getString("ArrivalLoc"));
            }
            destinationComboBox.setItems(destinationData);
            arrivalComboBox.setItems(arrivalData);
        }catch(Exception e) {
            e.printStackTrace();
        }
    }
    public void initialize(URL arg0, ResourceBundle arg1) {
        fillComboBox();
    }
}
