package com.example.yehiaticketsystem;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

public class TrainSchedule implements Initializable {
    @FXML
    private TableView<TrainSearchModel> trainTableView;
    @FXML
    private TableColumn<TrainSearchModel , String> departureLocationTableColumn;
    @FXML
    private TableColumn<TrainSearchModel , String> departureTimeTableColumn;
    @FXML
    private TableColumn<TrainSearchModel , String> arrivalLocationTableColumn;
    @FXML
    private TableColumn<TrainSearchModel , String> arrivalTimeTableColumn;
    @FXML
    private Button gobackButton;
    ObservableList<TrainSearchModel> trainSearchModelObservableList = FXCollections.observableArrayList();

    @Override
    public void initialize(URL url, ResourceBundle resource){
        TrainDatabaseConnection connectNow = new TrainDatabaseConnection();
        Connection connectDB = connectNow.getConnection();
        String trainSearchQuery = "SELECT DepartureLoc, DepartureTime, ArrivalLoc, ArrivalTime FROM trainSchedule";
        try{
            Statement statement = connectDB.createStatement();
            ResultSet queryResult = statement.executeQuery(trainSearchQuery);
            while(queryResult.next()){

                String queryDepartureLocation = queryResult.getString("DepartureLoc");
                String queryDepartureTime = queryResult.getString("DepartureTime");
                String queryArrivalLocation = queryResult.getString("ArrivalLoc");
                String queryArrivalTime = queryResult.getString("ArrivalTime");

                trainSearchModelObservableList.add(new TrainSearchModel(queryDepartureLocation, queryDepartureTime, queryArrivalLocation, queryArrivalTime));
            }
            departureLocationTableColumn.setCellValueFactory(new PropertyValueFactory<>("DepartureLoc"));
            departureTimeTableColumn.setCellValueFactory(new PropertyValueFactory<>("DepartureTime"));
            arrivalLocationTableColumn.setCellValueFactory(new PropertyValueFactory<>("ArrivalLoc"));
            arrivalTimeTableColumn.setCellValueFactory(new PropertyValueFactory<>("ArrivalTime"));
            trainTableView.setItems(trainSearchModelObservableList);

        }catch (SQLException e){
            Logger.getLogger(TrainSchedule.class.getName()).log(Level.SEVERE,null, e);
            e.printStackTrace();
        }
    }
    public void gobackButtonOnAction(ActionEvent event) throws Exception{
        Stage stage = (Stage)gobackButton.getScene().getWindow();
        stage.close();
    }

}
