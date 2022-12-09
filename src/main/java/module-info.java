module com.example.myrpggame {
    requires javafx.controls;
    requires javafx.fxml;
    requires org.apache.logging.log4j;
    requires org.apache.logging.log4j.core;


    opens com.isep.utils to javafx.fxml;
    exports com.isep.utils;
}