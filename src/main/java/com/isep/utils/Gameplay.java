package com.isep.utils;


import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.stage.Stage;
import java.io.IOException;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.core.LoggerContext;
import org.apache.logging.log4j.core.config.Configuration;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.core.config.LoggerConfig;
import javafx.application.Application;

public class Gameplay extends Application {

    @Override
    public void start(Stage primaryStage) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("gameplay.fxml"));

        primaryStage.setTitle("RPG");
        primaryStage.setScene(new Scene(root, 750, 500));
        primaryStage.show();
    }


}
