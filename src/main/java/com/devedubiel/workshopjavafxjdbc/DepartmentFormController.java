package com.devedubiel.workshopjavafxjdbc;

import com.devedubiel.workshopjavafxjdbc.controllers.DataChangeListener;
import com.devedubiel.workshopjavafxjdbc.controllers.util.Alerts;
import com.devedubiel.workshopjavafxjdbc.controllers.util.Constraints;
import com.devedubiel.workshopjavafxjdbc.controllers.util.Utils;
import com.devedubiel.workshopjavafxjdbc.database.DbException;
import com.devedubiel.workshopjavafxjdbc.model.entities.Department;
import com.devedubiel.workshopjavafxjdbc.services.DepartmentService;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class DepartmentFormController implements Initializable {
    private Department entity;
    private DepartmentService service;
    private List<DataChangeListener> dataChangeListeners = new ArrayList<>();

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

    public void setService(DepartmentService service) {
        this.service = service;
    }

    public void subscribeDataChangeList(DataChangeListener listener){
        dataChangeListeners.add(listener);
    }

    @FXML
    public void obBtSaveAction(ActionEvent event){
        if (entity == null){
            throw  new IllegalArgumentException("Entity must not be null");
        }if (service == null){
            throw new IllegalArgumentException("Service must not be null");
        }
        try {
            entity = getFormData();
            service.saveOrUpdate(entity);
            notifyDataChangedListeners();
            Utils.currentStage(event).close();
        }catch (DbException e){
            Alerts.showAlert("Error saving object", null, e.getMessage(), Alert.AlertType.ERROR);
        }
    }

    private void notifyDataChangedListeners() {
        for (DataChangeListener listener: dataChangeListeners){
            listener.onDataChange();
        }
    }

    private Department getFormData() {
        Department obj = new Department();
        obj.setId(Utils.tryParseToInteger(txtId.getText()));
        obj.setName(txtName.getText());
        return obj;
    }

    @FXML
    public void obBtCancelAction(ActionEvent event){
        Utils.currentStage(event).close();
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
