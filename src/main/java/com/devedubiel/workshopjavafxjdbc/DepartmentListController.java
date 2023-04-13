package com.devedubiel.workshopjavafxjdbc;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class DepartmentListController implements Initializable {
    private DepartmentService service;
    @FXML
    private TableView<Department> tableViewDepartment;
    @FXML
    private TableColumn<Department,Integer> tableColumnID;
    @FXML
    private TableColumn<Department,String> tableColumnName;
    @FXML
    private Button btNew;
    private ObservableList<Department> obsList;
    @FXML
    public void onBtNewAction(){
        System.out.println("onBtNewAction");
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        initializeNodes();
    }

    public void setService(DepartmentService service) {
        this.service = service;
    }

    private void initializeNodes() {
        tableColumnID.setCellValueFactory(new PropertyValueFactory<>("Id"));
        tableColumnName.setCellValueFactory(new PropertyValueFactory<>("Name"));

        Stage stage = (Stage) Main.getMainScene().getWindow();
        tableViewDepartment.prefHeightProperty().bind(stage.heightProperty());
    }

    public void updateTableView() {
        if (service == null) {
            throw new IllegalStateException("Service was null");
        }
        List<Department> list = service.findAll();
        obsList = FXCollections.observableArrayList(list);
        tableViewDepartment.setItems(obsList);
    }
}
