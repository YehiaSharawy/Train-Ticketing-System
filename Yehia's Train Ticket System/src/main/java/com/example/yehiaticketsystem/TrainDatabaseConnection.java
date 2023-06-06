package com.example.yehiaticketsystem;

import java.sql.Connection;
import java.sql.DriverManager;

public class TrainDatabaseConnection {
    public Connection databaseLink;
    public Connection getConnection(){
        String databaseName = "traindb";
        String databaseUser = "root";
        String databasePassword = "0120113625root";
        String url = "jdbc:mysql://localhost:3306/" + databaseName;

        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            databaseLink = DriverManager.getConnection(url, databaseUser, databasePassword);
        }catch(Exception e){
            e.printStackTrace();
        }
        return databaseLink;
    }
}
