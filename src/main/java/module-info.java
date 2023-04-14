module com.devedubiel.workshopjavafxjdbc {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens com.devedubiel.workshopjavafxjdbc to javafx.fxml;
    exports com.devedubiel.workshopjavafxjdbc;
    exports com.devedubiel.workshopjavafxjdbc.model.entities;
    opens com.devedubiel.workshopjavafxjdbc.model.entities to javafx.fxml;
    exports com.devedubiel.workshopjavafxjdbc.services;
    opens com.devedubiel.workshopjavafxjdbc.services to javafx.fxml;
}