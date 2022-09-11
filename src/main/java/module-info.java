module com.example.javafxlogin {
    requires javafx.controls;
    requires javafx.fxml;
    requires com.gluonhq.charm.glisten;
    requires java.sql;
    requires mysql.connector.java;
    requires lombok;
    requires junit;
    requires org.junit.jupiter.api;
    requires org.apache.logging.log4j;

    exports com.example.javafxlogin.controller;
    opens com.example.javafxlogin.controller to javafx.fxml;
    exports com.example.javafxlogin.view;
    opens com.example.javafxlogin.view to javafx.fxml;
}