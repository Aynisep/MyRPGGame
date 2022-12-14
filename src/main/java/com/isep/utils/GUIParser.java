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

/**
 * classe qui définit le coeur du programme, elle permet de lancer l'application
 */
public class GUIParser extends Application {


    @Override
    public void start(Stage primaryStage) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("guiparser.fxml"));

        primaryStage.setTitle("RPG");

        primaryStage.setScene(new Scene(root, 800, 575));
        primaryStage.show();
    }

    /**
     * fonction prinicpale du programme
     * @param args aucun argument pris en compte
     */
    public static void main(String[] args) {

        LoggerContext ctx = (LoggerContext) LogManager.getContext(false);

        Configuration config = ctx.getConfiguration();
        LoggerConfig loggerConfig = config.getLoggerConfig(LogManager.ROOT_LOGGER_NAME);
        loggerConfig.setLevel(Level.WARN);

        launch();
    }
}
