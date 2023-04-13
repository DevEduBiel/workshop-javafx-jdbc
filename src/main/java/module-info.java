module com.devedubiel.workshopjavafxjdbc {
    requires javafx.controls;
    requires javafx.fxml;
            
                            
    opens com.devedubiel.workshopjavafxjdbc to javafx.fxml;
    exports com.devedubiel.workshopjavafxjdbc;
}