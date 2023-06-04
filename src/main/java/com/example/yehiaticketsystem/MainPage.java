package com.example.yehiaticketsystem;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import javafx.stage.StageStyle;


import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;


public class MainPage implements Initializable {
    public Button availableTrainsButton;
    @FXML
    private ComboBox<String> destinationComboBox;
    @FXML
    private ComboBox<String> arrivalComboBox;
    @FXML
    private ComboBox<String> numOfSeatsComboBox;
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
    public void availableTrainsButtonOnAction(ActionEvent event) throws Exception{
        Stage stage = new Stage();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("TrainSchedule.fxml"));
        stage.setScene(new Scene(loader.load(), 1080, 600));
        stage.initStyle(StageStyle.UNDECORATED);
        stage.show();
    }
    public void fillComboBox(){
        TrainDatabaseConnection connectNow = new TrainDatabaseConnection();
        Connection connectDB = connectNow.getConnection();
        try{
            Statement statement = connectDB.createStatement();
            ResultSet queryResult = statement.executeQuery("SELECT * FROM trainSchedule");
            ObservableList<String> destinationData = FXCollections.observableArrayList();
            ObservableList<String> arrivalData = FXCollections.observableArrayList();
            ObservableList<String> numOfSeats = FXCollections.observableArrayList("1", "2", "3", "4", "5", "6", "7", "8", "9", "10");

            while (queryResult.next()) {
                destinationData.add(queryResult.getString("DepartureLoc"));
                arrivalData.add(queryResult.getString("ArrivalLoc"));
            }
            destinationComboBox.setItems(destinationData);
            arrivalComboBox.setItems(arrivalData);
            numOfSeatsComboBox.getItems().addAll(numOfSeats);
        }catch(Exception e) {
            e.printStackTrace();
        }
    }
    public void initialize(URL arg0, ResourceBundle arg1) {
        fillComboBox();
    }
}
