module com.devedubiel.workshopjavafxjdbc {
    requires javafx.controls;
    requires javafx.fxml;
            
                            
    opens com.devedubiel.workshopjavafxjdbc to javafx.fxml;
    exports com.devedubiel.workshopjavafxjdbc;
    exports com.devedubiel.workshopjavafxjdbc.controllers;
    opens com.devedubiel.workshopjavafxjdbc.controllers to javafx.fxml;
}