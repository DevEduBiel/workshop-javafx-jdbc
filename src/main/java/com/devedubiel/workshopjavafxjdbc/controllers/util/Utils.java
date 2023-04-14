package com.devedubiel.workshopjavafxjdbc.controllers.util;

import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.stage.Stage;

public class Utils {
    public static Stage currentStage(ActionEvent event) {
        return (Stage) ((Node)event.getSource()).getScene().getWindow();
    }

    public static Integer tryParseToInteger(String str) {
        try {
            return Integer.getInteger(str);
        }catch (NumberFormatException e){
            return null;
        }
    }
}
