package hu.petrik.morgovannimrod_javafxrestclientdolgozat;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

public class CompanyController {

    @FXML
    private TableColumn<Company, String> phoneCol;
    @FXML
    private Button updateBtn;
    @FXML
    private TableColumn<Company, Integer>  totalemployeeCol;
    @FXML
    private Button insertBtn;
    @FXML
    private TableColumn<Company, String>  nameCol;
    @FXML
    private TableView<Company> companyTable;
    @FXML
    private TableColumn<Company, String>  creditcardCol;
    @FXML
    private Button deleteBtn;

    @FXML
    public void insertClick(ActionEvent actionEvent) {
    }

    @FXML
    public void deleteClick(ActionEvent actionEvent) {
    }

    @FXML
    public void updateClick(ActionEvent actionEvent) {
    }
}