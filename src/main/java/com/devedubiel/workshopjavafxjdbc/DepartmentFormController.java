package com.devedubiel.workshopjavafxjdbc;

import com.devedubiel.workshopjavafxjdbc.controllers.util.Constraints;
import com.devedubiel.workshopjavafxjdbc.model.entities.Department;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class DepartmentFormController implements Initializable {
    private Department entity;

    @FXML
    private TextField txtId;
    @FXML
    private TextField txtName;
    @FXML
    private Label labelErroName;
    @FXML
    private Button btSave;
    @FXML
    private Button btCancel;

    public void setEntity(Department entity) {
        this.entity = entity;
    }

    @FXML
    public void obBtSaveAction(){
        System.out.println("onBtSaveAction");
    }

    @FXML
    public void obBtCancelAction(){
        System.out.println("onBtCancelAction");
    }

    private void initializeNodes(){
        Constraints.setTextFieldInteger(txtId);
        Constraints.setTextFieldMaxLength(txtName,30);
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        initializeNodes();
    }

    public void updateFormData() {
        if (entity == null) {
            throw new IllegalStateException("Entity is null");
        }
        txtId.setText(String.valueOf(entity.getId()));
        txtName.setText(String.valueOf(entity.getName()));
    }
}
