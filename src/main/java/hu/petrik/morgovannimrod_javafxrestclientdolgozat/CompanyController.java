package hu.petrik.morgovannimrod_javafxrestclientdolgozat;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import com.google.gson.Gson;

import java.io.IOException;

public class CompanyController {

    @FXML
    private TableColumn<Company, String> phoneCol;
    @FXML
    private Button updateBtn;
    @FXML
    private TableColumn<Company, Integer> totalemployeeCol;
    @FXML
    private Button insertBtn;
    @FXML
    private TableColumn<Company, String> nameCol;
    @FXML
    private TableView<Company> companyTable;
    @FXML
    private TableColumn<Company, String> creditcardCol;
    @FXML
    private Button deleteBtn;

    @FXML
    private void initialize() {
        nameCol.setCellValueFactory(new PropertyValueFactory<>("companyName"));
        phoneCol.setCellValueFactory(new PropertyValueFactory<>("companyPhone"));
        totalemployeeCol.setCellValueFactory(new PropertyValueFactory<>("totalEmployee"));
        creditcardCol.setCellValueFactory(new PropertyValueFactory<>("creditCard"));
        Platform.runLater(() -> {
            try {
                loadFromServer();
            } catch (IOException e) {
              //TODO: error
                Platform.exit();
            }
        });
    }

    private void loadFromServer() throws IOException {
        Response response = RequestHandler.get(App.BASE_URL);
        String content = response.getContent();
        Gson converter = new Gson();
        Company[] companies = converter.fromJson(content, Company[].class);
        companyTable.getItems().clear();
        for (Company company : companies) {
            companyTable.getItems().add(company);
        }
    }


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